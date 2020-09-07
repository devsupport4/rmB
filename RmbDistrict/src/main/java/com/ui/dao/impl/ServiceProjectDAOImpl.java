package com.ui.dao.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ui.dao.ServiceProjectDAO;
import com.ui.model.ServiceProject;
import com.ui.model.ServiceProjectBeneficiary;
import com.ui.model.ServiceProjectImage;

public class ServiceProjectDAOImpl implements ServiceProjectDAO{
	
	@Autowired	
	private DataSource dataSource;	
	public void setDataSource(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}
	
	private static final Logger logger = LoggerFactory.getLogger(ServiceProjectDAOImpl.class);
	
	public List<ServiceProject> getAllServiceProject()
	{
		logger.info("********** INSIDE SERVICE PROJECT IMPL **********");		
		List<ServiceProject> ServiceProject = new ArrayList<ServiceProject>();		
		String s = "y";
		
		String sql = "select service_project_id, service_project_title, service_project_subtitle, service_project_date, service_sequence, service_project_description, status, created_by, created_date, ip_address from service_project where status=?";		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);

			ServiceProject serviceproject = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				try {
						serviceproject = new ServiceProject(              
							rs.getInt("service_project_id"),
							rs.getString("service_project_title"),
							rs.getString("service_project_subtitle"),
							rs.getString("service_project_date"),
							rs.getInt("service_sequence"),
							URLDecoder.decode(rs.getString("service_project_description"),"UTF-8"),
					        rs.getString("status"),
					        rs.getInt("created_by"),
					        rs.getString("created_date"),
					        rs.getString("ip_address")
					       
					);
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				ServiceProject.add(serviceproject);
           }			
           rs.close();
           ps.close();
          
