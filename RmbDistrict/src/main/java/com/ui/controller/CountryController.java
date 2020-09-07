package com.ui.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

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

import com.ui.dao.CountryDAO;
import com.ui.model.ClubInfo;
import com.ui.model.Country;
import com.ui.model.State;

@RestController
public class CountryController {
	@Autowired
	CountryDAO countrydao;
	ClubInfo clubinfo;
	private static final Logger logger = LoggerFactory.getLogger(CountryController.class);

	@RequestMapping(value = "/getCountry", method = RequestMethod.GET, produces = "application/json")
	public List<Country> getCountry(HttpServletRequest request) {
		logger.info("Inside Country");

		List<Country> Country = countrydao.getAllCountry();

		return Country;
	}

	@RequestMapping(value = "/GetRelatedState", method = RequestMethod.GET, produces = "application/json")
	public List<State> GetRelatedState(int countryid) {
		logger.info("Inside Get Related State");
		List<State> s = countrydao.getRelatedState(countryid);
		return s;
	}

	@RequestMapping(value = "/getClubInfo", method = RequestMethod.GET, produces = "application/json")
	public ClubInfo getClubInfo(HttpServletRequest request) {
		logger.info("***** GET CLUB INFO *****");
		ClubInfo clubInfo = countrydao.getClubInfo();
		return clubInfo;
	}

	@RequestMapping(value = "/updateClubInfo", method = RequestMethod.POST)
	public String updateClubInfo(@RequestParam(value = "logo", required = false) MultipartFile logo, int id,
			String title, String shorttitle, String clublogo, String clubno, String districtno, String zoneno,
			String add1, String add2, String day, String time, String map, String personname, String contactemail,
			String contacttelephoneno, String contactmobileno, String contactaddress, String oldimage, HttpSession session,
			HttpServletRequest request) throws IOException {
		logger.info("*************************** Inside Update Club Info *********************************");

		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		if (ipAddress == null) {
			ipAddress = request.getRemoteAddr();
		}
		String logoimage = "";
		if(id ==0) {
			logoimage = "";
		} else {
			logoimage = oldimage;
		}
		
		try {
			if (logo.getOriginalFilename() != null && logo.getOriginalFilename() != "") {
				try {
					byte[] bytes = logo.getBytes();
					File dir = new File(
							request.getRealPath("") + "/resources/admin/images/" + File.separator + "ClubInfo");
					if (!dir.exists())
						dir.mkdirs();
					String path = request.getRealPath("/resources/admin/images/ClubInfo/");
					File uploadfile = new File(path + File.separator + logo.getOriginalFilename());
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
					logoimage = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
							+ "/resources/admin/images/ClubInfo/" + logo.getOriginalFilename();
					// logoimage = request.getScheme() + "://" +
					// request.getServerName() + ":" +
					// request.getServerPort()+"/rmbv/resources/admin/images/ClubInfo/"+logo.getOriginalFilename();

					
					clubinfo = new ClubInfo(id, title, shorttitle, logoimage, clubno, districtno, zoneno, add1, add2,
							day, time, map, personname, contactemail, contacttelephoneno, contactmobileno,
							contactaddress, 1, ipAddress);
					if(id!=0) {
						countrydao.updateClubInfo(clubinfo);
					} else {
						countrydao.addClubInfo(clubinfo);
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			/*clubinfo = new ClubInfo(id, title, shorttitle, clublogo, clubno, districtno, zoneno, add1, add2, day, time,
					map, personname, contactemail, contacttelephoneno, contactmobileno, contactaddress, 1, ipAddress);
			countrydao.updateClubInfo(clubinfo);*/
		}
		clubinfo = new ClubInfo(id, title, shorttitle, logoimage, clubno, districtno, zoneno, add1, add2,
				day, time, map, personname, contactemail, contacttelephoneno, contactmobileno,
				contactaddress, 1, ipAddress);
		if(id!=0) {
			countrydao.updateClubInfo(clubinfo);
		} else {
			countrydao.addClubInfo(clubinfo);
		}
		return "";
	}
}
