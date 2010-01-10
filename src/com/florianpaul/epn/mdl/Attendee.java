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

import com.google.appengine.api.datastore.Key;

@Entity
public class Attendee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Key key;
	@ManyToMany(cascade=CascadeType.ALL)
	private Collection<ResourceItem> resourceItems = new ArrayList<ResourceItem>();
	@ManyToOne(cascade=CascadeType.ALL)
	private Event event;

	public Event getEvent() {
		return event;
	}

	public Collection<ResourceItem> getResourceItems() {
		return resourceItems;
	}

	public Key getKey() {
		return key;
	}

}
