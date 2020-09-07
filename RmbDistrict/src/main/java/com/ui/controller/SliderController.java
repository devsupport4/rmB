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
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ui.dao.SliderDAO;
import com.ui.model.Slider;

@RestController
public class SliderController
{
	@Autowired
	SliderDAO sliderDAO;
	Slider slider;
	
	private static final Logger logger = LoggerFactory.getLogger(SliderController.class);
	
	@RequestMapping(value="/getSliders", method= RequestMethod.GET, produces = "application/json")
	public List<Slider> getSliders(HttpServletRequest request, HttpServletResponse res)
	{
		res.addHeader("Access-Control-Allow-Origin", "*");
		logger.info("Inside Gel All Slider Controller");
		
		List<Slider> slider = sliderDAO.getAllSliders();
		
		return slider;
	}
	
	@RequestMapping(value="/getSlidersByPage", method= RequestMethod.GET, produces = "application/json")
	public List<Slider> getSlidersByPage(int pagesize, int startindex, HttpServletRequest request)
	{
		logger.info("Inside Get Slider By Page Controller");
		
		List<Slider> slider = sliderDAO.getAllSlidersByPage(pagesize, startindex);
		
		return slider;
	}
	
	@RequestMapping(value="/getActiveSliders", method= RequestMethod.GET, produces = "application/json")
	public List<Slider> getActiveSliders(HttpServletRequest request, HttpServletResponse res)
	{
		res.addHeader("Access-Control-Allow-Origin", "*");
		logger.info("Inside Gel Slider Controller");
		
		List<Slider> slider = sliderDAO.getActiveSliders();
		
		return slider;
	}
	
