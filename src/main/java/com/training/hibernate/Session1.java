package com.training.hibernate;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.training.hibernate.domain.Title;

/**
 * Hello world!
 *
 */
public class Session1 
{
	
	// This variable will store the current session
	static Session session;
	// This variable will store the sessionFactory
	// This factory will be use to created the current session of our application
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
	
    public static void main( String[] args )
    {
    	
    	buildSessionFactory();
    	
    	session = sessionFactory.openSession();
    	session.beginTransaction();
    	
    	String title = "Harry Potter";
    	String type = "Fantasy";
    	// String country = "England";
    	Date dateAdded = new Date();
    	Integer releaseYear = 2008;
    	String rating = "5 Stars";
    	String duration = "158 min";
    	
    	Title movie = new Title(title, type, dateAdded, releaseYear, rating, duration);	
    	session.saveOrUpdate(movie);
    	
        System.out.println( "Hello World!" );
        
        session.getTransaction().commit();
        session.close();
    }
}
