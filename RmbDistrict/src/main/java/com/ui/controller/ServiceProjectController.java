package com.ui.controller;

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
import java.net.URLDecoder;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ui.dao.ServiceProjectDAO;
import com.ui.model.ServiceProject;
import com.ui.model.ServiceProjectBeneficiary;
import com.ui.model.ServiceProjectImage;

@RestController
public class ServiceProjectController {

@Autowired
ServiceProjectDAO serviceprojectdao;
ServiceProject serviceproject;
ServiceProjectImage serviceprojectimages;
ServiceProjectBeneficiary serviceProjectBeneficiary;
	
	private static final Logger logger = LoggerFactory.getLogger(ServiceProjectController.class);
	
	@RequestMapping(value="/ServiceProject", method= RequestMethod.GET, produces = "application/json")
	public List<ServiceProject> ServiceProject(HttpServletRequest request, HttpServletResponse res)
	{
		logger.info("***** INSIDE ALL SERVICE PROJECT CONTROLLER *****");
		res.addHeader("Access-Control-Allow-Origin", "*");
		
		List<ServiceProject> serviceproject = serviceprojectdao.getAllServiceProject();
		res.setCharacterEncoding("UTF-8");
		
		return serviceproject;
	}
	
	@RequestMapping(value="/ServiceProjectWithImage", method= RequestMethod.GET, produces = "application/json")
	public List<ServiceProject> ServiceProjectWithImage(HttpServletRequest request, HttpServletResponse res)
	{
		logger.info("***** INSIDE ALL SERVICE PROJECT WITH IMAGE CONTROLLER *****");
		res.addHeader("Access-Control-Allow-Origin", "*");
		
		List<ServiceProject> serviceproject = serviceprojectdao.getAllServiceProjectWithImage();
		res.setCharacterEncoding("UTF-8");
		
		return serviceproject;
	}
	
	@RequestMapping(value="/getAllServiceProjectImages", method= RequestMethod.GET, produces = "application/json")
	public List<ServiceProjectImage> getAllServiceProjectImages(HttpServletRequest request, HttpServletResponse res)
	{
		logger.info("***** INSIDE GET ALL SERVICE PROJECT IMAGES CONTROLLER *****");
		res.addHeader("Access-Control-Allow-Origin", "*");
		
		List<ServiceProjectImage> serviceprojectimage = serviceprojectdao.getAllServiceProjectImages();
		res.setCharacterEncoding("UTF-8");
		
		return serviceprojectimage;
	}
	
	@RequestMapping(value="/addServiceProject",method= RequestMethod.POST)	
	public String addServiceProject(@RequestParam(value="desc", required=false) String desc, HttpServletRequest request, HttpSession session, String projecttitle, String projectsubtitle, String projectdate, int projectsequence)
	{
		logger.info("***** INSIDE SERVICE PROJECT CONTROLLER *****");
		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}		
		String s="y";
		if(projectdate == "")
		{
			projectdate = null;
		}
		
		try
		{
			serviceproject = new ServiceProject(projecttitle, projectsubtitle, projectdate, projectsequence, URLDecoder.decode(desc,"UTF-8"),s,1,IpAddress);
		}
		catch (UnsupportedEncodingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		serviceprojectdao.addServiceProject(serviceproject);
		
		return "";
	}
	
	@RequestMapping(value="/editServiceProject",method= RequestMethod.POST)	
	public String editServiceProject(@RequestParam(value="desc", required=false) String desc, HttpServletRequest request, HttpSession session, int id, String projecttitle, String projectsubtitle, String projectdate, int projectsequence)
	{
		logger.info("***** INSIDE SERVICE PROJECT CONTROLLER *****");
		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}		
		String s="y";
		if(projectdate == "")
		{
			projectdate = null;
		}
		
		try
		{
			serviceproject = new ServiceProject(projecttitle, projectsubtitle, projectdate, projectsequence, URLDecoder.decode(desc,"UTF-8"),s,1,IpAddress,id);
		}
		catch (UnsupportedEncodingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		serviceprojectdao.editServiceProject(serviceproject);
		
		return "";
	}
	
	@RequestMapping(value="/deleteServiceProject",method= RequestMethod.DELETE)
	public void deleteServiceProject(int id)
	{
		logger.info("********** INSIDE DELETE SERVICE PROJECT ********** ");		
		serviceprojectdao.deleteServiceProject(id);
	}
	
