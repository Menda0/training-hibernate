package com.training.hibernate;

import java.util.Date;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.training.hibernate.domain.Title;
import com.training.hibernate.services.TitleService;


public class TitleServiceTest {
	
	// This variable will store the sessionFactory
	// This factory will be use to created the current session of our application
	private SessionFactory sessionFactory;
	
	private TitleService titleService;
	
	private SessionFactory buildSessionFactory() {
	    // Creating Configuration Instance & Passing Hibernate Configuration File
	    Configuration config = new Configuration();
	    config.configure("hibernate.cfg.xml");

	    // Since Hibernate Version 4.x, ServiceRegistry Is Being Used
	    ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build(); 

	    // Creating Hibernate SessionFactory Instance
	    this.sessionFactory = config.buildSessionFactory(serviceRegistry);
	    return this.sessionFactory;
	}
	
	@Before
	public void setUp() {
		this.sessionFactory = this.buildSessionFactory();
		this.titleService = new TitleService(this.sessionFactory);
	}	

	@Test
	public void testAddTitle() {
		String name = "Harry Potter";
    	String type = "Fantasy";
    	String country = "England";
    	Date dateAdded = new Date();
    	Integer releaseYear = 2008;
    	String rating = "5 Stars";
    	String duration = "158 min";
		Title title = this.titleService.addTitle(name, type, country, dateAdded, releaseYear, rating, duration);
		
		Assert.assertNotNull(title.getId());
		Assert.assertEquals(name, title.getTitle());
		Assert.assertEquals(type, title.getType());
	}
	
	@Test
	public void testUpdateTitle() {
		String name = "Harry Potter";
    	String type = "Fantasy";
    	String country = "England";
    	Date dateAdded = new Date();
    	Integer releaseYear = 2008;
    	String rating = "5 Stars";
    	String duration = "158 min";
		Title title = this.titleService.addTitle(name, type, country, dateAdded, releaseYear, rating, duration);
		
		String newName = "Lord of the rings";
		Title titleUpdate = this.titleService.update(title.getId(), newName, null, null, null, null, null, null);
		
		Assert.assertNotNull(titleUpdate);
		Assert.assertEquals(newName, titleUpdate.getTitle());
		Assert.assertEquals(title.getCountry(), titleUpdate.getCountry());
	}
	
}
