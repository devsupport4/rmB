package com.ui.dao.impl;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.ui.dao.AccessControlDAO;
import com.ui.model.User;

public class AccessControlDAOImpl implements AccessControlDAO {

  @Autowired
  
  private DataSource dataSource;
  
  public void setDataSource(DataSource dataSource)
  {
      this.dataSource = dataSource;
  }
  
  JdbcTemplate jdbcTemplate;
  
  private static final Logger logger = LoggerFactory.getLogger(AccessControlDAOImpl.class);
  
  @Override
  public String setUserRoles(User user) {
    // TODO Auto-generated method stub
    return null;
  }

}
