package br.com.cities.backend.model;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;

import org.springframework.context.annotation.ComponentScan;

@SqlResultSetMapping(name = "CityMapping", classes = {
		  @ConstructorResult(targetClass = City.class, columns = {
		    @ColumnResult(name = "uf")
		  })
		})

@NamedNativeQuery(
        name="CityQuery",
        query="select c.uf as 'uf', count(c.uf) as 'qtd' from City c group by c.uf order by count(c.uf) desc limit 1",
        resultSetMapping = "CityMapping"
)
@ComponentScan
@Entity
public class City {
	
	@Id
	private Integer ibge_id;
	
	private String uf;
	
	private String name;
	
	private boolean capital;
	
	private double lon;
	
	private double lat;
	
	private String no_accents;
	
	private String alternative_names;
	
	private String microregion;
	
	private String mesoregion;

	public Integer getIbge_id() {
		return ibge_id;
	}

	public void setIbge_id(Integer ibge_id) {
		this.ibge_id = ibge_id;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isCapital() {
		return capital;
	}

	public void setCapital(boolean capital) {
		this.capital = capital;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public String getNo_accents() {
		return no_accents;
	}

	public void setNo_accents(String no_accents) {
		this.no_accents = no_accents;
	}

	public String getAlternative_names() {
		return alternative_names;
	}

	public void setAlternative_names(String alternative_names) {
		this.alternative_names = alternative_names;
	}

	public String getMicroregion() {
		return microregion;
	}

	public void setMicroregion(String microregion) {
		this.microregion = microregion;
	}

	public String getMesoregion() {
		return mesoregion;
	}

	public void setMesoregion(String mesoregion) {
		this.mesoregion = mesoregion;
	}
	
	public City() {
		
	}
	
	public City(String uf) {
		this.uf = uf;
	}
}

