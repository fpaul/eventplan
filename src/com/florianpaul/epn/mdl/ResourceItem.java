package com.florianpaul.epn.mdl;

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
public class ResourceItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Key key;
	private String text;
	@ManyToOne(cascade=CascadeType.ALL)
	private ResourceType type;
	@ManyToMany(mappedBy="resourceItems")
	Collection<Attendee> attendees;

	public ResourceType getType() {
		return type;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Key getKey() {
		return key;
	}

}
