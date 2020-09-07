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

import com.ui.dao.AlbumDAO;
import com.ui.model.Album;
import com.ui.model.AlbumImage;

@RestController
public class AlbumController
{
	@Autowired
	
	AlbumDAO albumdao;
	Album album;
	AlbumImage albumimage;	
	
	
	private static final Logger logger = LoggerFactory.getLogger(AlbumController.class);
	
	@RequestMapping(value="/Album", method= RequestMethod.GET, produces = "application/json")
	public List<Album> Album(HttpServletRequest request)	{
		logger.info("Inside Get All Album");	
		
		List<Album> Album = albumdao.getAllAlbum();		
		return Album;	
	}	
	
	@RequestMapping(value="/getAlbumOneImage", method= RequestMethod.GET, produces = "application/json")
	public List<AlbumImage> AlbumOneImages(HttpServletRequest request)	{
		logger.info("Inside Get One Image");		
		List<AlbumImage> AlbumImage = albumdao.getAlbumOneImage();		
		return AlbumImage;
	}
	
	
	@RequestMapping(value="/AlbumImages", method= RequestMethod.GET, produces = "application/json")
	public List<AlbumImage> AlbumImages(HttpServletRequest request)
	{
		logger.info("Inside Get All Album Images");
		
		List<AlbumImage> AlbumImage = albumdao.getAllAlbumImages();
		
		return AlbumImage;
	}
	
	@RequestMapping(value="/AlbumImage", method= RequestMethod.GET, produces = "application/json")
	public List<AlbumImage> AlbumImage(int albumid, HttpServletRequest request)
	{
		logger.info("Inside Get Album Image");
		
		List<AlbumImage> AlbumImage = albumdao.getAlbumImage(albumid);
		
		return AlbumImage;
	}
	
	@RequestMapping(value="/addAlbum", method= RequestMethod.POST)
	public String addAlbum(int projectid, String title, String subtitle, String albumdate, String description, HttpServletRequest request, HttpSession session)
	{
		logger.info("***** INSIDE ADD ALBUM CONTROLLER *****");
		
		String p = title.replace("$","&");
		String p1 = p.replace("~","#");
		String p2 = p1.replace("!","%");
				
		//int id = Integer.parseInt(session.getAttribute("adminid").toString());
		String status = "y";
		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}
				
		Album a = new Album(p2, subtitle, albumdate, description, projectid, status, 1, IpAddress);
		albumdao.addAlbum(a);
				
