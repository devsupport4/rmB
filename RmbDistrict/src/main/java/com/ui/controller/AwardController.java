package com.ui.controller;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
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

import com.ui.dao.AwardDAO;
import com.ui.model.AwardImage;
import com.ui.model.Awards;

@RestController
public class AwardController {
	@Autowired	
	AwardDAO awarddao;
	Awards awards;
	AwardImage awardimage;	
	
	private static final Logger logger = LoggerFactory.getLogger(AwardController.class);
	
	@RequestMapping(value="/Award", method= RequestMethod.GET, produces = "application/json")
	public List<Awards> Award(HttpServletRequest request)
	{
		logger.info("********** GET ALL AWARDS **********");		
		List<Awards> Awards = awarddao.getAllAwards();		
		return Awards;	
	}
	
	@RequestMapping(value="/AwardImage", method= RequestMethod.GET, produces = "application/json")
	public List<AwardImage> AwardImage(int awardid, HttpServletRequest request)
	{
		logger.info("********** AWARD IMAGE BY AWARD BY **********");		
		List<AwardImage> AwardImage = awarddao.getAwardImage(awardid);		
		return AwardImage;
	}
	
	@RequestMapping(value="/getAwardDetailById", method= RequestMethod.GET, produces = "application/json")
	public Awards getAwardDetailById(int awardid, HttpServletRequest request)	{
		logger.info("********** GET AWARD DEATIL ID **********");		
		Awards Awards = awarddao.getAwardDetailById(awardid);		
		return Awards;	
	}
	
	@RequestMapping(value="/getAwardDetailByRotaryYearWithOneImage", method= RequestMethod.GET, produces = "application/json")
	public List<AwardImage> getAwardDetailByRotaryYearWithOneImage(int rotaryyearid, HttpServletRequest request)
	{
		logger.info("********** GET AWARD DETAIL BY ROTARY YEAR ID WITH ONE IMAGE **********");		
		List<AwardImage> AwardImage = awarddao.getAwardDetailByRotaryYearWithOneImage(rotaryyearid);		
		return AwardImage;
	}
	
	@RequestMapping(value="/addAward", method= RequestMethod.POST)
	public String addAward(int rotaryyearid, String title, String subtitle, String awarddate, String description, HttpServletRequest request, HttpSession session)
	{
		logger.info("***** INSIDE ADD AWARD CONTROLLER *****");
		
		//int id = Integer.parseInt(session.getAttribute("adminid").toString());
		
		String p = title.replace("$","&");
		String p1 = p.replace("~","#");
		String p2 = p1.replace("!","%");		
		
		String status = "y";
		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}
				
		Awards a = new Awards(p2, subtitle, description, awarddate, rotaryyearid, status, 1, IpAddress);
		awarddao.addAward(a);
				
