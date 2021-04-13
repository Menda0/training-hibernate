package com.training.hibernate;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.training.hibernate.domain.Title;
import com.training.hibernate.repository.IRepository;
import com.training.hibernate.repository.RepositoryTitle;


public class TitleRepositoryTest {
	
	// This variable will store the sessionFactory
	// This factory will be use to created the current session of our application
	private SessionFactory sessionFactory;
	
	private IRepository<Title> titleRepository;
	
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
		this.titleRepository = new RepositoryTitle();
	}	

	@Test
	public void testHelloWorld() {
		String helloworld = "helloworld";
		
		Assert.assertEquals(helloworld , "helloworld");
	}
	
	@Test
	public void testGetTitle() {
		Session session = sessionFactory.openSession();
    	session.beginTransaction();
    	
    	String title = "Harry Potter";
    	String type = "Fantasy";
    	String country = "England";
    	Date dateAdded = new Date();
    	Integer releaseYear = 2008;
    	String rating = "5 Stars";
    	String duration = "158 min";
    	
    	Title movie = new Title(title, type, country, dateAdded, releaseYear, rating, duration);
    	
    	Assert.assertNull(movie.getId());
    	session.saveOrUpdate(movie);
    	session.getTransaction().commit();
    	
    	Title movieFromDatabase = this.titleRepository.get(session, movie.getId());
    	Assert.assertNotNull(movieFromDatabase);
    	Assert.assertEquals(movie.getId(), movieFromDatabase.getId());
    	
    	session.close();
    	
    	Assert.assertNotNull(movie.getId());
	}
}
