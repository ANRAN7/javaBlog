package com.rlovep.many2many;

import java.util.HashSet;
import java.util.Set;

public class Project {
       private int id;
       private String name;
       private Set<Developer> developers=new HashSet<>();
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
	public Set<Developer> getDevelopers() {
		return developers;
	}
	public void setDevelopers(Set<Developer> developers) {
		this.developers = developers;
	}
}
