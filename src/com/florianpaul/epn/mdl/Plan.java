package com.florianpaul.epn.mdl;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.appengine.api.datastore.Key;

@Entity
public class Plan {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Key key;
	private String owner;
	private Collection<EventType> eventTypes = new ArrayList<EventType>();
	private Collection<ResourceType> resourceTypes = new ArrayList<ResourceType>();

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public Key getKey() {
		return key;
	}

	public Collection<EventType> getEventTypes() {
		return eventTypes;
	}

	public Collection<ResourceType> getResourceTypes() {
		return resourceTypes;
	}

}
