package com.ui.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ui.dao.ReferenceDAO;
import com.ui.model.Reference;

public class ReferenceDAOImpl implements ReferenceDAO {
	
	Connection conn = null;
	private DataSource datasource;
	private static final Logger logger = LoggerFactory.getLogger(ReferenceDAOImpl.class);
	
	public void setDataSource(DataSource datasource)
	{
		this.datasource = datasource;
	}
	
	@Override
	public void addReference(Reference r)
	{
		logger.info("Inside Add Reference Impl");
		
		String sql = "insert into reference(reference_members_id, reference_first_name,reference_last_name,reference_email,reference_mobile_number,reference_address1,reference_address2,reference_state_id,reference_city_name,reference_pincode,reference_company_name,reference_occupation,member_id,status,created_by,ip_address) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		try
		{
			conn=datasource.getConnection();
		
			PreparedStatement ps= conn.prepareStatement(sql);

			ps.setInt(1, r.getReferenceMembersId());
			ps.setString(2,r.getReferenceFirstName());
			ps.setString(3, r.getReferenceLastName());
			ps.setString(4,r.getReferenceEmail());
			ps.setString(5,r.getReferenceMobileNumber());
			ps.setString(6,r.getReferenceAddress1());
			ps.setString(7,r.getReferenceAddress2());
			ps.setInt(8,r.getReferenceStateId());
			ps.setString(9,r.getReferenceCityName());
			ps.setString(10,r.getReferencePincode());
			ps.setString(11,r.getReferenceCompanyName());
			ps.setString(12,r.getReferenceOccupation());
			ps.setInt(13,r.getMemberId());			
			ps.setString(14,r.getStatus());
			ps.setInt(15,r.getCreatedBy());		
			ps.setString(16,r.getIpAddress());
			
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
	public List<Reference> getReference(int memberid)
	{
		List<Reference> Reference = new ArrayList<Reference>();
		
		String status = "y";
		
		String sql = "select reference_id, reference_members_id, reference_first_name, reference_last_name, reference_email, reference_mobile_number, reference_address1, reference_address2, reference_state_id, reference_city_name, reference_pincode, reference_company_name, reference_occupation, member_id from reference where member_id=? and reference_state_id != 0 and status = ?";
		String sql1 = "select r.reference_id, r.reference_members_id, m.member_first_name, m.member_last_name, m.member_email, m.member_mobile_number, m.member_address1, m.member_address2, m.member_state_id, m.member_city_name, m.member_pincode, m.member_company_name, m.member_occupation, r.member_id from reference r, members m where r.reference_members_id=m.member_id and r.member_id=? and reference_state_id = 0 and r.status = ?";
		String sql2 = "select reference_id, reference_members_id, reference_first_name, reference_last_name, reference_email, reference_mobile_number, reference_address1, reference_address2, reference_state_id, reference_city_name, reference_pincode, reference_company_name, reference_occupation, member_id from reference where member_id=? and reference_state_id = 0 and reference_members_id = 0 and status = ?";
		
		Connection conn = null;
		
		try
		{
			conn = datasource.getConnection();
			
			PreparedStatement ps =conn.prepareStatement(sql);
			PreparedStatement ps1 =conn.prepareStatement(sql1);
			PreparedStatement ps2 =conn.prepareStatement(sql2);
			
			ps.setInt(1, memberid);
			ps.setString(2,status);
			ps1.setInt(1, memberid);
			ps1.setString(2,status);
			ps2.setInt(1, memberid);
			ps2.setString(2,status);
			
			Reference reference = null;
			
			ResultSet rs = ps.executeQuery();
			ResultSet rs1 = ps1.executeQuery();
			ResultSet rs2 = ps2.executeQuery();
			
			while (rs.next())
			{
				reference = new Reference(
						rs.getInt("reference_id"),
						rs.getInt("reference_members_id"),
						rs.getString("reference_first_name"),
						rs.getString("reference_last_name"),
						rs.getString("reference_email"),
						rs.getString("reference_mobile_number"),
						rs.getString("reference_address1"),
						rs.getString("reference_address2"),
						rs.getInt("reference_state_id"),
						rs.getString("reference_city_name"),
						rs.getString("reference_pincode"),
						rs.getString("reference_company_name"),
						rs.getString("reference_occupation"),
						rs.getInt("member_id")
				);
				Reference.add(reference);
			}
			while (rs1.next())
			{
				reference = new Reference(
						rs1.getInt("reference_id"),
						rs1.getInt("reference_members_id"),
						rs1.getString("member_first_name"),
						rs1.getString("member_last_name"),
						rs1.getString("member_email"),
						rs1.getString("member_mobile_number"),
						rs1.getString("member_address1"),
						rs1.getString("member_address2"),
						rs1.getInt("member_state_id"),
						rs1.getString("member_city_name"),
						rs1.getString("member_pincode"),
						rs1.getString("member_company_name"),
						rs1.getString("member_occupation"),
						rs1.getInt("member_id")
				);
				Reference.add(reference);
			}
			while (rs2.next())
			{
				reference = new Reference(
						rs2.getInt("reference_id"),
						rs2.getInt("reference_members_id"),
						rs2.getString("reference_first_name"),
						rs2.getString("reference_last_name"),
						rs2.getString("reference_email"),
						rs2.getString("reference_mobile_number"),
						rs2.getString("reference_address1"),
						rs2.getString("reference_address2"),
						rs2.getInt("reference_state_id"),
						rs2.getString("reference_city_name"),
						rs2.getString("reference_pincode"),
						rs2.getString("reference_company_name"),
						rs2.getString("reference_occupation"),
						rs2.getInt("member_id")
				);
				Reference.add(reference);
			}
			ps.close();
			rs.close();
			ps1.close();
			rs1.close();
			ps2.close();
			rs2.close();
		} catch (SQLException e) {
		}
		finally
		{
			if(conn!= null)
			{
				try
				{
					conn.close();
				}
				catch (SQLException e)
				{
				}
			}
		}
		return Reference;
	}
	
	@Override
	public void deleteReference(int referenceid)
	{
		logger.info("Inside Delete Reference Impl");
		
		String sql1 = "update reference set status = 'n' where reference_id =?";
		
		Connection conn = null;
		try
		{
			conn = datasource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql1);
			ps.setInt(1, referenceid);
			
			ps.executeUpdate();
		}
		catch (SQLException e)
		{
        }
		finally
		{
            if (conn != null)
            {
            	try
            	{
            		conn.close();
                }
            	catch (SQLException e) {
            	}
            }
        }
	}


	@Override
	public void editReference(Reference r)
	{
		logger.info("Inside Edit Reference Impl");

		String sql1 = "update reference set reference_members_id=?, reference_first_name =?, reference_last_name =?, reference_email =?, reference_mobile_number =?, reference_address1=?, reference_address2=?, reference_state_id=?, reference_city_name=?, reference_pincode=?, reference_company_name=?, reference_occupation=?, created_by=?, ip_address=? where reference_id =?";
	
		Connection conn = null;
		try
		{
			conn = datasource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql1);
			
			ps.setInt(1, r.getReferenceMembersId());
			ps.setString(2,r.getReferenceFirstName());
			ps.setString(3,r.getReferenceLastName());
			ps.setString(4,r.getReferenceEmail());
			ps.setString(5,r.getReferenceMobileNumber());
			ps.setString(6,r.getReferenceAddress1());
			ps.setString(7,r.getReferenceAddress2());
			ps.setInt(8,r.getReferenceStateId());
			ps.setString(9,r.getReferenceCityName());
			ps.setString(10,r.getReferencePincode());
			ps.setString(11,r.getReferenceCompanyName());
			ps.setString(12,r.getReferenceOccupation());
			ps.setInt(13,r.getCreatedBy());			
			ps.setString(14,r.getIpAddress());
			ps.setInt(15,r.getReferenceId());
			
			ps.executeUpdate();
		}
		catch (SQLException e)
		{
        }
		finally
		{
            if (conn != null)
            {
            	try
            	{
            		conn.close();
                }
            	catch (SQLException e){
            	}
            }
        }
	}

	
	
}


