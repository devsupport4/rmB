package com.ui.controller;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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

import com.ui.dao.NewsDAO;
import com.ui.model.News;

@RestController
public class NewsController {

	private static final Logger logger = LoggerFactory.getLogger(NewsController.class);

	@Autowired
	NewsDAO newsdao;
	News news;

	@RequestMapping(value = "/News", method = RequestMethod.GET, produces = "application/json")
	public List<News> News(HttpServletRequest request) {
		logger.info("********** Inside News Controller **********");
		List<News> News = newsdao.getAllNews();
		return News;
	}
	
	@RequestMapping(value = "/getLastThreeNewsForHome", method = RequestMethod.GET, produces = "application/json")
	public List<News> getLastThreeNewsForHome(HttpServletRequest request) {
		logger.info("***** GET LAST THREE NEWS FOR HOME *****");
		List<News> News = newsdao.getLastThreeNewsForHome();
		return News;
	}

	@RequestMapping(value = "/addNews", method = RequestMethod.POST)
	public String addNews(@RequestParam(value = "file", required = false) MultipartFile[] file,
			HttpServletRequest request, HttpSession session, String newstitle, String newssubtitle,
			int beneficiarytypeid, String beneficiaryno, String newsdate, String newsimagetitle, String newsdescription,
			int rotaryyearid, int project, int valuex, int valuey, int valuew, int valueh) {
		logger.info("********** INSIDE ADD NEWS **********");

		String s = "y";
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}

