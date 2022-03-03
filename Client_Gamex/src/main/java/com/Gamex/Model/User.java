package com.Gamex.Model;

import java.io.Serializable;
import java.util.List;


public class User implements Serializable {
	protected String name;
	protected int id;
	protected double money;
	protected String password;
	protected boolean admin;
	protected List<Game> games;
	
	
	public User(String nombre,  double saldo, String password) {
		super();
		this.name = nombre;
	
		this.money = saldo;
		this.password = password;
		admin = false;
		games = null;
	}

	public User() {
		super();
		this.name = null;
		this.id = -1;
		this.money = 0.0f;
		this.password = "******";
		admin = false;
		games = null;

	}
	
	public String getNombre() {
		return name;
	}

	public void setNombre(String nombre) {
		this.name = nombre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getSaldo() {
		return money;
	}

	public void setSaldo(double saldo) {
		this.money = saldo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		admin = admin;
	}

	public List<Game> getJuegos() {
		return games;
	}

	public void setJuegos(List<Game> carrito) {
		games = carrito;
	}

	@Override
	public String toString() {
		return "User [nombre=" + name + ", saldo=" + money + ", password=" + password + "]";
	}
	
	
}
