package com.payulatam.banco.rest.controller;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;


import com.payulatam.bank.common.Cliente;
import com.payulatam.bank.common.Cuenta;
import com.payulatam.bank.common.Movimiento;
import com.payulatam.bank.common.Reporte;
import com.payulatam.banco.rest.service.IServiceBanco;
import com.payulatam.bank.service.RestURIConstants;
import com.payulatam.bank.service.response.ServiceResponse;
import com.payulatam.bank.service.response.ServiceResponseClientes;
import com.payulatam.bank.service.response.ServiceResponseCuentas;
import com.payulatam.bank.service.response.ServiceResponseReporte;

/*
 * Clase que controla todos los accesos al servicio rest
 */
@RestController
@RequestMapping(RestURIConstants.PATH)
public class ControllerService {

	private static final Logger logger = LoggerFactory.getLogger(ControllerService.class);	   

	//Intrefaz del servicio que contiene toda la lógica de negocio
	@Autowired
	IServiceBanco service;
	
	/*
	 * Metodo que recibe las peticiones para crear cliente
	 */
	@PostMapping(path = RestURIConstants.CREATE_CUSTOMER)
	public ServiceResponse crearCliente(@RequestBody Cliente cliente) {
		ServiceResponse respuesta = new ServiceResponse(0, "OK");
		Cliente clienteRs = service.crearCliente(cliente);
		if(clienteRs == null) {
			respuesta = new ServiceResponse(1, "Ya existe un cliente con teléfono " + cliente.getTelefono());
		}
		return respuesta;
	}

	
	/*
	 * Metodo que recibe las peticiones para crear cuenta
	 */
	@PostMapping(path = RestURIConstants.CREATE_ACCOUNT)	
	public ServiceResponse crearCuenta(@RequestBody Cuenta cuenta) {
		ServiceResponse respuesta = new ServiceResponse(0, "OK");
		Cuenta cuentaRs = service.crearCuenta(cuenta);
		if(cuentaRs == null) {
			respuesta = new ServiceResponse(1, "Ya existe una cuenta con número " + cuenta.getNumero());
		}
		return respuesta;
	}

	
	/*
	 * Metodo que recibe las peticiones para crear un movimiento sin procesar
	 */
	@PostMapping(path = RestURIConstants.CREATE_MOV)	
	public ServiceResponse crearMovimiento(@RequestBody Movimiento movimiento) {
		ServiceResponse respuesta = new ServiceResponse(0, "OK");
		if(movimiento.isTipoDebito()) {
			movimiento.setValor(movimiento.getValor().multiply(new BigDecimal(-1)));
		}
		if(movimiento.getCuentaAfectada().getSaldo().add(movimiento.getValor()).doubleValue()< BigDecimal.ZERO.doubleValue()) {
			respuesta = new ServiceResponse(2, "Movimiento no permitido por saldo insuficiente");
		}
		else {
			service.crearMovimiento(movimiento);
			
		}
		//respuesta.setObjeto(movimiento);
		return respuesta;
	}
	
	
	/*
	 * Metodo que recibe las peticiones para obtener todos los clientes
	 */
	@GetMapping(RestURIConstants.GET_ALL_CUSTOMER)
	public ServiceResponseClientes obtenerClientes() {		
		ServiceResponseClientes respuesta = new ServiceResponseClientes(0, "OK", service.getClientes());
		//respuesta.setObjeto(service.getClientes());
		return respuesta;
	}
	
	
	/*
	 * Metodo que recibe las peticiones para obtener todos las cuentas
	 */
	@GetMapping(RestURIConstants.GET_ALL_ACCOUNT)
	public ServiceResponseCuentas obtenerCuentas() {
		ServiceResponseCuentas respuesta = new ServiceResponseCuentas(0, "OK", service.getCuentas());
		//respuesta.setObjeto(service.getCuentas());
		return respuesta;
	}
	
	
	/*
	 * Metodo que recibe las peticiones para crear un reporte
	 */
	@PostMapping(path = RestURIConstants.GET_REPORT)	
	public ServiceResponse crearReporte(@RequestBody Reporte reporte) {
		ServiceResponseReporte respuesta = new ServiceResponseReporte(0, "OK", reporte);
		
		reporte = service.generarReporte(reporte);
		respuesta.setObjeto(reporte);
		return respuesta;
	}
	
	
	/*
	 * Metodo que recibe las peticiones para elimimar cliente
	 */
	@GetMapping(path = RestURIConstants.DELETE_CUSTOMER)
	public ServiceResponse eliminarCliente(@PathVariable String id) {
		ServiceResponse respuesta = new ServiceResponse(0, "OK");
		service.eliminarCliente(id);		
		return respuesta;
	}
	
	
	/*
	 * Metodo que recibe las peticiones para eliminar una cuenta
	 */
	@GetMapping(path = RestURIConstants.DELETE_ACCOUNT)
	public ServiceResponse eliminarCuenta(@PathVariable String id) {
		ServiceResponse respuesta = new ServiceResponse(0, "OK");
		service.eliminarCuenta(id);		
		return respuesta;
	}
	
	
	
	

}