	@RequestMapping(value = "/addSlider",method = RequestMethod.POST)
	public String addSlider(@RequestParam(value="image", required=false) MultipartFile image, String slidertitle, String active, String redirecturl, String target, String ipaddress, int valuex, int valuey, int valuew, int valueh, HttpSession session, HttpServletRequest request)
	{
		logger.info("Inside Add Slider Controller");
		
		String s = slidertitle.replace("$","&");
		String s1 = s.replace("~","#");
		String s2 = s1.replace("!","%");
		
		String u = redirecturl.replace("$","&");
		String u1 = u.replace("~","#");
		String u2 = u1.replace("!","%");
		
		int id = Integer.parseInt(session.getAttribute("adminid").toString());
		
		Random random = new Random();
		int randomnumber = 100000 + random.nextInt(900000);
		
		String image1="";
		
		try
	    {
	    	if(image.getOriginalFilename() != "")
			{
	    		try
				{			
					byte[] bytes =  image.getBytes();
					
					File dir = new File(request.getRealPath("")+"/resources/admin/images/" + File.separator + "slider");
	    			if (!dir.exists()) 
	    				dir.mkdirs();
	    			String path = request.getRealPath("/resources/admin/images/slider/");
		            File uploadfile = new File(path+File.separator+randomnumber+image.getOriginalFilename());
		            
		            /********* Today Start **********/
		            
		            ByteArrayInputStream in = new ByteArrayInputStream(bytes);
		        	try
		        	{
		        		BufferedImage img = ImageIO.read(in);
		        		
		        		Image scaledImage = img.getScaledInstance(valuew-1, valueh-1, Image.SCALE_SMOOTH);
		                BufferedImage SubImgage = img.getSubimage(valuex, valuey, valuew-1, valueh-1);
		                Graphics2D drawer = SubImgage.createGraphics();
		                drawer.setComposite(AlphaComposite.Src);
		                drawer.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		                drawer.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		                drawer.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		                drawer.drawImage(scaledImage, valuew-1, valueh-1, null);
		                drawer.dispose();
		                ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		                ImageIO.write(SubImgage, "jpg", buffer);
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

		           image1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/resources/admin/images/slider/" + randomnumber + image.getOriginalFilename();
		     //     image1 = "https" + "://" + request.getServerName() + "/resources/admin/images/slider/" + randomnumber + image.getOriginalFilename();
					 
					slider = new Slider();
							

							slider.setSliderTitle(s2);	
							slider.setImage(image1);
							slider.setRedirectUrl(u2);
							slider.setTarget(target);
							slider.setActive(active);
							slider.setStatus("y");
							slider.setCreatedBy(id);
							slider.setIpAddress(ipaddress);
							
					sliderDAO.addSlider(slider);
				}catch(Exception e)
				{
					System.out.println(e.getMessage());
				}
			}
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    }
		
		return "";
	}
	
	@RequestMapping(value = "/editSlider",method = RequestMethod.POST)
	public String editSlider(@RequestParam(value="image", required=false) MultipartFile image, int sliderid, String slidertitle, String active, String redirecturl, String target, String sliderimage, String ipaddress, int valuex, int valuey, int valuew, int valueh, HttpSession session, HttpServletRequest request)
	{
		logger.info("Inside Edit Slider Controller");
		
		String s = slidertitle.replace("$","&");
		String s1 = s.replace("~","#");
		String s2 = s1.replace("!","%");
		
		String u = redirecturl.replace("$","&");
		String u1 = u.replace("~","#");
		String u2 = u1.replace("!","%");
		
		int id = Integer.parseInt(session.getAttribute("adminid").toString());
		
		Random random = new Random();
		int randomnumber = 100000 + random.nextInt(900000);
		
		String image1=sliderimage;
		
		try
	    {
	    	if(image.getOriginalFilename() != "")
			{
	    		try
				{			
					byte[] bytes =  image.getBytes();
					
					File dir = new File(request.getRealPath("")+"/resources/admin/images/" + File.separator + "slider");
	    			if (!dir.exists()) 
	    				dir.mkdirs();
	    			String path = request.getRealPath("/resources/admin/images/slider/");
		            File uploadfile = new File(path+File.separator+randomnumber+image.getOriginalFilename());
		            
		            /********* Today Start **********/
		            
		            ByteArrayInputStream in = new ByteArrayInputStream(bytes);
		        	try
		        	{
		        		BufferedImage img = ImageIO.read(in);
		        		
		        		Image scaledImage = img.getScaledInstance(valuew-1, valueh-1, Image.SCALE_SMOOTH);
		                BufferedImage SubImgage = img.getSubimage(valuex, valuey, valuew-1, valueh-1);
		                Graphics2D drawer = SubImgage.createGraphics();
		                drawer.setComposite(AlphaComposite.Src);
		                drawer.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		                drawer.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		                drawer.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		                drawer.drawImage(scaledImage, valuew-1, valueh-1, null);
		                drawer.dispose();
		                ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		                ImageIO.write(SubImgage, "jpg", buffer);
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

		               image1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/resources/admin/images/slider/" + randomnumber + image.getOriginalFilename();
		          //	image1 = "https" + "://" + request.getServerName() + "/resources/admin/images/slider/" + randomnumber + image.getOriginalFilename();
					 
					slider = new Slider();
					
					slider.setSliderId(sliderid);
					slider.setSliderTitle(s2);	
					slider.setImage(image1);
					slider.setRedirectUrl(u2);
					slider.setTarget(target);
					slider.setActive(active);
					slider.setStatus("y");
					slider.setCreatedBy(id);
					slider.setIpAddress(ipaddress);
					
					sliderDAO.editSlider(slider);
				}catch(Exception e)
				{
					System.out.println(e.getMessage());
				}
			}
	    }
	    catch(Exception e)
	    {
	    	slider = new Slider();
			
			slider.setSliderId(sliderid);
			slider.setSliderTitle(s2);	
			slider.setImage(image1);
			slider.setRedirectUrl(u2);
			slider.setTarget(target);
			slider.setActive(active);
			slider.setStatus("y");
			slider.setCreatedBy(id);
			slider.setIpAddress(ipaddress);
			
	    	sliderDAO.editSlider(slider);
	    	e.printStackTrace();
	    }
		
		return "";
	}
	
	@RequestMapping(value="/deleteSlider",method= RequestMethod.DELETE  )
	public void delete(int sliderid)
	{
		logger.info("Inside delete Slider Controller...");

		sliderDAO.deleteSlider(sliderid);
	}
	
	@RequestMapping(value="setActiveOrInActiveSlider", method= RequestMethod.POST)
	public String setActiveOrInActiveSlider(int sliderid, String active, String ipaddress, HttpServletRequest request, HttpSession session)
	{
		logger.info("Inside Set Active Or InActive Slider Controller");
				
		int id = Integer.parseInt(session.getAttribute("adminid").toString());
		
		
		slider = new Slider();
		
		slider.setSliderId(sliderid);
		
	
		slider.setActive(active);
	
		slider.setCreatedBy(id);
		slider.setIpAddress(ipaddress);
		
		sliderDAO.setActiveOrInActiveSlider(slider);
			
		return "";
	}
	
	
}