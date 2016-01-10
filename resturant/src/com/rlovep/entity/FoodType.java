package com.rlovep.entity;

public class FoodType {
	private int id;
	private String typeName;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	@Override
	public String toString() {
		return "FoodType [id=" + id + ", typeName=" + typeName + "]";
	}
}
