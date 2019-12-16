package com.yinhe.bean;

import java.util.ArrayList;
import java.util.Date;

public class Orders {

	 private String oid;
	 
	 private Date ordertime;
	 
	 private double total;
	 
	 private int state;
	 
	 private String address;
	 
	 private String name;
	 
	 private String telephone;
	 
	 private String uid;
	 
	 private  User user;
	 
	 public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	private ArrayList<Orderitem> items;

	public ArrayList<Orderitem> getItems() {
		return items;
	}

	public void setItems(ArrayList<Orderitem> items) {
		this.items = items;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public Date getOrdertime() {
		return ordertime;
	}

	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}
	 
	 
	 
}
