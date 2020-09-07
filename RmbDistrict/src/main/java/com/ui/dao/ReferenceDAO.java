package com.ui.dao;

import java.util.List;

import com.ui.model.Reference;

public interface ReferenceDAO {
	
	public void addReference(Reference r);
	public List<Reference> getReference(int memberid);	
	public void deleteReference(int reference_id);
	public void editReference(Reference r);
}
