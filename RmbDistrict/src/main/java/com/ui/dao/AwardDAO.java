package com.ui.dao;

import java.util.List;

import com.ui.model.AwardImage;
import com.ui.model.Awards;

public interface AwardDAO {
	public void addAward(Awards a);
	public void addAwardImage(AwardImage p);
	public int getLastAwardId();
	public List<Awards> getAllAwards();
	public List<AwardImage> getAwardImage(int awardid);
	public void deleteSelectedAwardImage(int awardid);
	public void editAward(Awards a);
	public void deleteAward(int awardid);
	public List<AwardImage> getAwardDetailByRotaryYearWithOneImage(int rotaryyearid);
	public Awards getAwardDetailById(int awardid);
}
