package com.osmand.spring.react.example.bootstrap;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class BootstrapCLR implements CommandLineRunner {
	
	

	@Override
	public void run(String... args) throws Exception {
				
		// create movie list
		
		List<String> movies = new ArrayList<>();
		movies.add("Lincoln Lawyer");
		movies.add("Independence Day");
		movies.add("Terminator");
		movies.add("Apollo 13");
		movies.add("Back to Future");
		movies.add("Wizard of Oz");		
		movies.add("Despicables");
		movies.add("Lord of the Rings");
		movies.add("Jack Ryan");
		movies.add("Batman");
		movies.add("Day After Tomorrow");
		
		

	}

}
