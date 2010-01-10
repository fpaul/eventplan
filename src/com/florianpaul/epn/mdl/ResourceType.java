package com.florianpaul.epn.mdl;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.appengine.api.datastore.Key;

@Entity
public class ResourceType {
	public static enum DataType {
		Text, SingleSelection, MultipleSelection
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Key key;
	private String name;
	private String description;
	private DataType datatype;
	private Collection<ResourceItem> resourceItems = new ArrayList<ResourceItem>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public DataType getDatatype() {
		return datatype;
	}

	public void setDatatype(DataType datatype) {
		this.datatype = datatype;
	}

	public Key getKey() {
		return key;
	}

	public Collection<ResourceItem> getResourceItems() {
		return resourceItems;
	}

}