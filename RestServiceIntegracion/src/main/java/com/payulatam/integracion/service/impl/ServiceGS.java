package com.payulatam.integracion.service.impl;

import java.util.Arrays;
import java.util.List;

import org.openspaces.core.GigaSpace;
import org.openspaces.core.GigaSpaceConfigurer;
import org.openspaces.core.context.GigaSpaceContext;
import org.openspaces.core.space.UrlSpaceConfigurer;
import org.springframework.stereotype.Service;

import com.j_spaces.core.IJSpace;
import com.j_spaces.core.client.SQLQuery;

import com.payulatam.integracion.service.interfaz.IServiceGS;
import com.payulatam.tienda.common.Customer;
import com.payulatam.tienda.common.Token;

@Service
public class ServiceGS implements IServiceGS {

	// Variable que representa un contexto sobre gigaspace
	@GigaSpaceContext
	private GigaSpace gigaSpace;

	@Override
	public List<Customer> getCustomers() {
		return Arrays.asList(getGigaSpace().readMultiple(new Customer()));
	}
	
	@Override
	public List<Token> getTokens() {
		return Arrays.asList(getGigaSpace().readMultiple(new Token()));
	}

	@Override
	public List<Token> getTokensCustomer(Customer customer) {
		SQLQuery<Token> query = new SQLQuery<>(Token.class, "customer.id= ?");
		query.setParameter(1, customer.getIdCustomer());
		return Arrays.asList(getGigaSpace().readMultiple(query));
		
	}

	@Override
	public List<Token> getTokensCustomer(String customerId) {
		SQLQuery<Token> query = new SQLQuery<>(Token.class, "customer.id= ?");
		query.setParameter(1, customerId);
		return Arrays.asList(getGigaSpace().readMultiple(query));
	}

	@Override
	public Token persistToken(Token token) {
		getGigaSpace().write(token);
		return token;
	}
	
	
	
	private GigaSpace getGigaSpace() {
		if (gigaSpace == null) 
			iniciarGs();

		return gigaSpace;
	}

	private void iniciarGs() {
		System.out.println("Espacio nulo");
		IJSpace space = new UrlSpaceConfigurer("jini://*/*/spaceCommerce").space();
		// use gigaspace wrapper to for simpler API
		this.gigaSpace = new GigaSpaceConfigurer(space).gigaSpace();
	}

	


}
