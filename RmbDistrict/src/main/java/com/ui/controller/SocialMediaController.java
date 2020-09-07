package com.ui.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ui.dao.SocialMediaDAO;
import com.ui.model.SocialMediaLink;

@RestController
public class SocialMediaController {

	private static final Logger logger = LoggerFactory.getLogger(SocialMediaController.class);
	@Autowired
	SocialMediaDAO socialmediadao;
	SocialMediaLink socialmedialink;
	
	@RequestMapping(value="/getSocialMediaLinks", method= RequestMethod.GET, produces = "application/json")
	public List<SocialMediaLink> SocialMediaLink(HttpServletRequest request)
	{
		logger.info("********** INSIDE GET ALL SOCIAL MEDIA LINKS **********");		
		List<SocialMediaLink> socialmedialink = socialmediadao.getAllSocialMediaLinks();		
		return socialmedialink;
	}
	
	@RequestMapping(value="/addSocialMediaLink",method= RequestMethod.POST)
	public String addSocialMediaLink(HttpServletRequest request, HttpSession session, String mediaplatformtitle, String medialink)
	{
		logger.info("********** INSIDE ADD SOCIAL MEDIA LINK **********");		
		
		String s="y";
		int createdby  = 1;
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}		
		socialmedialink = new SocialMediaLink(mediaplatformtitle,medialink,s,createdby,IpAddress);		
		socialmediadao.addSocialMediaLink(socialmedialink);	
	
		return "";
	}
	
	@RequestMapping(value="/editSocialMediaLink",method= RequestMethod.POST)
	public String editSocialMediaLink(HttpServletRequest request, HttpSession session, int id, String mediaplatformtitle, String medialink)
	{
		logger.info("********** INSIDE EDIT SOCIAL MEDIA LINK **********");	
		
		int createdby  = 1;
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}
		
		socialmedialink = new SocialMediaLink(mediaplatformtitle,medialink,createdby,IpAddress,id);		
		socialmediadao.editSocialMediaLink(socialmedialink);	
	
		return "";
	}
	
	@RequestMapping(value="/deleteSocialMediaLink",method= RequestMethod.DELETE)
	public void delete(int id)
	{
		logger.info("********** INSIDE DELETE SOCIAL MEDIA LINK ********** ");
		
		socialmediadao.deleteSocialMediaLink(id);
	}
}
