package com.payulatam.tienda.common.request;

import javax.xml.bind.annotation.XmlRootElement;

import com.payulatam.tienda.common.Customer;
import com.payulatam.tienda.common.DeviceInfo;
import com.payulatam.tienda.common.Token;

/**
 * Clas que representa una solicitud de pago con token previamente registrado
 * 
 * @author jorge.riveros
 *
 */
@XmlRootElement
public class SolicitudPagoToken {
	/**
	 * Información del disposivo desde el que se realiza el pago
	 */
	private DeviceInfo deviceInfo;
	/**
	 * Información del cliente que realiza el pago
	 */
	private Customer customer;
	/**
	 * Información del Token con el que se realiza el pago
	 */
	private Token token;
	
	
	/**
	 * Constructor sin argumentos
	 */
	public SolicitudPagoToken() {
		// TODO Auto-generated constructor stub
	}

}