           return ServiceProject;
        }
		catch (SQLException e)
		{
			throw new RuntimeException(e);
        }
		finally
		{
            if (conn != null)
            {
            	try
            	{
            		conn.close();
                }
            	catch (SQLException e) {}
            }
        }
	}
	
	public List<ServiceProject> getAllServiceProjectWithImage()
	{
		logger.info("********** INSIDE SERVICE PROJECT WITH IMAGE IMPL **********");		
		List<ServiceProject> ServiceProject = new ArrayList<ServiceProject>();		
		String s = "y";
		
		String sql = "select sp.service_project_id, sp.service_project_title, sp.service_project_subtitle, sp.service_project_date, sp.service_sequence, sp.service_project_description, sp.status, sp.created_by, sp.created_date, sp.ip_address, spi.image from service_project sp, service_project_image spi where sp.status=? and spi.service_project_id = sp.service_project_id and spi.sequence = 1";		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);

			ServiceProject serviceproject = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				try {
						serviceproject = new ServiceProject(              
							rs.getInt("service_project_id"),
							rs.getString("service_project_title"),
							rs.getString("service_project_subtitle"),
							rs.getString("service_project_date"),
							rs.getInt("service_sequence"),
							URLDecoder.decode(rs.getString("service_project_description"),"UTF-8"),
					        rs.getString("status"),
					        rs.getInt("created_by"),
					        rs.getString("created_date"),
					        rs.getString("ip_address"),
					        rs.getString("image")
					);
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				ServiceProject.add(serviceproject);
           }			
           rs.close();
           ps.close();
          
           return ServiceProject;
        }
		catch (SQLException e)
		{
			throw new RuntimeException(e);
        }
		finally
		{
            if (conn != null)
            {
            	try
            	{
            		conn.close();
                }
            	catch (SQLException e) {}
            }
        }
	}
	
	public List<ServiceProjectImage> getAllServiceProjectImages()
	{
		logger.info("********** INSIDE GET ALL SERVICE PROJECT IMAGES IMPL **********");		
		List<ServiceProjectImage> ServiceProjectImage = new ArrayList<ServiceProjectImage>();	
		
		String sql = "select service_project_image_id, service_project_id, sequence, image_title, image from service_project_image";		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ServiceProjectImage serviceprojectimage = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				serviceprojectimage = new ServiceProjectImage(              
					rs.getInt("service_project_image_id"),
					rs.getInt("service_project_id"),
					rs.getString("sequence"),
					rs.getString("image_title"),														
				    rs.getString("image")       
			);
				
				ServiceProjectImage.add(serviceprojectimage);
           }			
           rs.close();
           ps.close();
          
           return ServiceProjectImage;
        }
		catch (SQLException e)
		{
			throw new RuntimeException(e);
        }
		finally
		{
            if (conn != null)
            {
            	try
            	{
            		conn.close();
                }
            	catch (SQLException e) {}
            }
        }
	}


	
	public void addServiceProject(ServiceProject sp)
	{
		logger.info("********** INSIDE ADD SERVICE PROJECT IMPL ***********");
		
		String sql = "insert into service_project(service_project_title,service_project_subtitle,service_project_date,service_sequence,service_project_description,status,created_by,ip_address) values (?,?,?,?,?,?,?,?)";
		
		Connection conn = null;
		
		try
		{
			conn = dataSource.getConnection();
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, sp.getServiceProjectTitle());
			ps.setString(2, sp.getServiceProjectSubtitle());
			ps.setString(3, sp.getServiceProjectDate());
			ps.setInt(4, sp.getServiceSequence());
			ps.setString(5, URLEncoder.encode(sp.getServiceProjectDescription(),"UTF-8"));
			ps.setString(6, sp.getStatus());
			ps.setInt(7, sp.getCreatedBy());			
			ps.setString(8, sp.getIpAddress());
			
			ps.executeUpdate();
		}
		
		catch (SQLException e)
		{
			throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
            if (conn != null)
            {
            	try
            	{
            		conn.close();
                }
            	catch (SQLException e) {}
            }
        }
	}
	
	public void deleteServiceProject(int id)
	{
		logger.info("********** INSIDE DELETE SERVICE PROJECT ***********");
		String status="n";
		String sql = "update service_project set status=? where service_project_id=?";
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, status);
			ps.setInt(2, id);
			
			ps.executeUpdate();
		}
		
		catch (SQLException e)
		{
			throw new RuntimeException(e);
        }
		finally
		{
            if (conn != null)
            {
            	try
            	{
            		conn.close();
                }
            	catch (SQLException e) {}
            }
        }
	}
	
	public void editServiceProject(ServiceProject sp)
	{		
		logger.info("********** INSIDE EDIT SERVICE PROJECT IMPL **********");
		String sql = "update service_project set service_project_title=?, service_project_subtitle=?, service_project_date=?, service_sequence=?, service_project_description=?, status=?, created_by=?, ip_address=?  where service_project_id=?";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, sp.getServiceProjectTitle());
			ps.setString(2, sp.getServiceProjectSubtitle());
			ps.setString(3, sp.getServiceProjectDate());
			ps.setInt(4, sp.getServiceSequence());
			ps.setString(5, URLEncoder.encode(sp.getServiceProjectDescription(),"UTF-8"));
			ps.setString(6, sp.getStatus());
			ps.setInt(7, sp.getCreatedBy());			
			ps.setString(8, sp.getIpAddress());
			ps.setInt(9, sp.getServiceProjectId());
			
			ps.executeUpdate();
		}
		
		catch (SQLException e)
		{
			throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
            if (conn != null)
            {
            	try
            	{
            		conn.close();
                }
            	catch (SQLException e1) {}
            }
        }
	}
	
	public int getLastServiceProjectId()
	{
		String sql = "select service_project_id from service_project order by service_project_id desc limit 0,1";		
		int id=0;
		Connection conn = null;
		logger.info("********** INSIDE GET LAST SERVICE PROJECT ID IMPL **********");
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				id= rs.getInt("service_project_id");
			}
            rs.close();
            ps.close();
         return id;
        }
		catch (SQLException e)
		{
			throw new RuntimeException(e);
        }
		finally
		{
            if (conn != null)
            {
            	try
            	{
            		conn.close();
                }
            	catch (SQLException e) {}
            }
        }
	}
	
	public void addServiceProjectImages(ServiceProjectImage spi)
	{
		logger.info("********** INSIDE ADD SERVICE PROJECT IMAGE IMPL **********");
		
		String sql = "insert into service_project_image (service_project_id,sequence,image_title,image,created_by,ip_address) values (?,?,?,?,?,?)";		
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, spi.getServiceProjectId());
			ps.setString(2, spi.getSequence());
			ps.setString(3, spi.getImageTitle());
			ps.setString(4, spi.getImage());
			ps.setInt(5, spi.getCreatedBy());
			ps.setString(6, spi.getIpAddress());
			
			ps.executeUpdate();
		}
		
		catch (SQLException e)
		{
			throw new RuntimeException(e);
        }
		finally
		{
            if (conn != null)
            {
            	try
            	{
            		conn.close();
                }
            	catch (SQLException e) {}
            }
        }
	}
	
	@Override
	public List<ServiceProjectImage> getRelatedServiceProjectImages(int id)
	{
		logger.info("********** INSIDE GET RELATED SERIVCE PROJECT IMAGE IMPL **********");
		List<ServiceProjectImage> ServiceProjectImage = new ArrayList<ServiceProjectImage>();			
		String sql = "select spi.sequence, spi.image_title, spi.image from service_project sp, service_project_image spi where sp.service_project_id = spi.service_project_id and sp.service_project_id=? order by sequence";
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, id);

			ServiceProjectImage serviceprojectimages = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				serviceprojectimages = new ServiceProjectImage(
						rs.getString("sequence"),
		                rs.getString("image_title"),
		                rs.getString("image")		                
		                );
				
				ServiceProjectImage.add(serviceprojectimages);
           }
           rs.close();
           ps.close();
          
           return ServiceProjectImage;
        }
		catch (SQLException e)
		{
			throw new RuntimeException(e);
        }
		finally
		{
            if (conn != null)
            {
            	try
            	{
            		conn.close();
                }
            	catch (SQLException e) {}
            }
        }
	}
	
	public void deleteSelectedServiceProjectImages(int id)
	{
		logger.info("********** INSIDE DELETE SELETCED SERVICE PROJECT IMAGES IMPL ***********");		
		String sql = "delete from service_project_image where service_project_id=?";
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
						
			ps.setInt(1, id);
			
			ps.executeUpdate();
		}
		
		catch (SQLException e)
		{
			throw new RuntimeException(e);
        }
		finally
		{
            if (conn != null)
            {
            	try
            	{
            		conn.close();
                }
            	catch (SQLException e) {}
            }
        }
	}
	
	public int getLastSequenceNo()
	{
		String sql = "select service_sequence from service_project order by service_sequence desc limit 0,1";		
		int id=0;
		Connection conn = null;
		logger.info("********** INSIDE GET LAST SEQUENCE NUMBER OF SERVICE PROJECT IMPL **********");
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				id= rs.getInt("service_sequence");
			}
            rs.close();
            ps.close();
         return id;
        }
		catch (SQLException e)
		{
			throw new RuntimeException(e);
        }
		finally
		{
            if (conn != null)
            {
            	try
            	{
            		conn.close();
                }
            	catch (SQLException e) {}
            }
        }
	}
	
	public ServiceProject getServiceProjectDetailById(int id) throws UnsupportedEncodingException
	{
		logger.info("********** INSIDE GET SERVICE PROJECT DETAIL BY ID IMPL **********");		
		//String s = "y";
		String sql = "select service_project_id, service_project_title, service_project_subtitle, service_project_date, service_sequence, service_project_description, status, created_by, created_date, ip_address from service_project where service_project_id=?";
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, id);
			
			ServiceProject serviceproject = null;			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				serviceproject = new ServiceProject(              
						rs.getInt("service_project_id"),
						rs.getString("service_project_title"),
						rs.getString("service_project_subtitle"),
						rs.getString("service_project_date"),
						rs.getInt("service_sequence"),
						URLDecoder.decode(rs.getString("service_project_description"),"UTF-8"),
				        rs.getString("status"),
				        rs.getInt("created_by"),
				        rs.getString("created_date"),
				        rs.getString("ip_address")
		                );		
           }
           rs.close();
           ps.close();
          
           return serviceproject;
        }
		catch (SQLException e)
		{
			throw new RuntimeException(e);
        }
		finally
		{
            if (conn != null)
            {
            	try
            	{
            		conn.close();
                }
            	catch (SQLException e) {}
            }
        }
	}
	
	@Override
	public List<ServiceProjectBeneficiary> getServiceProjectBeneficiaryByServiceProjectId(int serviceprojectid)
	{
		logger.info("********** INSIDE SERVICE PROJECT BENEFICIARY BY SERVICE PROJECT ID IMPL **********");		
		List<ServiceProjectBeneficiary> ServiceProjectBeneficiary = new ArrayList<ServiceProjectBeneficiary>();
		String s = "y";
		
		String sql = "SELECT spb.service_project_beneficiary_id, spb.service_project_id, spb.beneficiary_type_id, spb.beneficiary_id, spb.donor_id, spb.start_date, spb.end_date, spb.status, spb.created_by, spb.created_date, spb.ip_address, sp.service_project_title, bt.beneficiary_type_title, m1.member_first_name as beneficiary_first_name, m1.member_middle_name as beneficiary_middle_name, m1.member_last_name as beneficiary_last_name, m2.member_first_name as donor_first_name, m2.member_middle_name as donor_middle_name, m2.member_last_name as donor_last_name FROM service_project_beneficiary spb LEFT JOIN service_project sp on spb.service_project_id = sp.service_project_id LEFT JOIN beneficiary_type bt on spb.beneficiary_type_id = bt.beneficiary_type_id LEFT JOIN members m1 on spb.beneficiary_id = m1.member_id LEFT JOIN members m2 on spb.donor_id=m2.member_id where spb.service_project_id=? and spb.status=?";
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, serviceprojectid);
			ps.setString(2, s);

			ServiceProjectBeneficiary serviceprojectbeneficiary = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
					serviceprojectbeneficiary = new ServiceProjectBeneficiary(           
						rs.getInt("service_project_beneficiary_id"),
						rs.getInt("service_project_id"),
						rs.getInt("beneficiary_type_id"),
						rs.getInt("beneficiary_id"),
						rs.getInt("donor_id"),
						rs.getString("start_date"),
						rs.getString("end_date"),
					    rs.getString("status"),
					    rs.getInt("created_by"),
					    rs.getString("created_date"),
					    rs.getString("ip_address"),
					    rs.getString("service_project_title"),
					    rs.getString("beneficiary_type_title"),
					    rs.getString("beneficiary_first_name"),
					    rs.getString("beneficiary_middle_name"),
					    rs.getString("beneficiary_last_name"),
					    rs.getString("donor_first_name"),
					    rs.getString("donor_middle_name"),
					    rs.getString("donor_last_name")
					    );  				
				ServiceProjectBeneficiary.add(serviceprojectbeneficiary);
           }
           rs.close();
           ps.close();
          
           return ServiceProjectBeneficiary;
        }
		catch (SQLException e)
		{
			throw new RuntimeException(e);
        }
		finally
		{
            if (conn != null)
            {
            	try
            	{
            		conn.close();
                }
            	catch (SQLException e) {}
            }
        }
	}
	
	public void addServiceProjectBeneficiary(ServiceProjectBeneficiary sp)
	{
		logger.info("********** INSIDE ADD SERVICE PROJECT BENEFICIARY IMPL ***********");
		
		String sql = "insert into service_project_beneficiary(service_project_id, beneficiary_type_id, beneficiary_id, donor_id, start_date, end_date, status, created_by, ip_address) values (?,?,?,?,?,?,?,?,?)";
		
		Connection conn = null;
		
		try
		{
			conn = dataSource.getConnection();
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, sp.getServiceProjectId());
			ps.setInt(2, sp.getBeneficiaryTypeId());
			ps.setInt(3, sp.getBeneficiaryId());
			ps.setInt(4, sp.getDonorId());
			ps.setString(5, sp.getStartDate());
			ps.setString(6, sp.getEndDate());
			ps.setString(7, "y");
			ps.setInt(8, sp.getCreatedBy());			
			ps.setString(9, sp.getIpAddress());
			
			ps.executeUpdate();
		}
		catch (SQLException e)
		{
			throw new RuntimeException(e);
        }finally
		{
            if (conn != null)
            {
            	try
            	{
            		conn.close();
                }
            	catch (SQLException e) {}
            }
        }
	}
	
	public void editServiceProjectBeneficiary(ServiceProjectBeneficiary sp)
	{
		logger.info("********** INSIDE EDIT SERVICE PROJECT BENEFICIARY IMPL ***********");
		
		String sql = "update service_project_beneficiary set service_project_id=?, beneficiary_type_id=?, beneficiary_id=?, donor_id=?, start_date=?, end_date=?, created_by=?, ip_address=? where service_project_beneficiary_id=?";
		
		Connection conn = null;
		
		try
		{
			conn = dataSource.getConnection();
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, sp.getServiceProjectId());
			ps.setInt(2, sp.getBeneficiaryTypeId());
			ps.setInt(3, sp.getBeneficiaryId());
			ps.setInt(4, sp.getDonorId());
			ps.setString(5, sp.getStartDate());
			ps.setString(6, sp.getEndDate());
			ps.setInt(7, sp.getCreatedBy());			
			ps.setString(8, sp.getIpAddress());
			ps.setInt(9, sp.getServiceProjectBeneficiaryId());
			
			ps.executeUpdate();
		}
		catch (SQLException e)
		{
			throw new RuntimeException(e);
        }finally
		{
            if (conn != null)
            {
            	try
            	{
            		conn.close();
                }
            	catch (SQLException e) {}
            }
        }
	}
	
	public void deleteServiceProjectBeneficiary(int id)
	{
		logger.info("********** INSIDE DELETE SERVICE PROJECT BENEFICIARY ***********");
		String status="n";
		String sql = "update service_project_beneficiary set status=? where service_project_beneficiary_id=?";
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, status);
			ps.setInt(2, id);
			
			ps.executeUpdate();
		}
		catch (SQLException e)
		{
			throw new RuntimeException(e);
        }
		finally
		{
            if (conn != null)
            {
            	try
            	{
            		conn.close();
                }
            	catch (SQLException e) {}
            }
        }
	}


}
