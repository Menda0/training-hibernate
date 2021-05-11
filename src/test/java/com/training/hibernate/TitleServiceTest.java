package com.training.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.training.hibernate.domain.Country;
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
		
		Country c = title.getCountry();
    	
    	Assert.assertNotNull(c);
    	Assert.assertNotNull(c.getId());
		
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
		Assert.assertEquals(title.getCountry().getId(), titleUpdate.getCountry().getId());
	}
	
	@Test
	public void testGetTitles() {
		List<Title> titles1 = this.titleService.getAllHql();
		List<Title> titles2 = this.titleService.getAllCriteria();
		
		Assert.assertTrue(titles1.size() > 0);
		Assert.assertTrue(titles2.size() > 0);
	}
	
	@Test
	public void testGetTitleByName() {
		
		String name = "American History X";
    	String type = "Fantasy";
    	String country = "England";
    	Date dateAdded = new Date();
    	Integer releaseYear = 2008;
    	String rating = "5 Stars";
    	String duration = "158 min";
		Title title = this.titleService.addTitle(name, type, country, dateAdded, releaseYear, rating, duration);
		
		
		Title t = this.titleService.getTitleByNameHQL("American History X");
		
		Assert.assertNotNull(t.getId());
		Assert.assertEquals("American History X", t.getTitle());
		
	}
	
	@Test
	public void testGetTitleByCountry() {
	
		List<Title> titles = this.titleService.getTitlesByCountry("Japan");
		List<Title> titlesByCriteria = this.titleService.getTitlesByCountryCriteria("Japan");
		
		Assert.assertTrue(titles.size() > 0);
		Assert.assertEquals(titles.get(0).getCountry().getName(), "Japan");
		Assert.assertTrue(titlesByCriteria.size() > 0);
		Assert.assertEquals(titlesByCriteria.get(0).getCountry().getName(), "Japan");
		
	}

	
}
