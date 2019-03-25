package br.com.cities.backend;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@Configurable
@SpringBootApplication
public class Application {
	
	public static void main(String[] args) {
		String loadDatabase = System.getProperty("loaddatabase");
		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
		
		if(loadDatabase != null) {
			context.getBean(LoadData.class).loadDataToDatabase(loadDatabase);
		}
	}
}
