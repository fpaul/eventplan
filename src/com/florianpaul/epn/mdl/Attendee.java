package com.florianpaul.epn.mdl;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.appengine.api.datastore.Key;

@Entity
public class Attendee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Key key;
	private Collection<ResourceItem> resourceItems = new ArrayList<ResourceItem>();

	public Collection<ResourceItem> getResourceItems() {
		return resourceItems;
	}

	public Key getKey() {
		return key;
	}

}