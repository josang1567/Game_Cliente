package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class User implements Serializable {
	/**
	 * 
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String username;
	protected int id;
	protected double money;
	protected String password;
	protected boolean admin;
	protected List<Game> games;
	
	
	public User(String nombre,  double saldo, String password) {
		super();
		this.username = nombre;
	
		this.money = saldo;
		this.password = password;
		admin = false;
		games = null;
	}

	public User() {
		super();
		this.games=new ArrayList<Game>();

	}
	
	public String getNombre() {
		return username;
	}

	public void setNombre(String nombre) {
		this.username = nombre;
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
		return "User [nombre=" + username + ", saldo=" + money + ", password=" + password + "]";
	}
	
	
}
