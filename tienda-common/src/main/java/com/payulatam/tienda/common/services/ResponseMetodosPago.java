package com.payulatam.tienda.common.services;

import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ResponseMetodosPago {

	private int code;
	private String message;
	private Set<String> metodosPago;

	public ResponseMetodosPago() {
		// TODO Auto-generated constructor stub
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Set<String> getMetodosPago() {
		return metodosPago;
	}

	public void setMetodosPago(Set<String> metodosPago) {
		this.metodosPago = metodosPago;
	}
	
	

}
