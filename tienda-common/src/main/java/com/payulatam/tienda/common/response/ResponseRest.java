package com.payulatam.tienda.common.response;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ResponseRest {
	
	private String codigo;
	private String mensaje;
	
	public ResponseRest() {
		// TODO Auto-generated constructor stub
	}
	
	

	public ResponseRest(String codigo, String mensaje) {
		super();
		this.codigo = codigo;
		this.mensaje = mensaje;
	}



	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	
	

}
