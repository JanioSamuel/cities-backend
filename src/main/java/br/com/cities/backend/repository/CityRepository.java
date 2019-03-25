package br.com.cities.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.cities.backend.model.City;

public interface CityRepository extends CrudRepository<City, Integer> {
	
	@Query(value="select c.no_accents from City c where c.capital=1 order by c.no_accents asc")
	public List<City> findOnlyCapital();
	
	@Query(value="select c.uf, count(c.uf) from City c group by c.uf order by count(c.uf) desc")
	public List<City> findStateWithMoreCities();
	
	@Query(value="select c.uf, count(c.uf) from City c group by c.uf order by count(c.uf) asc")
	public List<City> findStateWithFewerCities();
	
	@Query(value="select c from City c where c.ibge_id= :ibge_id")
	public List<City> getCitybyIbgeId(@Param("ibge_id")Integer ibgeId);
	
	@Query(value="select c from City c where c.uf = :uf")
	public List<City> getAllCitiesByState(@Param("uf") String uf);
	
	@Query(value="select c from City c where c.name like '@:name%' or c.name like '%@:name' or c.name like '%@:name%'")
	public List<City> search(@Param("name")String name);
	
	@Query(value="select count(c) from City c")
	public List<City> countAllRecords();
}
