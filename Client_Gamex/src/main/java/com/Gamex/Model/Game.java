package com.Gamex.Model;

import java.io.Serializable;
import java.util.Date;

public class Game implements Serializable {
	protected String nombre;
	protected int id;
	protected double precioCompra;
	protected String caratula;


	public Game(String nombre, int id,  double precioCompra, String caratula) {
		super();
		this.nombre = nombre;
		this.id = id;
		this.precioCompra = precioCompra;
		this.caratula = caratula;
		
	}

	public Game() {
		super();
		this.nombre = "";
		this.id = -1;
		this.precioCompra = 0.0f;
		this.caratula = "";
		
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

	
	

	public double getPrecioCompra() {
		return precioCompra;
	}

	public void setPrecioCompra(double precioCompra) {
		this.precioCompra = precioCompra;
	}

	public String getCaratula() {
		return caratula;
	}

	public void setCaratula(String caratula) {
		this.caratula = caratula;
	}

	

	@Override
	public String toString() {
		return "Game [nombre=" + nombre + ", id=" + id  + ", precioCompra="
				+ precioCompra + ", caratula=" + caratula  + "]";
	}
	
	
}
