package com.ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ui.dao.ReadExcelDAO;
import com.ui.model.ReadExcel;

@RestController
public class ReadExcelCtrl {

	@Autowired
	ReadExcelDAO readExceldao;
	
	@RequestMapping(value="/addExcelData", method= RequestMethod.POST)
    public String addExcelData(int number,String name)
 
    {
        String result = "";
       
        ReadExcel r = new ReadExcel();
        
       
        r.setNumber(number);
        r.setName(name);
     
        System.out.println("daoimpl");
        result = readExceldao.readExcelData(r);
  
        return result;
    }
}
