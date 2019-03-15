package com.payulatam.integracion.service.interfaz;

import java.util.List;

import com.payu.sdk.exceptions.ConnectionException;
import com.payu.sdk.exceptions.InvalidParametersException;
import com.payu.sdk.exceptions.PayUException;
import com.payu.sdk.model.CreditCardToken;
import com.payu.sdk.model.PaymentMethodComplete;
import com.payulatam.tienda.common.Customer;
import com.payulatam.tienda.common.Token;
import com.payulatam.tienda.common.request.SolicitudToken;


public interface IServicePayU {
	
	public boolean callPing() throws PayUException, ConnectionException;
	
	public List<PaymentMethodComplete> getMetodosPago() throws PayUException, ConnectionException;
	
	public CreditCardToken tokenizar(SolicitudToken solicitud) throws PayUException, InvalidParametersException, ConnectionException;

	public void eliminarToken(Token token) throws PayUException, InvalidParametersException, ConnectionException;

	public void pagarConToken(Customer customer, Token token)
			throws PayUException, InvalidParametersException, ConnectionException;

	public void pagarSinToken() throws PayUException, InvalidParametersException, ConnectionException;
	
	
	

}
