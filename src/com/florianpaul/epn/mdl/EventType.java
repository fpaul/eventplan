package com.florianpaul.epn.mdl;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	private Collection<Key> resourceTypeKeys = new ArrayList<Key>();
	@ManyToOne(cascade=CascadeType.ALL)
	Plan plan;

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
	
	public void addEvent(Event event) {
		events.add(event);
		event.type = this;
	}
	
	public void removeEvent(Event event) {
		events.remove(event);
		event.type = null;
	}

	public Collection<Event> getEvents() {
		return new ArrayList<Event>(events);
	}
	
	public void addResourceTypes(Collection<ResourceType> resourceTypes) {
		for(ResourceType rt : resourceTypes) {
			addResourceType(rt);
		}
	}
	
	public void removeResourceTypes(Collection<ResourceType> resourceTypes) {
		for(ResourceType rt : resourceTypes) {
			removeResourceType(rt);
		}
	}

	public void addResourceType(ResourceType resourceType) {
		if(resourceType==null || resourceTypeKeys.contains(resourceType.getKey())) {
			return;
		}
		resourceTypeKeys.add(resourceType.getKey());
		resourceType.addEventType(this);
	}

	public void removeResourceType(ResourceType resourceType) {
		if(resourceType==null || !resourceTypeKeys.contains(resourceType.getKey())) {
			return;
		}
		resourceTypeKeys.remove(resourceType.getKey());
		resourceType.removeEventType(this);
	}
	
	
}
