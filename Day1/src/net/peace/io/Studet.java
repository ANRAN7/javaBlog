package net.peace.io;

import java.io.Serializable;

public class Studet implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private transient int money;
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
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
	public Studet(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public Studet() {
		super();
	}
}
