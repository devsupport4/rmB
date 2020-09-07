package com.ui.dao;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.ui.model.ServiceProject;
import com.ui.model.ServiceProjectBeneficiary;
import com.ui.model.ServiceProjectImage;

public interface ServiceProjectDAO {
	
	public List<ServiceProject> getAllServiceProject();
	public List<ServiceProject> getAllServiceProjectWithImage();
	public List<ServiceProjectImage> getAllServiceProjectImages();
	public void addServiceProject(ServiceProject sp);
	public void editServiceProject(ServiceProject sp);
	public void deleteServiceProject(int id);
	public int getLastServiceProjectId();
	public void addServiceProjectImages(ServiceProjectImage spi);
	public List<ServiceProjectImage> getRelatedServiceProjectImages(int id);
	public void deleteSelectedServiceProjectImages(int id);
	public int getLastSequenceNo();
	public ServiceProject getServiceProjectDetailById(int id) throws UnsupportedEncodingException;
	public List<ServiceProjectBeneficiary> getServiceProjectBeneficiaryByServiceProjectId(int serviceprojectid);
	public void addServiceProjectBeneficiary(ServiceProjectBeneficiary sp);
	public void editServiceProjectBeneficiary(ServiceProjectBeneficiary sp);
	public void deleteServiceProjectBeneficiary(int id);
}
