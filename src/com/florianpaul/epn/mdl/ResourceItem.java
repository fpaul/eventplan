package com.florianpaul.epn.mdl;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.appengine.api.datastore.Key;

@Entity
public class ResourceItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Key key;
	private String text;

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
