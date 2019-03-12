package com.payulatam.integracion.service.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.payu.sdk.model.PaymentMethodComplete;

public class Converter {
	
	public static Set<String> covertirListaMetodosPago(List<PaymentMethodComplete> metodosPago){
		Set<String> respuesta = new HashSet<>();
		for(PaymentMethodComplete metodoPago: metodosPago) {
			respuesta.add(metodoPago.getDescription());
		}
		return respuesta;
	}

}
