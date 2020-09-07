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

import com.ui.dao.MemberPostDAO;
import com.ui.model.MemberPost;
import com.ui.model.MemberPostComments;

@RestController
public class MemberPostController {
	@Autowired
	MemberPostDAO memberPostDao;
	
	MemberPost memberPost;
	MemberPostComments memberPostComments;
	private static final Logger logger = LoggerFactory.getLogger(MemberPostController.class);
	
	@RequestMapping(value="/getAllMemberPost", method= RequestMethod.GET, produces = "application/json")
	public List<MemberPost> getAllMemberPost(HttpServletRequest request) {
		logger.info("********** GET ALL MEMBER POST CONTROLLER **********");		
		List<MemberPost> MemberPost = memberPostDao.getAllMemberPost();		
		return MemberPost;
	}
	
	@RequestMapping(value="/getLastThreeMemberPost", method= RequestMethod.GET, produces = "application/json")
	public List<MemberPost> getLastThreeMemberPost(HttpServletRequest request) {
		logger.info("***** GET LAST MEMBER POST *****");		
		List<MemberPost> MemberPost = memberPostDao.getLastThreeMemberPost();		
		return MemberPost;
	}
	
	@RequestMapping(value="/addNewMemberPost", method=RequestMethod.POST)
	public String addNewMemberPost(String memberpost, HttpServletRequest request, HttpSession session){
		logger.info("********** ADD NEW MEMBER POST CONTROLLER **********");
		
		String status = "y";
		int id  = (Integer) session.getAttribute("loginid");		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}		
		memberPost = new MemberPost(memberpost,status,id,IpAddress);
		memberPostDao.addNewMemberPost(memberPost);
		
		return "Success";
	}
	
	@RequestMapping(value="/getPostCommentsById", method= RequestMethod.GET, produces = "application/json")
	public List<MemberPostComments> getPostCommentsById(int postid, HttpServletRequest request) {
		logger.info("********** GET POST ALL COMMENTS CONTROLLER **********");		
		List<MemberPostComments> MemberPostComments = memberPostDao.getPostCommentsById(postid);		
		return MemberPostComments;
	}
	
	@RequestMapping(value="/addComment", method=RequestMethod.POST)
	public String addComment(int postid, String postcomment, HttpServletRequest request, HttpSession session){
		logger.info("********** ADD NEW COMMENT CONTROLLER **********");
		
		String status = "y";
		int id  = (Integer) session.getAttribute("loginid");		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}		
		memberPostComments = new MemberPostComments(postcomment,postid,status,id,IpAddress);
		memberPostDao.addComment(memberPostComments);
		
		return "Success";
	}
}
