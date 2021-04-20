package com.training.hibernate.services;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.training.hibernate.domain.Title;
import com.training.hibernate.repository.IRepository;
import com.training.hibernate.repository.RepositoryTitle;

public class TitleService {

	private SessionFactory sessionFactory;
	private IRepository<Title> titleRepository;

	public TitleService(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		this.titleRepository = new RepositoryTitle();
	}

	public Title addTitle(String name, String type, String country, Date dateAdded, Integer releaseYear, String rating,
			String duration) {
		Session session = this.sessionFactory.openSession();
		session.beginTransaction();

		Title title = new Title(name, type, country, dateAdded, releaseYear, rating, duration);

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
		if(country != null) {
			title.setTitle(country);
		}
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
}
