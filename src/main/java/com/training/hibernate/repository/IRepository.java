package com.training.hibernate.repository;

import java.util.Collection;
import java.util.Optional;

import org.hibernate.Session;

public interface IRepository<T> {
	// GET, GET_ALL, SAVE_OR_UPDATE, DELETE
	
	// Get entity by ID
	T get(Session session, int id);
	
	// Get all entities on database (Select * from T)
	Collection<T> getAll(Session session);
	
	// Add or update entity on database
	T saveOrUpdate(Session session, T t);
	
	// Remove entity from database
	void delete(Session session, Integer id);
}
