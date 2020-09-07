package com.ui.dao;

import java.util.List;

import com.ui.model.Album;
import com.ui.model.AlbumImage;

public interface AlbumDAO 
{
	public List<Album> getAllAlbum();
	public List<AlbumImage> getAlbumOneImage();
	public List<AlbumImage> getAllAlbumImages();
	public List<AlbumImage> getAlbumImage(int albumid);
	public void addAlbum(Album a);
	public void addAlbumImage(AlbumImage p);
	public int getLastAlbumId();
	public void deleteSelectedAlbumImage(int albumid);
	public void editAlbum(Album p);	
	public void deleteAlbum(int albumid);
	public List<AlbumImage> getPhotoAlbumByServiceProjectId(int id);
}
