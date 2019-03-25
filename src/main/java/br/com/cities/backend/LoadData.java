package br.com.cities.backend;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cities.backend.model.City;
import br.com.cities.backend.repository.CityRepository;

@Component
public class LoadData {

	@Autowired
	private CityRepository cityRepository;
	
	public void loadDataToDatabase(String filePath) {
		City city = new City();
		String[] split;
		try {
			BufferedReader br = new BufferedReader(new FileReader(
					new File(filePath)));
			String line;
			System.out.println(String.format("\nCARREGANDO A MASSA DE DADOS ENCONTRADA EM: %s \n", filePath));
			while ((line = br.readLine()) != null) {
				if(line.contains("ibge_id")) {
					continue;
				}
				split = line.split(",");
				city.setIbge_id(Integer.parseInt(split[0]));
				city.setUf(split[1]);
				city.setName(split[2]);
				city.setCapital(Boolean.parseBoolean(split[3]));
				city.setLon(Double.parseDouble(split[4]));
				city.setLat(Double.parseDouble(split[5]));
				city.setNo_accents(split[6]);
				city.setAlternative_names(split[7]);
				city.setMicroregion(split[8]);
				city.setMesoregion(split[9]);
				cityRepository.save(city);
				System.out.print(".");
			}
			br.close();
			System.out.println("\nCARREGAMENTO DE DADOS COMPLETA.");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