	@RequestMapping(value="/addServiceProjectImages",method= RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String addServiceProjectImages(@RequestParam(value="serviceprojectimage", required=false) MultipartFile[] serviceprojectimage,HttpServletRequest request, HttpSession session, String sequence[], String imagetitle[])
	{
		logger.info("********** INSIDE ADD SERVICE PROJECT IMAGE **********");
		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}				
		int serviceprojectid = serviceprojectdao.getLastServiceProjectId();
		String image = null;
		try
		{           
			for(int i=0; i<serviceprojectimage.length; i++)
			{
				if(serviceprojectimage[i].getOriginalFilename() != null && serviceprojectimage[i].getOriginalFilename() != "")
				{
					try
					{			
						byte[] bytes =  serviceprojectimage[i].getBytes();
						
						File dir = new File(request.getRealPath("")+"/resources/admin/images/" + File.separator + "ServiceProject");
		    			if (!dir.exists()) 
		    				dir.mkdirs();
		    			String path = request.getRealPath("/resources/admin/images/ServiceProject/");
			            File uploadfile = new File(path+File.separator+serviceprojectimage[i].getOriginalFilename());
			            
			            /********* Today Start **********/
			            
			            int height=416, width=833;
			            
			            ByteArrayInputStream in = new ByteArrayInputStream(bytes);
			        	try
			        	{
			        		/*BufferedImage img = ImageIO.read(in);
			        		
			        		int original_width = img.getWidth();
			        	    int original_height = img.getHeight();
			        	    int bound_width = 833;
			        	    int bound_height = 416;
			        		
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

			        		bytes = buffer.toByteArray();*/
			        		
			        		BufferedImage img = ImageIO.read(in);

			                Image scaledImage = img.getScaledInstance(width, height, Image.SCALE_FAST);

			                BufferedImage imageBuff = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

			                Graphics2D drawer = imageBuff.createGraphics();
			                drawer.drawImage(scaledImage, 0,0 ,null);
			                drawer.dispose();
			                
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
			            File f = new File(path);
			            File files[] = f.listFiles();
			            
			             for (int j = 0; j < files.length; j++)
			             {
			                  if (files[j].isFile())
			                  {
			                    System.out.println("File " + files[j].getName()+" "+files[j].length());
			                  }
			             }
			             
			             
			             //image = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+"/rcbsEvent/resources/admin/images/ServiceProject/"+serviceprojectimage[i].getOriginalFilename();
			             image = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+"/resources/admin/images/ServiceProject/"+serviceprojectimage[i].getOriginalFilename();
			             			             
			            serviceprojectimages = new ServiceProjectImage(serviceprojectid,sequence[i+1],imagetitle[i+1],image,1,IpAddress);
			            serviceprojectdao.addServiceProjectImages(serviceprojectimages);
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}	
	
		return "";
	}
	
	@RequestMapping(value="/getRelatedServiceProjectImages", method= RequestMethod.GET, produces = "application/json")
	public List<ServiceProjectImage> getRelatedServiceProjectImages(HttpServletRequest request, int id)
	{
		logger.info("********** INSIDE GET RELATED SERVICE PROJECT IMAGES **********");		
		List<ServiceProjectImage> ServiceProjectImage = serviceprojectdao.getRelatedServiceProjectImages(id);	
		return ServiceProjectImage;
	}
	
	@RequestMapping(value="/deleteSelectedServiceProjectImages",method= RequestMethod.DELETE)
	public void deleteimage(int id)
	{
		logger.info("********** INSIDE DELETE SELECTED SERVICE PROJECT IMAGES **********");
		
		serviceprojectdao.deleteSelectedServiceProjectImages(id);
	}
	
	@RequestMapping(value="/editServiceProjectImages",method= RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String editServiceProjectImages(@RequestParam(value="serviceprojectimage", required=false) MultipartFile[] serviceprojectimage,HttpServletRequest request, HttpSession session, int id, String sequence[], String imagetitle[])
	{
		logger.info("********** INSIDE ADD SERVICE PROJECT IMAGE **********");
		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}				
		
		String image = null;
		try
		{  
			for(int i=0; i<serviceprojectimage.length; i++)
			{
				if(serviceprojectimage[i].getOriginalFilename() != null && serviceprojectimage[i].getOriginalFilename() != "")
				{
					try
					{			
						byte[] bytes =  serviceprojectimage[i].getBytes();
						
						File dir = new File(request.getRealPath("")+"/resources/admin/images/" + File.separator + "ServiceProject");
		    			if (!dir.exists()) 
		    				dir.mkdirs();
		    			String path = request.getRealPath("/resources/admin/images/ServiceProject/");
			            File uploadfile = new File(path+File.separator+serviceprojectimage[i].getOriginalFilename());
			            
			            /********* Today Start **********/
			            
			            int height=416, width=833;
			            
			            ByteArrayInputStream in = new ByteArrayInputStream(bytes);
			        	try
			        	{
			        		/*BufferedImage img = ImageIO.read(in);
			        		
			        		int original_width = img.getWidth();
			        	    int original_height = img.getHeight();
			        	    int bound_width = 833;
			        	    int bound_height = 416;
			        		
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

			        		bytes = buffer.toByteArray();*/
			        		
			        		BufferedImage img = ImageIO.read(in);

			                Image scaledImage = img.getScaledInstance(width, height, Image.SCALE_FAST);

			                BufferedImage imageBuff = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

			                Graphics2D drawer = imageBuff.createGraphics();
			                drawer.drawImage(scaledImage, 0,0 ,null);
			                drawer.dispose();
			                
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
			            File f = new File(path);
			            File files[] = f.listFiles();
			            
			             for (int j = 0; j < files.length; j++)
			             {
			                  if (files[j].isFile())
			                  {
			                    System.out.println("File " + files[j].getName()+" "+files[j].length());
			                  }
			             }            
			             
			             //image = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+"/rcbsEvent/resources/admin/images/ServiceProject/"+serviceprojectimage[i].getOriginalFilename();
			             image = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+"/resources/admin/images/ServiceProject/"+serviceprojectimage[i].getOriginalFilename();
			             
			            serviceprojectimages = new ServiceProjectImage(id,sequence[i+1],imagetitle[i+1],image,1,IpAddress);
			            serviceprojectdao.addServiceProjectImages(serviceprojectimages);
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}	
	
		return "";
	}
	
	@RequestMapping(value="/editServiceProjectImagesNew",method= RequestMethod.POST)
	public String editServiceProjectImagesNew(HttpServletRequest request, HttpSession session,int id, String sequence, String imagetitle, String image)
	{
		logger.info("********** INSIDE EDIT SERVICE PROJECT IMAGES NEW **********");
		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}
		
		serviceprojectimages = new ServiceProjectImage(id,sequence,imagetitle,image,1,IpAddress);
		serviceprojectdao.addServiceProjectImages(serviceprojectimages);
			
		return "";
	}
	
	@RequestMapping(value="/getLastSequenceNo",method= RequestMethod.GET)
	public int getLastSequenceNo(HttpServletRequest request, HttpSession session)
	{
		int temp = 0, id = 0;
		logger.info("********** INSIDE GET LAST SEQUENCE NUMBER OF SERVICE PROJECT **********");		
		temp = serviceprojectdao.getLastSequenceNo();		
		id = temp + 1;	
		return id;
	}
	
	@RequestMapping(value="/getServiceProjectDetailById", method= RequestMethod.GET, produces = "application/json")
	public ServiceProject getServiceProjectDetailById(int id, HttpServletRequest request) throws UnsupportedEncodingException
	{
		logger.info("********** INSIDE GET SERVICE PROJECT DETAIL BY ID **********");
		
		ServiceProject serviceproject = serviceprojectdao.getServiceProjectDetailById(id);
		
		return serviceproject;
	}
	
	@RequestMapping(value="/addServiceProjectBeneficiary",method= RequestMethod.POST)	
	public String addServiceProjectBeneficiary(int serviceprojectid, int beneficiarytypeid, int beneficiaryid, int donorid, String startdate, String enddate, HttpServletRequest request, HttpSession session)
	{
		logger.info("***** INSIDE SERVICE PROJECT BENEFICIARY CONTROLLER *****");
		
		int id = Integer.parseInt(session.getAttribute("adminid").toString());
		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}
		
		if(startdate == "")
		{
			startdate = null;	
		}
		if(enddate == "")
		{
			enddate = null;	
		}
		
		serviceProjectBeneficiary = new ServiceProjectBeneficiary(serviceprojectid, beneficiarytypeid, beneficiaryid, donorid, startdate, enddate, id, IpAddress);
		serviceprojectdao.addServiceProjectBeneficiary(serviceProjectBeneficiary);
		
		return "";
	}
	
	@RequestMapping(value="/getServiceProjectBeneficiaryByServiceProjectId", method= RequestMethod.GET, produces = "application/json")
	public List<ServiceProjectBeneficiary> getServiceProjectBeneficiaryByServiceProjectId(int serviceprojectid, HttpServletRequest request, HttpServletResponse res)
	{
		logger.info("***** INSIDE ALL SERVICE PROJECT BENEFICIARY BY SERVICE PROJECT ID CONTROLLER *****");
		res.addHeader("Access-Control-Allow-Origin", "*");
		
		List<ServiceProjectBeneficiary> serviceproject = serviceprojectdao.getServiceProjectBeneficiaryByServiceProjectId(serviceprojectid);
		res.setCharacterEncoding("UTF-8");
		
		return serviceproject;
	}
	
	@RequestMapping(value="/editServiceProjectBeneficiary",method= RequestMethod.POST)	
	public String editServiceProjectBeneficiary(int serviceprojectbeneficiaryid, int serviceprojectid, int beneficiarytypeid, int beneficiaryid, int donorid, String startdate, String enddate, HttpServletRequest request, HttpSession session)
	{
		logger.info("***** INSIDE EDIT SERVICE PROJECT BENEFICIARY CONTROLLER *****");
		
		int id = Integer.parseInt(session.getAttribute("adminid").toString());
		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}
		
		if(startdate == "")
		{
			startdate = null;	
		}
		if(enddate == "")
		{
			enddate = null;	
		}
		
		serviceProjectBeneficiary = new ServiceProjectBeneficiary(serviceprojectbeneficiaryid, serviceprojectid, beneficiarytypeid, beneficiaryid, donorid, startdate, enddate, id, IpAddress);
		serviceprojectdao.editServiceProjectBeneficiary(serviceProjectBeneficiary);
		
		return "";
	}
	
	@RequestMapping(value="/deleteServiceProjectBeneficiary",method= RequestMethod.DELETE)
	public void deleteServiceProjectBeneficiary(int id)
	{
		logger.info("********** INSIDE DELETE SERVICE PROJECT BENEFICIARY ********** ");		
		serviceprojectdao.deleteServiceProjectBeneficiary(id);
	}

}
