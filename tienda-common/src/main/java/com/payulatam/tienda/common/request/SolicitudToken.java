package com.payulatam.tienda.common.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import com.payulatam.tienda.common.CreditCardData;
import com.payulatam.tienda.common.Customer;

/**
 * Clase que representa una solicutd para tokenizar una tarjeta de credito
 * 
 * @author jorge.riveros
 *
 */
@XmlRootElement
public class SolicitudToken {

	/**
	 * Nombre de la tarjeta o token de facil recordación
	 */
	@NotNull
	@Size(min = 10, max = 60, message = "Nombre de token no válido")
	private String nombreTarjeta;

	/**
	 * Datos de la tarjeta a tokenizar
	 */
	private CreditCardData cardData;

	/**
	 * Datos del cliente
	 */
	private Customer customer;

	public SolicitudToken() {
		this.nombreTarjeta = "";
	}

	public SolicitudToken(String nombreTarjeta) {
		this.nombreTarjeta = nombreTarjeta;

	}

	public String getNombreTarjeta() {
		return nombreTarjeta;
	}

	public void setNombreTarjeta(String nombreTarjeta) {
		this.nombreTarjeta = nombreTarjeta;
	}

	public CreditCardData getCardData() {
		return cardData;
	}

	public void setCardData(CreditCardData cardData) {
		this.cardData = cardData;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	

}
