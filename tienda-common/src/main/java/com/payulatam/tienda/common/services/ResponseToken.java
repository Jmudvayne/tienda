package com.payulatam.tienda.common.services;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.payulatam.tienda.common.Token;

@XmlRootElement
public class ResponseToken {
	
	private int codigo;
	private String mensaje;
	
	private List<Token> tokens;
	
	public ResponseToken() {
		tokens = new ArrayList<Token>();
	}
	
	

	public ResponseToken(int codigo, String mensaje) {
		super();
		this.codigo = codigo;
		this.mensaje = mensaje;
		tokens = new ArrayList<Token>();
	}



	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public List<Token> getTokens() {
		return tokens;
	}

	public void setTokens(List<Token> tokens) {
		this.tokens = tokens;
	}
	
	
	
	
	
	

}
