package com.brunno.desafio.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.brunno.desafio.services.DbService;


@Configuration
@Profile("dev")
public class DevConfig {
	
	
	@Autowired
	private DbService dbService;
	
	@Bean
	public boolean instDevDB() throws ParseException {
		dbService.instDevDB();
		
		return true;
	}
}
