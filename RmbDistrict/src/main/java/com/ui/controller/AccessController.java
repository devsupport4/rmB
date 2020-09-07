package com.ui.controller;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ui.dao.AccessControlDAO;
import com.ui.model.User;

@RestController
public class AccessController {
  
  @Autowired
  AccessControlDAO accessControlDao;
  User user;
  
  private static final Logger logger = LoggerFactory.getLogger(AccessController.class);
  
  
  @RequestMapping(value = "/setRoles", method = RequestMethod.POST)
  public String setUserRoles(HttpServletRequest request, HttpSession session, int roleid, String userName,
          String acesslist, String roleName) {
      logger.info("********** INSIDE SET ROLES **********");

      int id = 0;
      String IpAddress = request.getHeader("X-FORWARDED-FOR");
      if (IpAddress == null) {
          IpAddress = request.getRemoteAddr();
      }
      
      String firstName= "";
      if(userName.split("\\w+").length>1){
         firstName = userName.substring(0, userName.lastIndexOf(' '));
      }
       else{
         firstName = userName;
      }
      
      roleName = roleName.replaceAll("\\s+","");

      String username = firstName+roleName+getSaltString(3);
      user = new User();
      user.setMemberName(userName);
      user.setCreatedBy(id);
      user.setStatus("y");
      user.setUserRoleId(roleid);
      user.setPassword(getSaltString(10));
      user.setUserName(username);
      
      
      System.out.println("username="+user.getUserName()+" password="+user.getPassword());
      String result = accessControlDao.setUserRoles(user);

      return "";
  }
  
  protected String getSaltString(int leng) {
    String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
    StringBuilder salt = new StringBuilder();
    Random rnd = new Random();
    while (salt.length() < leng) { // length of the random string.
        int index = (int) (rnd.nextFloat() * SALTCHARS.length());
        salt.append(SALTCHARS.charAt(index));
    }
    String saltStr = salt.toString();
    return saltStr;

}

}
