package models;

import java.io.Serializable;


public class Game implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String name;
	protected int id;
	protected double price;
	


	public Game(String nombre,  double precioCompra) {
		super();
		this.name = nombre;
		this.price = precioCompra;
	
		
	}

	public Game() {
		super();
		this.name = "";
		this.id = -1;
		this.price = 0.0f;
	
		
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

	
	

	@Override
	public String toString() {
		return "Game [nombre=" + name + ", id=" + id  + ", precioCompra="
				+ price + "]";
	}
	
	
}
