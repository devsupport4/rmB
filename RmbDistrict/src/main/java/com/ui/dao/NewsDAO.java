package com.ui.dao;

import java.util.List;

import com.ui.model.News;

public interface NewsDAO {

	public void addNews(News n);
	public void editNews(News n);
	public void editNewsWithoutImage(News n);
	public List<News> getAllNews();
	public List<News> getLastThreeNewsForHome();
	public void deleteNews(int id);
	public News getNewsDetailById(int id);
	public List<News> getNewsDetailByRotaryYear(int rotaryyearid);
}
