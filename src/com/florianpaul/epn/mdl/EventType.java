package com.florianpaul.epn.mdl;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.appengine.api.datastore.Key;

@Entity
public class EventType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Key key;
	private String name;
	private Collection<Event> events = new ArrayList<Event>();
	private Collection<ResourceType> resourceTypes = new ArrayList<ResourceType>();

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
