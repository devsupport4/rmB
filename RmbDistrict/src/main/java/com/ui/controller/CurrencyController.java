package com.ui.controller;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ui.dao.CurrencyDAO;
import com.ui.model.Currency;

@RestController
public class CurrencyController
{
	@Autowired
	
	CurrencyDAO currencydao;
	Currency currency;
	
	private static final Logger logger = LoggerFactory.getLogger(CurrencyController.class);
	
	@RequestMapping(value="/Currency", method= RequestMethod.GET, produces = "application/json")
	public List<Currency> Currency()
	{
		logger.info("Inside Get All Currency");
		
		List<Currency> Currency = currencydao.getAllCurrency();
		
		return Currency;
		
	}
	
	/*@RequestMapping(value="addCurrency", method= RequestMethod.POST)
	public String addCurrency(@RequestParam(value="file", required=false) MultipartFile file,HttpServletRequest request, HttpSession session, String currencyname, String currencycode, String description, String createddate)
	{
		logger.info("Inside Add Currency");
		
		String c = currencyname.replace("$","&");
		String c1 = c.replace("~","#");
		String c2 = c1.replace("!","%");
		
		String d = description.replace("$","&");
		String d1 = d.replace("~","#");
		String d2 = d1.replace("!","%");
		
		int id = Integer.parseInt(session.getAttribute("id").toString());
		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}
		String s = "y";
		String image = null;

		try
		{
			if (file.getOriginalFilename() != null && file.getOriginalFilename() != "")
			{
				try
				{			
					byte[] bytes =  file.getBytes();
					
					File dir = new File(request.getRealPath("")+"/resources/front/images/" + File.separator + "currency");
	    			if (!dir.exists()) 
	    				dir.mkdirs();
					
	    			String path = request.getRealPath("/resources/front/images/currency/");
		            //String path = System.getProperty("catalina.home");
		            //String path = "ultrasmartsolutions.com:8080/PDFConvertor/resources/front";
		            File uploadfile = new File(path+File.separator+file.getOriginalFilename());
		            //System.out.println("*********************Path"+path);
		            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(uploadfile));
		            bufferedOutputStream.write(bytes);
		            bufferedOutputStream.close();
		            
		            File f = new File(path);
		            File files[] = f.listFiles();
		            
		             for (int i = 0; i < files.length; i++)
		             {
		                  if (files[i].isFile())
		                  {
		                    System.out.println("File " + files[i].getName()+" "+files[i].length());
		                  }
		             }
					//image = path+"/images/category/"+file.getOriginalFilename();
		             image = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+"/eCBMP/resources/front/images/currency/"+file.getOriginalFilename();
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
		
		currency = new Currency(c2,currencycode,image,description,s,id,createddate,IpAddress);
		
		currencydao.addCurrency(currency);
	
		return session.getAttribute("path").toString();
		//return "";
	}

	
	@RequestMapping(value="editCurrency", method= RequestMethod.POST)
	public String editCurrency(@RequestParam(value="file", required=false) MultipartFile file,HttpServletRequest request, HttpSession session, String currencyid, String currencyname, String currencycode, String description, String createddate)

	//@RequestMapping(value="editCurrency", method= RequestMethod.PUT)
	//public String editCurrency(String currencyid, String currencyname, String currencycode, String description, String createddate, HttpServletRequest request, HttpSession session)
	{
		logger.info("Inside Edit Currency");
		
		String c = currencyname.replace("$","&");
		String c1 = c.replace("~","#");
		String c2 = c1.replace("!","%");
		
		String d = description.replace("$","&");
		String d1 = d.replace("~","#");
		String d2 = d1.replace("!","%");
		
		int sid = Integer.parseInt(session.getAttribute("id").toString());
		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}
		
		String s="y";
		String image = null;

		try
		{
			if (file.getOriginalFilename() != null && file.getOriginalFilename() != "")
			{
				try
				{			
					byte[] bytes =  file.getBytes();
					
					File dir = new File(request.getRealPath("")+"/resources/front/images/" + File.separator + "currency");
	    			if (!dir.exists()) 
	    				dir.mkdirs();
					
	    			String path = request.getRealPath("/resources/front/images/currency/");
		            //String path = System.getProperty("catalina.home");
		            //String path = "ultrasmartsolutions.com:8080/PDFConvertor/resources/front";
		            File uploadfile = new File(path+File.separator+file.getOriginalFilename());
		            //System.out.println("*********************Path"+path);
		            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(uploadfile));
		            bufferedOutputStream.write(bytes);
		            bufferedOutputStream.close();
		            
		            File f = new File(path);
		            File files[] = f.listFiles();
		            
		             for (int i = 0; i < files.length; i++)
		             {
		                  if (files[i].isFile())
		                  {
		                    System.out.println("File " + files[i].getName()+" "+files[i].length());
		                  }
		             }
					//image = path+"/images/category/"+file.getOriginalFilename();
		             image = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+"/eCBMP/resources/front/images/currency/"+file.getOriginalFilename();
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
		
		try
		{
			int id=Integer.parseInt(currencyid);
			
			currency = new Currency(id,c2,currencycode,image,description,s,sid,createddate,IpAddress);
			
			currencydao.editCurrency(currency);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return "";
	}
	
	@RequestMapping(value="deleteCurrency", method= RequestMethod.DELETE)
	public void delete(String currencyid)
	{
		logger.info("Inside delete currency...");
		int id=Integer.parseInt(currencyid);
		currencydao.deleteCurrency(id);
	
	}*/
	
		
		
	
}