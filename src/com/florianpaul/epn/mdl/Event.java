package com.florianpaul.epn.mdl;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.appengine.api.datastore.Key;

@Entity
public class Event {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Key key;
	private String title;
	private Collection<Attendee> attendees = new ArrayList<Attendee>();

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Collection<Attendee> getAttendees() {
		return attendees;
	}

	public Key getKey() {
		return key;
	}

}
