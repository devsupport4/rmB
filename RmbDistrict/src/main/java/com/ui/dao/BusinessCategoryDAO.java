package com.ui.dao;

import java.util.List;

import com.ui.model.BusinessCategory;

public interface BusinessCategoryDAO {
	public List<BusinessCategory> getAllBusinessCategories();
	public List<BusinessCategory> getLastTenCategoriesForHome();
	public List<BusinessCategory> getBusinessCategoryByPage(int pagesize, int startindex);
	public List<BusinessCategory> searchBusinessCategory(String keyword);
	public void addBusinessCategory(BusinessCategory bc);
	public void editBusinessCategory(BusinessCategory bc);
	public void deleteBusinessCategory(int id);
}
