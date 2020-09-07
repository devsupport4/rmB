package com.ui.dao;

import java.util.List;

import com.ui.model.BeneficiaryType;

public interface BeneficiaryTypeDAO{
	
	public List<BeneficiaryType> getAllBeneficiaryType();
	public void addBeneficiaryType(BeneficiaryType bt);
	public void editBeneficiaryType(BeneficiaryType bt);
	public void deleteBeneficiaryType(int id);
}
