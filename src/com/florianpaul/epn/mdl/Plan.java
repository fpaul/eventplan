package com.florianpaul.epn.mdl;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.google.appengine.api.datastore.Key;

@Entity
public class Plan {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Key key;
	private String owner;
	@OneToMany(cascade=CascadeType.ALL,mappedBy="plan")
	private Collection<EventType> eventTypes = new ArrayList<EventType>();
	@OneToMany(cascade=CascadeType.ALL,mappedBy="plan")
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
	
	public void addEventType(EventType eventType) {
		eventTypes.add(eventType);
		eventType.plan = this;
	}
	
	public void removeEventType(EventType eventType) {
		eventTypes.remove(eventType);
		eventType.plan = null;
	}

	public Collection<EventType> getEventTypes() {
		return new ArrayList<EventType>(eventTypes);
	}
	
	public void addResourceType(ResourceType resourceType) {
		resourceTypes.add(resourceType);
		resourceType.plan = this;
	}
	
	public void removeResourceType(ResourceType resourceType) {
		resourceTypes.remove(resourceType);
		resourceType.plan = null;
	}
	
	public Collection<ResourceType> getResourceTypes() {
		return new ArrayList<ResourceType>(resourceTypes);
	}

}
