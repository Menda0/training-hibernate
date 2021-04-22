package com.training.hibernate.repository;

import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.training.hibernate.domain.Country;

public class RepositoryCountry implements IRepository<Country>{

	@Override
	public Country get(Session session, int id) {
		return (Country) session.get(Country.class, id);
	}

	@Override
	public Collection<Country> getAll(Session session) {
		Criteria c = session.createCriteria(Country.class);
		return c.list();
	}

	@Override
	public Integer saveOrUpdate(Session session, Country t) {
		return (Integer) session.save(t);
	}

	@Override
	public void delete(Session session, Country id) {
		session.delete(id);
	}

	public Country getByName(Session session, String name) {
		Criteria c = session.createCriteria(Country.class);
		c.add(Restrictions.eq("name", name));
		
		Country results = (Country) c.uniqueResult();
		return results;
	}
}
