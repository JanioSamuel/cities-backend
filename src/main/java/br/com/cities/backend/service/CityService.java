package br.com.cities.backend.service;

import java.util.List;

import br.com.cities.backend.model.City;

public interface CityService {
	
	public List<City> findOnlyCapital();
	
	public List<City> findStateWithMoreCities();
	
	public List<City> findStateWithFewerCities();
	
	public List<City> getTotalCitiesByState();
	
	public List<City> getCitybyIbgeId(Integer ibgeId);
	
	public List<City> getAllCitiesByState(String uf);
	
	public List<City> search(String name);
	
	public Integer countUniqueRecordsPerColumn(String column);
	
	public List<City> countAllRecords();
	
	public List<String> searchMoreDistantCities();
}
