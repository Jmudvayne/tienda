package com.payulatam.tienda.common.response;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.payulatam.tienda.common.Token;

@XmlRootElement
public class ResponseToken extends ResponseRest{
	
	private List<Token> tokens;
	
	public ResponseToken() {
		tokens = new ArrayList<Token>();
	}
	
	

	public ResponseToken(String codigo, String mensaje) {
		super(codigo, mensaje);
		tokens = new ArrayList<Token>();
	}

	
	public List<Token> getTokens() {
		return tokens;
	}

	public void setTokens(List<Token> tokens) {
		this.tokens = tokens;
	}
	
	
	
	
	
	

}
