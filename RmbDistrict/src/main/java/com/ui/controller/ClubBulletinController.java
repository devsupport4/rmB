/*package com.ui.controller;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPage;
import com.sun.pdfview.PDFRenderer;
import com.ui.dao.ClubBulletinDAO;
import com.ui.model.ClubBulletin;

@RestController
public class ClubBulletinController {

	private static final Logger logger = LoggerFactory.getLogger(ClubBulletinController.class);

	@Autowired
	ClubBulletinDAO clubbulletindao;
	ClubBulletin clubbulletin;

	@RequestMapping(value = "/ClubBulletin", method = RequestMethod.GET, produces = "application/json")
	public List<ClubBulletin> ClubBulletin(HttpServletRequest request) {
		logger.info("********** Inside Club Bulletin Controller **********");

		List<ClubBulletin> ClubBulletin = clubbulletindao.getAllClubBulletins();

		return ClubBulletin;
	}

	@RequestMapping(value = "/addClubBulletin", method = RequestMethod.POST)
	public String addClubBulletin(@RequestParam(value = "file", required = false) MultipartFile file,
			HttpServletRequest request, HttpSession session, int rotaryyearid, int issueno, String bulletintitle,
			String issuedate) {
		logger.info("********** INSIDE ADD CLUB BULLETIN **********");

		String s = "y";
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}

		String image = null;
		String image1 = null;
		try {
			if (file.getOriginalFilename() != null && file.getOriginalFilename() != "") {
				try {
					byte[] bytes = file.getBytes();

					File dir = new File(
							request.getRealPath("") + "/resources/admin/images/" + File.separator + "ClubBulletins");
					if (!dir.exists())
						dir.mkdirs();

					String path = request.getRealPath("/resources/admin/images/ClubBulletins/");
					File uploadfile = new File(path + File.separator + file.getOriginalFilename());
					File filename = new File(file.getOriginalFilename());

					BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
							new FileOutputStream(uploadfile));
					bufferedOutputStream.write(bytes);
					bufferedOutputStream.close();

					File f = new File(path);
					File files[] = f.listFiles();

					for (int i = 0; i < files.length; i++) {
						if (files[i].isFile()) {
							System.out.println("File " + files[i].getName() + " " + files[i].length());
						}
					}

					// image = request.getScheme() + "://" +
					// request.getServerName() + ":" +
					// request.getServerPort()+"/rmbv/resources/admin/images/ClubBulletins/"+file.getOriginalFilename();
					image = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
							+ "/resources/admin/images/ClubBulletins/" + file.getOriginalFilename();

					// load a pdf from a byte buffer

					System.out.println("Reading: " + uploadfile.getAbsolutePath());
					RandomAccessFile raf = new RandomAccessFile(uploadfile, "r");
					FileChannel channel = raf.getChannel();
					ByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
					PDFFile pdffile = new PDFFile(buf);

					// draw the first page to an image
					PDFPage page = pdffile.getPage(0);
					int width = (int) (page.getBBox().getWidth() / 3);
					int height = (int) (page.getBBox().getHeight() / 3);

					// create and configure a graphics object
					BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
					Graphics2D g2 = img.createGraphics();
					g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

					// do the actual drawing
					Rectangle rect = new Rectangle(0, 0, width, height);
					PDFRenderer renderer = new PDFRenderer(page, g2, rect, null, Color.WHITE);
					page.waitForFinish();
					renderer.run();

					// write the thumbnail
					File out = new File(uploadfile + ".png");
					System.out.println("Writing: " + out.getAbsolutePath());
					ImageIO.write(img, "png", out);
					// image1 = request.getScheme() + "://" +
					// request.getServerName() + ":" +
					// request.getServerPort()+"/rmbv/resources/admin/images/ClubBulletins/"+filename+".png";
					image1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
							+ "/resources/admin/images/ClubBulletins/" + filename + ".png";
					// image1 = out.getAbsolutePath();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		clubbulletin = new ClubBulletin(issueno, bulletintitle, issuedate, image, image1, rotaryyearid, s, 1,
				IpAddress);
		clubbulletindao.addClubBulletin(clubbulletin);

		return "";
	}

	@RequestMapping(value = "/editClubBulletinWithoutFile", method = RequestMethod.POST)
	public String editClubBulletinWithoutFile(HttpServletRequest request, HttpSession session, int id, int rotaryyearid,
			int issueno, String bulletintitle, String issuedate) {
		logger.info("********** INSIDE EDIT CLUB BULLETINS **********");

		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}

		clubbulletin = new ClubBulletin(issueno, bulletintitle, issuedate, rotaryyearid, IpAddress, id);
		clubbulletindao.editClubBulletinWithoutFile(clubbulletin);

		return "";
	}

	@RequestMapping(value = "/editClubBulletinWithFile", method = RequestMethod.POST)
	public String editClubBulletinWithFile(@RequestParam(value = "file", required = false) MultipartFile file,
			HttpServletRequest request, HttpSession session, int id, int rotaryyearid, int issueno,
			String bulletintitle, String issuedate) {
		logger.info("********** INSIDE EDIT CLUB BULLETINS WITH FILE **********");

		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}
		String image = null;
		String image1 = null;
		try {
			if (file.getOriginalFilename() != null && file.getOriginalFilename() != "") {
				try {
					byte[] bytes = file.getBytes();

					File dir = new File(
							request.getRealPath("") + "/resources/admin/images/" + File.separator + "ClubBulletins");
					if (!dir.exists())
						dir.mkdirs();

					String path = request.getRealPath("/resources/admin/images/ClubBulletins/");
					File uploadfile = new File(path + File.separator + file.getOriginalFilename());
					File filename = new File(file.getOriginalFilename());
					BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
							new FileOutputStream(uploadfile));
					bufferedOutputStream.write(bytes);
					bufferedOutputStream.close();

					File f = new File(path);
					File files[] = f.listFiles();

					for (int i = 0; i < files.length; i++) {
						if (files[i].isFile()) {
							System.out.println("File " + files[i].getName() + " " + files[i].length());
						}
					}
					// image = request.getScheme() + "://" +
					// request.getServerName() + ":" +
					// request.getServerPort()+"/rmbv/resources/admin/images/ClubBulletins/"+file.getOriginalFilename();
					image = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
							+ "/resources/front/admin/ClubBulletins/" + file.getOriginalFilename();

					// load a pdf from a byte buffer
					System.out.println("Reading: " + uploadfile.getAbsolutePath());
					RandomAccessFile raf = new RandomAccessFile(uploadfile, "r");
					FileChannel channel = raf.getChannel();
					ByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
					PDFFile pdffile = new PDFFile(buf);

					// draw the first page to an image
					PDFPage page = pdffile.getPage(0);
					int width = (int) (page.getBBox().getWidth() / 8);
					int height = (int) (page.getBBox().getHeight() / 8);

					// create and configure a graphics object
					BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
					Graphics2D g2 = img.createGraphics();
					g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

					// do the actual drawing
					Rectangle rect = new Rectangle(0, 0, width, height);
					PDFRenderer renderer = new PDFRenderer(page, g2, rect, null, Color.WHITE);
					page.waitForFinish();
					renderer.run();

					// write the thumbnail
					File out = new File(uploadfile + ".png");
					System.out.println("Writing: " + out.getAbsolutePath());
					ImageIO.write(img, "png", out);
					// image1 = request.getScheme() + "://" +
					// request.getServerName() + ":" +
					// request.getServerPort()+"/rmbv/resources/admin/images/ClubBulletins/"+filename+".png";
					image1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
							+ "/resources/admin/images/ClubBulletins/" + filename + ".png";
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		clubbulletin = new ClubBulletin(issueno, bulletintitle, issuedate, image, image1, rotaryyearid, IpAddress, id);
		clubbulletindao.editClubBulletinWithFile(clubbulletin);

		return "";
	}

	@RequestMapping(value = "/deleteClubBulletin", method = RequestMethod.DELETE)
	public void delete(int id) {
		logger.info("********** INSIDE DELETE CLUB BULLETIN ********** ");

		clubbulletindao.deleteClubBulletin(id);
	}

	@RequestMapping(value = "/getClubBulletinDetailByRotaryYear", method = RequestMethod.GET, produces = "application/json")
	public List<ClubBulletin> getClubBulletinDetailByRotaryYear(int rotaryyearid, HttpServletRequest request) {
		logger.info("***** INSIDE GET NEWS OF CURRENT YEAR *****");
		List<ClubBulletin> ClubBulletin = clubbulletindao.getClubBulletinDetailByRotaryYear(rotaryyearid);
		return ClubBulletin;
	}
}
*/