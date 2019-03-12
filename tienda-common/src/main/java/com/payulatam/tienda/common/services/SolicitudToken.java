package com.payulatam.tienda.common.services;

import javax.xml.bind.annotation.XmlRootElement;

import com.payulatam.tienda.common.Customer;

@XmlRootElement
public class SolicitudToken {
	
	private String nombreTarjeta;
	private String numeroTarjeta;
	private String fechaExp;
	private String metodoPago;
	private Customer customer;
	
	public SolicitudToken() {
		// TODO Auto-generated constructor stub
	}
	
	public SolicitudToken(String nombreTarjeta, String numeroTarjeta, String fechaExp, String metodoPago,
			Customer customer) {
		super();
		this.nombreTarjeta = nombreTarjeta;
		this.numeroTarjeta = numeroTarjeta;
		this.fechaExp = fechaExp;
		this.metodoPago = metodoPago;
		this.customer = customer;
	}

	public String getNombreTarjeta() {
		return nombreTarjeta;
	}

	public void setNombreTarjeta(String nombreTarjeta) {
		this.nombreTarjeta = nombreTarjeta;
	}

	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}

	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

	public String getFechaExp() {
		return fechaExp;
	}

	public void setFechaExp(String fechaExp) {
		this.fechaExp = fechaExp;
	}

	public String getMetodoPago() {
		return metodoPago;
	}

	public void setMetodoPago(String metodoPago) {
		this.metodoPago = metodoPago;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	
	

}
