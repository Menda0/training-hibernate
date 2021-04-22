package com.training.hibernate;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.training.hibernate.services.TitleService;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class Init {
	
	
	static SessionFactory sessionFactory;
	
	private static SessionFactory buildSessionFactory() {
	    // Creating Configuration Instance & Passing Hibernate Configuration File
	    Configuration config = new Configuration();
	    config.configure("hibernate.cfg.xml");

	    // Since Hibernate Version 4.x, ServiceRegistry Is Being Used
	    ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build(); 

	    // Creating Hibernate SessionFactory Instance
	    sessionFactory = config.buildSessionFactory(serviceRegistry);
	    return sessionFactory;
	}

	public static void main(String[] args) throws IOException, CsvException {
		
		SimpleDateFormat formatter = new SimpleDateFormat("MMMM dd, yyyy", Locale.ENGLISH);
		FileReader file = new FileReader("src/main/resources/netflix_titles.csv");
		CSVReader reader = new CSVReader(file);
		
		SessionFactory sessionFactory = buildSessionFactory();
		TitleService titleService = new TitleService(sessionFactory);
		
		List<String[]> rows = reader.readAll();
		
		for (String[] row : rows) {
			String name = row[2];
			String type = row[1];
			String country = row[5];
			Date dateAdded = null;
			try {
				dateAdded = formatter.parse(row[6]);
			} catch (Exception e) {
				System.out.println("Cannot parse date:" + row[6]);
			}

			int releaseYear = Integer.parseInt(row[7]);
			String rating = row[8];
			String duration = row[9];
			titleService.addTitle(name, type, country, dateAdded, releaseYear, rating, duration);
		}
		
	}
}
