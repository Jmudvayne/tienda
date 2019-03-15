package com.payulatam.tienda.common;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Clase para representar una cita por la que se esta realizando el pago
 * @author jorge.riveros
 *
 */
@XmlRootElement
public class Cita {
	
	private Date fechaCita;
	
	private Customer customer;
	
	private int estado;
	
	

}