		String image = null;
		try {
			for (int i = 0; i < file.length; i++) {
				if (file[i].getOriginalFilename() != null && file[i].getOriginalFilename() != "") {
					try {
						byte[] bytes = file[i].getBytes();

						File dir = new File(
								request.getRealPath("") + "/resources/admin/images/" + File.separator + "news");
						if (!dir.exists())
							dir.mkdirs();
						String path = request.getRealPath("/resources/admin/images/news/");
						File uploadfile = new File(path + File.separator + file[i].getOriginalFilename());

						ByteArrayInputStream in = new ByteArrayInputStream(bytes);
						try {
							BufferedImage img = ImageIO.read(in);
							Image scaledImage = img.getScaledInstance(valuew, valueh, Image.SCALE_SMOOTH);
							BufferedImage SubImgage = img.getSubimage(valuex, valuey, valuew, valueh);
							Graphics2D drawer = SubImgage.createGraphics();
							drawer.setComposite(AlphaComposite.Src);
							drawer.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
									RenderingHints.VALUE_INTERPOLATION_BILINEAR);
							drawer.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
							drawer.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
							drawer.drawImage(scaledImage, valuew, valueh, null);
							drawer.dispose();
							ByteArrayOutputStream buffer = new ByteArrayOutputStream();
							ImageIO.write(SubImgage, "jpg", buffer);
							bytes = buffer.toByteArray();
						} catch (IOException e) {
							// throw new ApplicationException("IOException in
							// scale");
						}

						/********* Today End **********/

						System.out.println("*********************Path" + path);

						BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
								new FileOutputStream(uploadfile));
						bufferedOutputStream.write(bytes);
						bufferedOutputStream.close();
						File f = new File(path);
						File files[] = f.listFiles();

						for (int j = 0; j < files.length; j++) {
							if (files[j].isFile()) {
								System.out.println("File " + files[j].getName() + " " + files[j].length());
							}
						}

						// image = request.getScheme() + "://" +
						// request.getServerName() + ":" +
						// request.getServerPort()+"/rmbv/resources/admin/images/news/"+file[i].getOriginalFilename();
						image = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
								+ "/resources/admin/images/news/" + file[i].getOriginalFilename();

						news = new News(newstitle, newssubtitle, beneficiarytypeid, beneficiaryno, newsdate, image,
								newsimagetitle, newsdescription, rotaryyearid, project, s, 1, IpAddress);
						newsdao.addNews(news);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}

	@RequestMapping(value = "/editNews", method = RequestMethod.POST)
	public String editNews(@RequestParam(value = "file", required = false) MultipartFile[] file,
			HttpServletRequest request, HttpSession session, int id, String newstitle, String newssubtitle,
			int beneficiarytypeid, String beneficiaryno, String newsdate, String newsimagetitle, String newsdescription,
			int rotaryyearid, int projectid, int valuex, int valuey, int valuew, int valueh) {
		logger.info("********** INSIDE EDIT NEWS **********");

		String image = null;
		try {
			for (int i = 0; i < file.length; i++) {
				if (file[i].getOriginalFilename() != null && file[i].getOriginalFilename() != "") {
					try {
						byte[] bytes = file[i].getBytes();

						File dir = new File(
								request.getRealPath("") + "/resources/admin/images/" + File.separator + "news");
						if (!dir.exists())
							dir.mkdirs();
						String path = request.getRealPath("/resources/admin/images/news/");
						File uploadfile = new File(path + File.separator + file[i].getOriginalFilename());

						ByteArrayInputStream in = new ByteArrayInputStream(bytes);
						try {
							BufferedImage img = ImageIO.read(in);
							Image scaledImage = img.getScaledInstance(valuew, valueh, Image.SCALE_SMOOTH);
							BufferedImage SubImgage = img.getSubimage(valuex, valuey, valuew, valueh);
							Graphics2D drawer = SubImgage.createGraphics();
							drawer.setComposite(AlphaComposite.Src);
							drawer.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
									RenderingHints.VALUE_INTERPOLATION_BILINEAR);
							drawer.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
							drawer.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
							drawer.drawImage(scaledImage, valuew, valueh, null);
							drawer.dispose();
							ByteArrayOutputStream buffer = new ByteArrayOutputStream();
							ImageIO.write(SubImgage, "jpg", buffer);
							bytes = buffer.toByteArray();
						} catch (IOException e) {
							// throw new ApplicationException("IOException in
							// scale");
						}

						/********* Today End **********/

						System.out.println("*********************Path" + path);

						BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
								new FileOutputStream(uploadfile));
						bufferedOutputStream.write(bytes);
						bufferedOutputStream.close();
						File f = new File(path);
						File files[] = f.listFiles();

						for (int j = 0; j < files.length; j++) {
							if (files[j].isFile()) {
								System.out.println("File " + files[j].getName() + " " + files[j].length());
							}
						}

						// image = request.getScheme() + "://" +
						// request.getServerName() + ":" +
						// request.getServerPort()+"/rmbv/resources/admin/images/news/"+file[i].getOriginalFilename();
						image = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
								+ "/resources/admin/images/news/" + file[i].getOriginalFilename();

						news = new News(newstitle, newssubtitle, beneficiarytypeid, beneficiaryno, newsdate, image,
								newsimagetitle, newsdescription, rotaryyearid, projectid, id);
						newsdao.editNews(news);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}

	@RequestMapping(value = "/editNewsWithoutImage", method = RequestMethod.POST)
	public String editNewsWithoutImage(HttpServletRequest request, HttpSession session, int id, String newstitle,
			String newssubtitle, int beneficiarytypeid, String beneficiaryno, String newsdate, String newsimagetitle,
			String newsdescription, int rotaryyearid, int projectid) {
		logger.info("********** INSIDE EDIT WITHOUT IMAGE NEWS **********");

		try {
			news = new News(newstitle, newssubtitle, beneficiarytypeid, beneficiaryno, newsdate, newsimagetitle,
					newsdescription, rotaryyearid, projectid, id);
			newsdao.editNewsWithoutImage(news);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}

	@RequestMapping(value = "/deleteNews", method = RequestMethod.DELETE)
	public void delete(int id) {
		logger.info("********** INSIDE DELETE NEWS ********** ");

		newsdao.deleteNews(id);
	}

	@RequestMapping(value = "/getNewsDetailById", method = RequestMethod.GET, produces = "application/json")
	public News getNewsDetailById(int id, HttpServletRequest request) {
		logger.info("********** INSIDE GET NEWS DETAIL BY ID **********");

		News news = newsdao.getNewsDetailById(id);

		return news;
	}

	@RequestMapping(value = "/getNewsDetailByRotaryYear", method = RequestMethod.GET, produces = "application/json")
	public List<News> getNewsDetailByRotaryYear(int rotaryyearid, HttpServletRequest request) {
		logger.info("***** INSIDE GET NEWS OF CURRENT YEAR *****");
		List<News> News = newsdao.getNewsDetailByRotaryYear(rotaryyearid);
		return News;
	}
}