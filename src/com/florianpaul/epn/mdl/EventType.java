package com.florianpaul.epn.mdl;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.google.appengine.api.datastore.Key;

@Entity
public class EventType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Key key;
	private String name;
	@OneToMany(cascade=CascadeType.ALL,mappedBy="type")
	private Collection<Event> events = new ArrayList<Event>();
	@ManyToMany(cascade=CascadeType.ALL)
	private Collection<ResourceType> resourceTypes = new ArrayList<ResourceType>();
	@ManyToOne(cascade=CascadeType.ALL)
	private Plan plan;

	public Plan getPlan() {
		return plan;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Key getKey() {
		return key;
	}

	public Collection<Event> getEvents() {
		return events;
	}

	public Collection<ResourceType> getResourceTypes() {
		return resourceTypes;
	}

}
