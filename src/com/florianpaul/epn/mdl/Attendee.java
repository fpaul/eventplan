package com.florianpaul.epn.mdl;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.google.appengine.api.datastore.Key;

@Entity
public class Attendee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Key key;
	private Collection<Key> resourceItemKeys = new ArrayList<Key>();

	@ManyToOne(cascade=CascadeType.ALL)
	Event event;

	public Event getEvent() {
		return event;
	}

	public Key getKey() {
		return key;
	}
	
	public void addResourceItem(ResourceItem resourceItem) {
		if(resourceItem==null || resourceItemKeys.contains(resourceItem.getKey())) {
			return;
		}
		resourceItemKeys.add(resourceItem.getKey());
	}
	
	public void removeResourceItem(ResourceItem resourceItem) {
		if(resourceItem==null || !resourceItemKeys.contains(resourceItem.getKey())) {
			return;
		}
		resourceItemKeys.remove(resourceItem.getKey());
	}

	public Collection<Key> getResourceItemKeys() {
		return new ArrayList<Key>(resourceItemKeys);
	}
	
}
