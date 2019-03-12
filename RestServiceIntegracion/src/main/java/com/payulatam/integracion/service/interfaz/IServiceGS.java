package com.payulatam.integracion.service.interfaz;

import java.util.List;

import com.payulatam.tienda.common.Customer;
import com.payulatam.tienda.common.Token;

public interface IServiceGS {
	
	public List<Customer> getCustomers();
	
	public List<Token> getTokens();
	
	public List<Token> getTokensCustomer(Customer customer);
	
	public List<Token> getTokensCustomer(String customerId);
	
	public Token persistToken(Token token);

}
