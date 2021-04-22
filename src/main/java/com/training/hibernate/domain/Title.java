package com.training.hibernate.domain;

import java.util.Date;

public class Title {

	private Integer id;
	private String title;
	private String type;
	private Date dateAdded;
	private Integer releaseYear;
	private String rating;
	private String duration;
	
	// Add the entitie pojo you want to relate
	private Country country;
	
	public Title() {}

	public Title(String title, String type, Date dateAdded, Integer releaseYear,
			String rating, String duration) {
		super();
		this.title = title;
		this.type = type;
		this.dateAdded = dateAdded;
		this.releaseYear = releaseYear;
		this.rating = rating;
		this.duration = duration;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(Integer releaseYear) {
		this.releaseYear = releaseYear;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}
	
	
}
