package com.training.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.training.hibernate.domain.Country;
import com.training.hibernate.repository.RepositoryCountry;


public class CountryRepositoryTest {
	
	// This variable will store the sessionFactory
	// This factory will be use to created the current session of our application
	private SessionFactory sessionFactory;
	
	private RepositoryCountry countryRepository;
	
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
		this.countryRepository = new RepositoryCountry();
	}	

	@Test
	public void testHelloWorld() {
		String helloworld = "helloworld";
		
		Assert.assertEquals(helloworld , "helloworld");
	}
	
	@Test
	public void createAndGetCountry() {
		Session session = sessionFactory.openSession();
    	session.beginTransaction();
    	
    	Country c1 = new Country("Portugal");
    	
    	this.countryRepository.saveOrUpdate(session, c1);
    	
    	Country c2 = this.countryRepository.getByName(session, "Portugal");
    	
    	Assert.assertNotNull(c2);
    	Assert.assertEquals(c1.getId(), c2.getId());
    	
    	session.getTransaction().commit();
    	session.close();
	}
	
}
