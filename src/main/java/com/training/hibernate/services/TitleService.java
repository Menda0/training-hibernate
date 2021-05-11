package com.training.hibernate.services;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.training.hibernate.domain.Country;
import com.training.hibernate.domain.Title;
import com.training.hibernate.repository.IRepository;
import com.training.hibernate.repository.RepositoryCountry;
import com.training.hibernate.repository.RepositoryTitle;

public class TitleService {

	private SessionFactory sessionFactory;
	private IRepository<Title> titleRepository;
	private RepositoryCountry countryRepository;

	public TitleService(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		this.titleRepository = new RepositoryTitle();
		this.countryRepository = new RepositoryCountry();
	}

	public Title addTitle(String name, String type, String country, Date dateAdded, Integer releaseYear, String rating,
			String duration) {
		Session session = this.sessionFactory.openSession();
		session.beginTransaction();
		
		Country countryInDb = this.countryRepository.getByName(session, country);
		
		session.getTransaction().commit();
		session.beginTransaction();
		
		if(countryInDb == null) {
			countryInDb = new Country(country);
			//this.countryRepository.saveOrUpdate(session, countryInDb);
		}
		

		Title title = new Title(name, type, dateAdded, releaseYear, rating, duration);
		title.setCountry(countryInDb);

		this.titleRepository.saveOrUpdate(session, title);

		session.getTransaction().commit();
		session.close();
		return title;
	}

	public Title update(Integer id, String name, String type, String country, Date dateAdded, Integer releaseYear,
			String rating, String duration) {
		Session session = this.sessionFactory.openSession();
		session.beginTransaction();
		
		Title title = this.titleRepository.get(session, id);
		if(name != null) {
			title.setTitle(name);
		}
		if(type != null) {
			title.setType(type);
		}
//		if(country != null) {
//			title.setCountry(country);
//		}
		if(dateAdded != null) {
			title.setDateAdded(dateAdded);
		}
		if(releaseYear != null) {
			title.setReleaseYear(releaseYear);
		}
		if(rating != null) {
			title.setRating(rating);
		}
		if(duration != null) {
			title.setDuration(duration);
		}
		
		this.titleRepository.saveOrUpdate(session, title);
		
		session.getTransaction().commit();
		session.close();
		return title;
	}

	public boolean removeTitle(Integer id) {
		// TODO
		return false;
	}
	
	// Exercise 1 
	// Select * from TITLE
	public List<Title> getAllHql(){
		Session session = this.sessionFactory.openSession();
		session.beginTransaction();
		
		String hql = "FROM Title";
		Query query = session.createQuery(hql);
		
		List<Title> result = (List<Title>)query.list();
		
		session.getTransaction().commit();
		session.close();
		
		return result;
	}
	
	public List<Title> getAllCriteria(){
		Session session = this.sessionFactory.openSession();
		session.beginTransaction();
		
		Criteria cr = session.createCriteria(Title.class);
		List<Title> result = cr.list();
		
		session.getTransaction().commit();
		session.close();
		
		return result;
	}
	
	// Exercise 2
	// Select * from TITLE where t.title = :name
	public Title getTitleByNameHQL(String name) {
		Session session = this.sessionFactory.openSession();
		session.beginTransaction();
		
		String hql = "FROM Title t where t.title = :name";
		Query query = session.createQuery(hql);
		query.setParameter("name", name);
		Title results = (Title)query.uniqueResult();
		
		
		session.getTransaction().commit();
		session.close();
		return results;
	}
	
	// Exercise 3
	// Select * from TITLE where t inner join COUNTRY c on c.country_id = t.country_id where c.name = :name
	public List<Title> getTitlesByCountry(String name){
		Session session = this.sessionFactory.openSession();
		session.beginTransaction();
		
		String hql = "FROM Title as T WHERE T.country.name = :name";
		Query query = session.createQuery(hql);
		query.setParameter("name", name);
		
		List<Title> results = query.list();
		
		session.getTransaction().commit();
		session.close();
		
		return results;
	}
	
	public List<Title> getTitlesByCountryCriteria(String name){
		Session session = this.sessionFactory.openSession();
		session.beginTransaction();
		
		Criteria cr = session.createCriteria(Title.class);
		cr.createCriteria("country", "country");
		cr.add(Restrictions.eq("country.name", name));
		List<Title> results = cr.list();
		
		session.getTransaction().commit();
		session.close();
		
		return results;
	} 
	
}