		return "";
	}
	
	@RequestMapping(value="addAlbumImage", method= RequestMethod.POST)
	public String addAlbumImage(@RequestParam(value="image", required=false) MultipartFile[] image, String imagetitle[], String imagedescription[], HttpServletRequest request, HttpSession session) throws UnsupportedEncodingException
	{
		logger.info("***** INSIDE ADD ALBUM IMAGE *****");
				
		int id = Integer.parseInt(session.getAttribute("adminid").toString());
		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}
		
		int albumid = albumdao.getLastAlbumId();

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
						
						File dir = new File(request.getRealPath("")+"/resources/admin/images/" + File.separator + "photoalbum");
		    			if (!dir.exists()) 
		    				dir.mkdirs();
		    			
		    			String path = request.getRealPath("/resources/admin/images/photoalbum/");
			            File uploadfile = new File(path+File.separator+image[i].getOriginalFilename());
			            
			            /********* Today Start **********/
			            
			            int height=575, width=915;
			            
			            ByteArrayInputStream in = new ByteArrayInputStream(bytes);
			        	try
			        	{
			        		BufferedImage img = ImageIO.read(in);
			        		
			        		int original_width = img.getWidth();
			        	    int original_height = img.getHeight();
			        	    int bound_width = 915;
			        	    int bound_height = 575;
			        	    int new_width = original_width;
			        	    int new_height = original_height;
			        		
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
			            image1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+"/RmbDistrict/resources/admin/images/photoalbum/"+image[i].getOriginalFilename();
			            //image1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+"/resources/admin/images/photoalbum/"+image[i].getOriginalFilename();
			            //
			            
			            String t = imagetitle[i].toString().replace("$","&");
			    		String t1 = t.replace("~","#");
			    		String t2 = t1.replace("!","%"); 		
			            
			    		albumimage = new AlbumImage(t2, imagedescription[i].toString(), image1, albumid,id,IpAddress);
			    		albumdao.addAlbumImage(albumimage);
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
	
	
	@RequestMapping(value="deleteSelectedAlbumImage", method= RequestMethod.DELETE)
	public void deleteSelectedAlbumImage(int albumid)
	{
		logger.info("Inside delete Selected Album Image...");
		
		albumdao.deleteSelectedAlbumImage(albumid);
	}
	
	
	@RequestMapping(value="editAlbum", method= RequestMethod.PUT)
	public String editAlbum(int albumid, int project, String title, String subtitle, String albumdate, String description, HttpServletRequest request, HttpSession session) throws UnsupportedEncodingException
	{
		logger.info("***** INSIDE EDIT PHOTO ALBUM *****");
		
		String p = title.replace("$","&");
		String p1 = p.replace("~","#");
		String p2 = p1.replace("!","%");
		
		int id = Integer.parseInt(session.getAttribute("adminid").toString());
		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}
		
		Album a = new Album(albumid, p2, subtitle, albumdate, description, project, id, IpAddress);
		albumdao.editAlbum(a);
		
		return "";
	}
	
	@RequestMapping(value="editAlbumImage", method= RequestMethod.POST)
	public String editAlbumImage(@RequestParam(value="image", required=false) MultipartFile[] image, int albumid, String imagetitle[], String imagedescription[], HttpServletRequest request, HttpSession session)
	{
		logger.info("***** INSIDE EDIT ALBUM IMAGE *****");
		
		int id = Integer.parseInt(session.getAttribute("adminid").toString());
		
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
						
						File dir = new File(request.getRealPath("")+"/resources/admin/images/" + File.separator + "photoalbum");
		    			if (!dir.exists()) 
		    				dir.mkdirs();
						
		    			String path = request.getRealPath("/resources/admin/images/photoalbum/");
			            File uploadfile = new File(path+File.separator+image[i].getOriginalFilename());
			            
			           //********* Today Start **********//
			            
			            int height=575, width=915;
			            
			            ByteArrayInputStream in = new ByteArrayInputStream(bytes);
			        	try
			        	{
			        		BufferedImage img = ImageIO.read(in);
			        		
			        		int original_width = img.getWidth();
			        	    int original_height = img.getHeight();
			        	    int bound_width = 915;
			        	    int bound_height = 575;
			        	    int new_width = original_width;
			        	    int new_height = original_height;
			        		
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
			            
			            //
			            image1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+"/RmbDistrict/resources/admin/images/photoalbum/"+image[i].getOriginalFilename();
			            //image1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+"/resources/admin/images/photoalbum/"+image[i].getOriginalFilename();
			            
			            albumimage = new AlbumImage(imagetitle[i].toString(), imagedescription[i].toString(), image1, albumid, id, IpAddress);
			    		
			            albumdao.addAlbumImage(albumimage);
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
	
	@RequestMapping(value="/editAlbumImageNew",method= RequestMethod.POST)
	public String editAlbumImageNew(HttpServletRequest request, HttpSession session, int albumid, String imagetitle, String imagedescription, String image)
	{
		logger.info("***** INSIDE EDIT ALBUM IMAGE NEW *****");
		
		//int id = Integer.parseInt(session.getAttribute("adminid").toString());
		int id = 1;
		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}
		
		albumimage = new AlbumImage(imagetitle, imagedescription, image, albumid,id,IpAddress);
		albumdao.addAlbumImage(albumimage);
		
		return "";
	}

	@RequestMapping(value="deleteAlbum", method= RequestMethod.DELETE)
	public void delete(int albumid)
	{
		logger.info("Inside delete Album...");
		albumdao.deleteAlbum(albumid);
	}
	
	@RequestMapping(value="/getPhotoAlbumByServiceProjectId", method= RequestMethod.GET, produces = "application/json")
	public List<AlbumImage> getPhotoAlbumByServiceProjectId(int id, HttpServletRequest request)
	{
		logger.info("********** INSIDE GET ALBUM BY SERVICE PROJECT BY ID **********");
		
		List<AlbumImage> albumimage = albumdao.getPhotoAlbumByServiceProjectId(id);
		
		return albumimage;
	}
}
