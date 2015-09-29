package com.henry.model;

import java.io.Serializable;

public class Station implements Serializable {
	
	static final long serialVersionUID = 20140124L;
	
	private int id;
	private String name;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
