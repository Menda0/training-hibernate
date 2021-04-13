package com.training.hibernate.repository;

import java.util.Collection;
import java.util.Optional;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Title saveOrUpdate(Session session, Title t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Session session, Integer id) {
		// TODO Auto-generated method stub
		
	}

}
