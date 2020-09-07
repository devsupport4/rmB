package com.ui.dao;

import java.util.List;

import com.ui.model.ClubInfo;
import com.ui.model.Country;
import com.ui.model.State;

public interface CountryDAO {
	public List<Country> getAllCountry();

	public List<State> getRelatedState(long countryId);

	public ClubInfo getClubInfo();

	public void updateClubInfo(ClubInfo c);
	public void addClubInfo(ClubInfo c);
	/*public int getLastClubInfoId();*/
	public Country getCountryDetailById(int countryid);
}
