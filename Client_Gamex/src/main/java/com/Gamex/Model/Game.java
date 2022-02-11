package com.Gamex.Model;

import java.io.Serializable;
import java.util.Date;

public class Game implements Serializable {
	protected String nombre;
	protected int id;
	protected float precioVenta;
	protected float precioCompra;
	protected String caratula;
	protected Date fechaCompra;
}
