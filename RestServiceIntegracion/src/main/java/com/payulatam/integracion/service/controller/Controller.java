package com.payulatam.integracion.service.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.payu.sdk.exceptions.ConnectionException;
import com.payu.sdk.exceptions.InvalidParametersException;
import com.payu.sdk.exceptions.PayUException;
import com.payu.sdk.model.CreditCardToken;
import com.payulatam.integracion.service.impl.Service;

import com.payulatam.integracion.service.interfaz.IServiceGS;
import com.payulatam.integracion.service.interfaz.IServicePayU;
import com.payulatam.tienda.common.Token;
import com.payulatam.tienda.common.request.SolicitudToken;
import com.payulatam.tienda.common.response.ResponseMetodosPago;
import com.payulatam.tienda.common.response.ResponseRest;
import com.payulatam.tienda.common.response.ResponseToken;
import com.payulatam.tienda.common.services.RestURIConstants;


@RestController
@RequestMapping("/tienda")
public class Controller {

	private static final Logger logger = LoggerFactory.getLogger(Controller.class);

	@Autowired
	private IServiceGS serviceGS;

	@Autowired
	private IServicePayU servicePayU;

	@Autowired
	private Service service;

	@GetMapping("/ping")
	public boolean pingApi() {
		boolean response = false;
		try {
			/*
			*/
			response = service.callPing();
			service.test2();
			service.test();
			
			//servicePayU.eliminarToken(null);
			servicePayU.pagarConToken(null, null);
			//servicePayU.pagarSinToken();
			

		} catch (PayUException | ConnectionException | InvalidParametersException e) {
			logger.error("Problema al hacder ping", e);
			e.printStackTrace();
		}

		return response;

	}

	@GetMapping("/metodosPago")
	public ResponseMetodosPago obtenerMetodosPago() {

		ResponseMetodosPago response = new ResponseMetodosPago();

		try {
			response.setMetodosPago(Converter.covertirListaMetodosPago(servicePayU.getMetodosPago()));
		} catch (PayUException | ConnectionException e) {

			logger.error("Problema al obtener metodos de pago", e);
			e.printStackTrace();

		}

		return response;
	}

	@GetMapping("/tokens")
	public ResponseToken obtenerTokens() {

		ResponseToken response = new ResponseToken("200", "ok");

		response.getTokens().addAll(serviceGS.getTokens());

		return response;

	}

	@GetMapping("/tokenizar")
	public String tokenizar() {
		String response = "";
		try {
			/*
			*/
			service.token();
			response = service.token().getTokenId();
		} catch (PayUException | ConnectionException | InvalidParametersException e) {

			e.printStackTrace();
			response = e.getMessage();
		}

		return response;

	}

	@PostMapping(path = RestURIConstants.TOKEN_PROCESS)
	public ResponseToken tokenizar(@RequestBody @Valid SolicitudToken solicitud) {
		ResponseToken response = new ResponseToken();
		try {
			CreditCardToken tokenCard = servicePayU.tokenizar(solicitud);

			Token token = new Token();
			token.setCustomer(solicitud.getCustomer());
			token.setTokenId(tokenCard.getTokenId());
			token.setMaskedNumber(tokenCard.getMaskedNumber());
			token.setName(solicitud.getNombreTarjeta());
			token.setPaymentMethod(tokenCard.getPaymentMethod().name());

			token = serviceGS.persistToken(token);

			response.setCodigo("0");
			response.setMensaje("Mensaje de guardado");
			response.getTokens().add(token);

		} catch (PayUException | InvalidParametersException | ConnectionException e) {

			logger.error("Problema al tokenizar", e);
			response.setCodigo("404");
			response.setMensaje(e.getMessage());

		}

		return response;
	}


	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseRest handleException(MethodArgumentNotValidException exception) {

		String errorMsg = exception.getBindingResult().getFieldErrors().stream()
				.map(DefaultMessageSourceResolvable::getDefaultMessage).findFirst().orElse(exception.getMessage());
		if(exception.getBindingResult().getObjectName().equals("solicitudToken"))
			return new ResponseToken(HttpStatus.BAD_REQUEST.value()+"",errorMsg);
		else
			return new ResponseRest(HttpStatus.BAD_REQUEST.value()+"",errorMsg);
	}

}
