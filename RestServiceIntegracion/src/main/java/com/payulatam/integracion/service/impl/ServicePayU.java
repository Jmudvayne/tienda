package com.payulatam.integracion.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.payu.sdk.PayU;
import com.payu.sdk.PayUPayments;
import com.payu.sdk.PayUTokens;
import com.payu.sdk.exceptions.ConnectionException;
import com.payu.sdk.exceptions.InvalidParametersException;
import com.payu.sdk.exceptions.PayUException;
import com.payu.sdk.model.CreditCardToken;
import com.payu.sdk.model.Language;
import com.payu.sdk.model.PaymentMethodComplete;
import com.payulatam.integracion.service.interfaz.IServicePayU;
import com.payulatam.tienda.common.Customer;
import com.payulatam.tienda.common.services.SolicitudToken;

@Service
public class ServicePayU implements IServicePayU {

	private static final Logger logger = LoggerFactory.getLogger(ServicePayU.class);

	public ServicePayU() {
		PayU.apiKey = "4Vj8eK4rloUd272L48hsrarnUA"; // Ingresa aquí tu apiKey.
		PayU.apiLogin = "pRRXKOl8ikMmt9u"; // Ingresa aquí tu apiLogin.
		PayU.language = Language.es; // Ingresa aquí el idioma que prefieras.
		PayU.isTest = true; // Dejarlo verdadero cuando sean pruebas.
		PayU.paymentsUrl = "https://sandbox.api.payulatam.com/payments-api/";
	}

	@Override
	public boolean callPing() throws PayUException, ConnectionException {
		return PayUPayments.doPing();
	}

	@Override
	public List<PaymentMethodComplete> getMetodosPago() throws PayUException, ConnectionException {
		return PayUPayments.getPaymentMethods().stream().filter(x -> x.getCountry().equals("CO"))
				.collect(Collectors.toList());
	}

	@Override
	public CreditCardToken tokenizar(SolicitudToken solicitud)
			throws PayUException, InvalidParametersException, ConnectionException {
		CreditCardToken response = null;

		if (solicitud.getCustomer() == null)
			solicitud.setCustomer(new Customer("10", "Jorge Riveros", "1018405648", "Bogotá", "Calle falsa 123",
					"3014437174", false));

		Map<String, String> parameters = new HashMap<String, String>();
		// Ingrese aquí el nombre del pagador.
		parameters.put(PayU.PARAMETERS.PAYER_NAME, solicitud.getCustomer().getName());
		// Ingrese aquí el identificador del pagador.
		parameters.put(PayU.PARAMETERS.PAYER_ID, solicitud.getCustomer().getIdCustomer());
		// Ingrese aquí el documento de identificación del comprador.
		parameters.put(PayU.PARAMETERS.PAYER_DNI, solicitud.getCustomer().getDni());
		// Ingrese aquí el número de la tarjeta de crédito
		parameters.put(PayU.PARAMETERS.CREDIT_CARD_NUMBER, solicitud.getNumeroTarjeta());
		// Ingrese aquí la fecha de vencimiento de la tarjeta de crédito
		parameters.put(PayU.PARAMETERS.CREDIT_CARD_EXPIRATION_DATE, solicitud.getFechaExp());
		// Ingrese aquí el nombre de la tarjeta de crédito
		parameters.put(PayU.PARAMETERS.PAYMENT_METHOD, solicitud.getMetodoPago());

		response = PayUTokens.create(parameters);

		return response;
	}

}
