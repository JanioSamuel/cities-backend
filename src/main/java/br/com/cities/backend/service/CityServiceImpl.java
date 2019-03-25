package br.com.cities.backend.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cities.backend.Utils;
import br.com.cities.backend.model.City;
import br.com.cities.backend.repository.CityRepository;

@Service
public class CityServiceImpl implements CityService{

	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private CityRepository cityRepository;
	
	@Override
	public List<City> findOnlyCapital() {
		List<City> cities = cityRepository.findOnlyCapital();
		return cities;
	}

	@Override
	public List<City> findStateWithMoreCities() {
		List obj = new ArrayList<>();
		obj = entityManager.createQuery("select c.uf, count(c.uf) from City c group by c.uf order by count(c.uf) desc").setMaxResults(1).getResultList();
		List<City> city = new ArrayList<>();
		city.addAll(obj);
		return city;
	}

	@Override
	public List<City> findStateWithFewerCities() {
		List obj = new ArrayList<>();
		obj = entityManager.createQuery("select c.uf, count(c.uf) from City c group by c.uf order by count(c.uf) asc").setMaxResults(1).getResultList();
		List<City> city = new ArrayList<>();
		city.addAll(obj);
		return city;
	}

	@Override
	public List<City> getTotalCitiesByState() {
		List obj = new ArrayList<>();
		obj = entityManager.createQuery("select c.uf, count(c.uf) from City c group by c.uf order by count(c.uf) asc").getResultList();
		List<City> city = new ArrayList<>();
		city.addAll(obj);
		return city;
	}

	@Override
	public List<City> getCitybyIbgeId(Integer ibgeId) {
		return cityRepository.getCitybyIbgeId(ibgeId);
	}

	@Override
	public List<City> getAllCitiesByState(String uf) {
		return cityRepository.getAllCitiesByState(uf);
	}

	@Override
	public List<City> search(String name) {
		Query query = entityManager.createQuery("select c from City c where c.name like :name1  or c.name like :name2 or c.name like :name3");
		query.setParameter("name1", "%" + name);
		query.setParameter("name2", name + "%");
		query.setParameter("name3", "%" + name + "%");
		List<City> cities = query.getResultList();
		return cities;
	}

	@Override
	public Integer countUniqueRecordsPerColumn(String column) {
		Query query = entityManager.createQuery(String.format("select count(distinct %s) from City c", column));
		Long count = (long) query.getSingleResult();
		return Integer.valueOf(count.intValue());
	}

	@Override
	public List<City> countAllRecords() {
		return cityRepository.countAllRecords();
	}

	@Override
	public List<String> searchMoreDistantCities() {
		Map<String, Double> values = new HashMap<>();
		List<City> cities = (List<City>) cityRepository.findAll();
		Utils utils = new Utils();
		for (City city : cities) {
			System.out.println(city);
			for (City city2 : cities) {				
				double dist = utils.distance(city.getLat(), city.getLon(), city2.getLat(), city2.getLon(), "K".charAt(0));
				values.put(city.getNo_accents() + "." + city2.getNo_accents(), dist);
			}
		}
		Double max = Collections.max(values.values());
		return values.entrySet().stream().filter(entry -> entry.getValue() == max).map(entry -> entry.getKey()).collect(Collectors.toList());
	}
}
