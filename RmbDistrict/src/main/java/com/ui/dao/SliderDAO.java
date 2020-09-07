package com.ui.dao;

import java.util.List;

import com.ui.model.Slider;

public interface SliderDAO {

	public List<Slider> getAllSliders();
	public List<Slider> getAllSlidersByPage(int pagesize, int startindex);
	public List<Slider> getActiveSliders();
	public void addSlider(Slider slider);
	public void editSlider(Slider slider);
	public void deleteSlider(int sliderid);
	public void setActiveOrInActiveSlider(com.ui.model.Slider slider);
}