		return "";
	}
	
	@RequestMapping(value="addAwardImage", method= RequestMethod.POST)
	public String addAwardImage(@RequestParam(value="image", required=false) MultipartFile[] image, String imagetitle[], String imagedescription[], HttpServletRequest request, HttpSession session) throws UnsupportedEncodingException
	{
		logger.info("***** INSIDE ADD AWARD IMAGE *****");	
		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}		
		int awardid = awarddao.getLastAwardId();
		String image1 = null;
		try
		{
			if (image != null && image.length > 0)
			{
				try
				{
					for(int i=0; i<image.length; i++)
					{
						byte[] bytes =  image[i].getBytes();
						
						File dir = new File(request.getRealPath("")+"/resources/admin/images/" + File.separator + "AwardPhoto");
		    			if (!dir.exists()) 
		    				dir.mkdirs();
		    			
		    			String path = request.getRealPath("/resources/admin/images/AwardPhoto/");
			            File uploadfile = new File(path+File.separator+image[i].getOriginalFilename());
			            
			            /********* Today Start **********/
			            
			            int height=416, width=833;
			            
			            ByteArrayInputStream in = new ByteArrayInputStream(bytes);
			        	try
			        	{
			        		BufferedImage img = ImageIO.read(in);
			        		
			        		int original_width = img.getWidth();
			        	    int original_height = img.getHeight();
			        	    int bound_width = 833;
			        	    int bound_height = 416;        	    
			        		
			        	    //bound_height = (int) (bound_width * original_height / original_width);
			        	    
			        		if (original_height/bound_height > original_width/bound_width) {
			        			bound_width = (int) (bound_height * original_width / original_height);
			                } else {
			                	bound_height = (int) (bound_width * original_height / original_width);
			                }
			        		
			        		Image scaledImage = img.getScaledInstance(bound_width, bound_height, Image.SCALE_SMOOTH);			        		
			        		
		        			BufferedImage imageBuff = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		        			
		        			Graphics2D drawer = imageBuff.createGraphics() ;
		        			drawer.setBackground(Color.WHITE);
		        			//Color c = Color.decode(color1);
		        			//drawer.setBackground(c);
		        			drawer.clearRect(0,0,width,height);
		        			
		        			imageBuff.getGraphics().drawImage(scaledImage, (width-bound_width)/2, (height-bound_height)/2, new Color(0,0,0), null);
			        		
			        		ByteArrayOutputStream buffer = new ByteArrayOutputStream();

			        		ImageIO.write(imageBuff, "jpg", buffer);

			        		bytes = buffer.toByteArray();
			        	}
			        	catch (IOException e)
			        	{
			        		//throw new ApplicationException("IOException in scale");
			        	}		            
			            /********* Today End **********/
			            
			            System.out.println("*********************Path"+path);         
			            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(uploadfile));
			            bufferedOutputStream.write(bytes);
			            bufferedOutputStream.close();			            
			            image1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+"/resources/admin/images/AwardPhoto/"+image[i].getOriginalFilename();
			            //image1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+"/resources/admin/images/AwardPhoto/"+image[i].getOriginalFilename();
			            //
			            
			            String t = imagetitle[i].toString().replace("$","&");
			    		String t1 = t.replace("~","#");
			    		String t2 = t1.replace("!","%"); 		
			            
			    		awardimage = new AwardImage(t2, imagedescription[i].toString(), image1, awardid, IpAddress);
			    		awarddao.addAwardImage(awardimage);
					}
				}
				catch(Exception e)
				{
					e.printStackTrace();
					//logger.error("Exception", e);
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			//logger.error("Exception", e);
		}
		return "";
	}
	
	@RequestMapping(value="deleteSelectedAwardImage", method= RequestMethod.DELETE)
	public void deleteSelectedAwardImage(int awardid)
	{
		logger.info("*********** DELETE SELECTED AWARD IMAGE **********");		
		awarddao.deleteSelectedAwardImage(awardid);
	}
	
	@RequestMapping(value="editAward", method= RequestMethod.PUT)
	public String editAward(int awardid, int rotaryyearid, String title, String subtitle, String awarddate, String description, HttpServletRequest request, HttpSession session) throws UnsupportedEncodingException
	{
		logger.info("********** INSIDE EDIT AWARD **********");
		
		String p = title.replace("$","&");
		String p1 = p.replace("~","#");
		String p2 = p1.replace("!","%");
		
		int id = Integer.parseInt(session.getAttribute("adminid").toString());
		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}
		
		Awards a = new Awards(awardid, p2, subtitle, description, awarddate, rotaryyearid, id, IpAddress);
		awarddao.editAward(a);
		
		return "";
	}
	
	@RequestMapping(value="editAwardImage", method= RequestMethod.POST)
	public String editAwardImage(@RequestParam(value="image", required=false) MultipartFile[] image, int awardid, String imagetitle[], String imagedescription[], HttpServletRequest request, HttpSession session)
	{
		logger.info("********** INSIDE EDIT AWARD IMAGE **********");			
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}		
		String image1 = null;
		try
		{
			if (image != null && image.length > 0)
			{
				try
				{
					for(int i=0; i<image.length; i++)
					{
						byte[] bytes =  image[i].getBytes();						
						File dir = new File(request.getRealPath("")+"/resources/admin/images/" + File.separator + "AwardPhoto");
		    			if (!dir.exists()) 
		    				dir.mkdirs();						
		    			String path = request.getRealPath("/resources/admin/images/AwardPhoto/");
			            File uploadfile = new File(path+File.separator+image[i].getOriginalFilename());			            
			           //********* Today Start **********//			            
			            int height=416, width=833;			            
			            ByteArrayInputStream in = new ByteArrayInputStream(bytes);
			        	try
			        	{
			        		BufferedImage img = ImageIO.read(in);			        		
			        		int original_width = img.getWidth();
			        	    int original_height = img.getHeight();
			        	    int bound_width = 833;
			        	    int bound_height = 416;
			        	    
			        	    //bound_height = (int) (bound_width * original_height / original_width);
			        		
			        		if (original_height/bound_height > original_width/bound_width) {
			        			bound_width = (int) (bound_height * original_width / original_height);
			                } else {
			                	bound_height = (int) (bound_width * original_height / original_width);
			                }
			        		
			        		Image scaledImage = img.getScaledInstance(bound_width, bound_height, Image.SCALE_SMOOTH);		        		
		        			BufferedImage imageBuff = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);		        			
		        			Graphics2D drawer = imageBuff.createGraphics() ;
		        			drawer.setBackground(Color.WHITE);		        			
		        			drawer.clearRect(0,0,width,height);		        			
		        			imageBuff.getGraphics().drawImage(scaledImage, (width-bound_width)/2, (height-bound_height)/2, new Color(0,0,0), null);
			        		
			        		ByteArrayOutputStream buffer = new ByteArrayOutputStream();

			        		ImageIO.write(imageBuff, "jpg", buffer);

			        		bytes = buffer.toByteArray();
			        	}
			        	catch (IOException e)
			        	{
			        		//throw new ApplicationException("IOException in scale");
			        	}      
			            //********* Today End **********//			            
			            System.out.println("*********************Path"+path);			            
			            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(uploadfile));
			            bufferedOutputStream.write(bytes);
			            bufferedOutputStream.close();
			            
			            //image1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+"/resources/admin/images/AwardPhoto/"+image[i].getOriginalFilename();
			            image1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+"/resources/admin/images/AwardPhoto/"+image[i].getOriginalFilename();
			            
			            awardimage = new AwardImage(imagetitle[i].toString(), imagedescription[i].toString(), image1, awardid, IpAddress);			    		
			            awarddao.addAwardImage(awardimage);
					}
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "";
	}
	
	@RequestMapping(value="/editAwardImageNew",method= RequestMethod.POST)
	public String editAwardImageNew(HttpServletRequest request, HttpSession session, int awardid, String imagetitle, String imagedescription, String image)
	{
		logger.info("********** INSIDE EDIT AWARD IMAGE NEW **********");		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}		
		awardimage = new AwardImage(imagetitle,imagedescription,image,awardid,IpAddress);
		awarddao.addAwardImage(awardimage);
		
		return "";
	}
	
	@RequestMapping(value="deleteAward", method= RequestMethod.DELETE)
	public void delete(int awardid)
	{
		logger.info("********** DELETE AWARD **********");
		awarddao.deleteAward(awardid);
	}
}
