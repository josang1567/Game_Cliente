package com.Gamex.Model;

import java.io.Serializable;
import java.util.List;


public class User implements Serializable {
	protected String nombre;
	protected int id;
	protected float saldo;
	protected String password;
	protected boolean Admin;
	protected List<Game> juegos;
	
	
	public User(String nombre, int id, float saldo, String password) {
		super();
		this.nombre = nombre;
		this.id = id;
		this.saldo = saldo;
		this.password = password;
		Admin = false;
		juegos = null;
	}

	public User() {
		super();
		this.nombre = null;
		this.id = -1;
		this.saldo = 0.0f;
		this.password = "******";
		Admin = false;
		juegos = null;

	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getSaldo() {
		return saldo;
	}

	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAdmin() {
		return Admin;
	}

	public void setAdmin(boolean admin) {
		Admin = admin;
	}

	public List<Game> getJuegos() {
		return juegos;
	}

	public void setJuegos(List<Game> carrito) {
		juegos = carrito;
	}
}
