package com.payulatam.tienda.common.response;

import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ResponseMetodosPago extends ResponseRest {

	private Set<String> metodosPago;

	public ResponseMetodosPago() {

	}

	public ResponseMetodosPago(String codigo, String mensaje) {
		super(codigo,mensaje);
	}

	public Set<String> getMetodosPago() {
		return metodosPago;
	}

	public void setMetodosPago(Set<String> metodosPago) {
		this.metodosPago = metodosPago;
	}

}
