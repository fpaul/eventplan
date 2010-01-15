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
public class Event {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Key key;
	private String title;
	@OneToMany(cascade=CascadeType.ALL,mappedBy="event")
	private Collection<Attendee> attendees = new ArrayList<Attendee>();
	@ManyToOne(cascade=CascadeType.ALL)
	EventType type;

	public EventType getType() {
		return type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public void addAttendee(Attendee attendee) {
		attendees.add(attendee);
		attendee.event = this;
	}
	
	public void removeAttendee(Attendee attendee) {
		attendees.remove(attendee);
		attendee.event = null;
	}

	public Collection<Attendee> getAttendees() {
		return new ArrayList<Attendee>(attendees);
	}

	public Key getKey() {
		return key;
	}

}
