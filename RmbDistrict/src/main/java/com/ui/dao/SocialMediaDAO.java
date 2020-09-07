package com.ui.dao;

import java.util.List;

import com.ui.model.SocialMediaLink;

public interface SocialMediaDAO {

	public List<SocialMediaLink> getAllSocialMediaLinks();	
	public void addSocialMediaLink(SocialMediaLink s);
	public void editSocialMediaLink(SocialMediaLink s);
	public void deleteSocialMediaLink(int id);
}
