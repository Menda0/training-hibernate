package com.training.hibernate.domain;

import java.util.HashSet;
import java.util.Set;

public class Country {
	private Integer id;
	private String name;
	
	// Add an entity set for the entity pojo you want to relate
	private Set<Title> titles = new HashSet<Title>();
	
	public Country() {}

	public Country(String name) {
		super();
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Title> getTitles() {
		return titles;
	}

	public void setTitles(Set<Title> titles) {
		this.titles = titles;
	}
}
