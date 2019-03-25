package br.com.cities.backend.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.cities.backend.model.City;
import br.com.cities.backend.repository.CityRepository;
import br.com.cities.backend.service.CityService;

@Controller
@RequestMapping(path="/index")
public class CityController {
	
	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private CityService cityService;
	
	@PostMapping(path="/add")
	public @ResponseBody String addCity (@RequestBody City city) {
		cityRepository.save(city);
		return "Saved";
	}
	
	@GetMapping(path="/findAll")
	public @ResponseBody Iterable<City> getAllCities() {
		return cityRepository.findAll();
	}
	
	@GetMapping(path="/findOnlyCapital")
	public @ResponseBody Iterable<City> getOnlyCapital() {
		return cityService.findOnlyCapital();
	}
	
	@GetMapping(path="/findStateWithMoreAndFewerCities")
	public @ResponseBody Iterable<City> getStateWithMoreAndFewerCities() {
		List<City> stateWithMoreCities = cityService.findStateWithMoreCities();
		List<City> stateWithFewerCities = cityService.findStateWithFewerCities();
		List<City> states = new ArrayList<>();
		states.addAll(stateWithMoreCities);
		states.addAll(stateWithFewerCities);
		return states;
	}
	
	@GetMapping(path="/getTotalCitiesByState")
	public @ResponseBody Iterable<City> getTotalCitiesByState() {
		return cityService.getTotalCitiesByState();
	}
	
	@GetMapping(path="/getCityByIbgeId")
	public @ResponseBody Iterable<City> getCitybyIbgeId(@RequestParam Integer ibgeId) {
		return cityService.getCitybyIbgeId(ibgeId);
	}
	
	@GetMapping(path="/getAllCitiesByState")
	public @ResponseBody Iterable<City> getAllCitiesByState(@RequestParam String uf) {
		return cityService.getAllCitiesByState(uf);
	}
	
	@DeleteMapping(path="/delete")
	public @ResponseBody String deleteCity (@RequestBody City city) {
		cityRepository.delete(city);
		return "Deleted";
	}
	
	@GetMapping(path="/search")
	public @ResponseBody Iterable<City> search(@RequestParam String name) {
		return cityService.search(name);
	}
	
	@GetMapping(path="/countUniqueRecordsPerColumn")
	public @ResponseBody Integer countUniqueRecordsPerColumn(@RequestParam String column) {
		return cityService.countUniqueRecordsPerColumn(column);
	}
	
	@GetMapping(path="/countAllRecords")
	public @ResponseBody Iterable<City> countAllRecords() {
		return cityService.countAllRecords();
	}
	
	@GetMapping(path="/searchMoreDistantCities")
	public @ResponseBody Iterable<String> searchMoreDistantCities() {
		return cityService.searchMoreDistantCities();
	}
}
