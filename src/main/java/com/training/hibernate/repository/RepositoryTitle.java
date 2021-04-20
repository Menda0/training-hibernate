package com.training.hibernate.repository;

import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;

import com.training.hibernate.domain.Title;

public class RepositoryTitle implements IRepository<Title>{

	@Override
	public Title get(Session session, int id) {
		Title t = (Title) session.get(Title.class, id);
		return t;
	}

	@Override
	public Collection<Title> getAll(Session session) {
		Criteria c = session.createCriteria(Title.class);
		List<Title> titles = c.list();
		return titles;
	}

	@Override
	public Integer saveOrUpdate(Session session, Title t) {
		return (Integer) session.save(t);
	}

	@Override
	public void delete(Session session, Title t) {
		session.delete(t);
	}

}
