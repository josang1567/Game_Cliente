package com.Gamex.Model;

import java.io.Serializable;
import java.util.Date;

public class Game implements Serializable {
	protected String name;
	protected int id;
	protected double price;
	protected String photo;


	public Game(String nombre,  double precioCompra, String caratula) {
		super();
		this.name = nombre;
		this.price = precioCompra;
		this.photo = caratula;
		
	}

	public Game() {
		super();
		this.name = "";
		this.id = -1;
		this.price = 0.0f;
		this.photo = "";
		
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

	
	

	public double getPrecioCompra() {
		return price;
	}

	public void setPrecioCompra(double precioCompra) {
		this.price = precioCompra;
	}

	public String getCaratula() {
		return photo;
	}

	public void setCaratula(String caratula) {
		this.photo = caratula;
	}

	

	@Override
	public String toString() {
		return "Game [nombre=" + name + ", id=" + id  + ", precioCompra="
				+ price + ", caratula=" + photo  + "]";
	}
	
	
}
