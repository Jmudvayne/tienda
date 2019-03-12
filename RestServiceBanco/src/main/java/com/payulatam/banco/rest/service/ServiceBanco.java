package com.payulatam.banco.rest.service;

import org.springframework.stereotype.Service;

import com.j_spaces.core.IJSpace;
import com.j_spaces.core.LeaseContext;
import com.j_spaces.core.client.SQLQuery;
import com.payulatam.bank.common.Cliente;
import com.payulatam.bank.common.Cuenta;
import com.payulatam.bank.common.Movimiento;
import com.payulatam.bank.common.Reporte;

import java.util.Arrays;
import java.util.List;

import org.openspaces.core.GigaSpace;
import org.openspaces.core.GigaSpaceConfigurer;
import org.openspaces.core.context.GigaSpaceContext;
import org.openspaces.core.space.UrlSpaceConfigurer;

/*
 * Implementación de las operacion validas para el servicio
 */
@Service
public class ServiceBanco implements IServiceBanco {

	// Variable que representa un contexto sobre gigaspace
	@GigaSpaceContext
	private GigaSpace gigaSpace;

	/**
	 * Metodo que crear un Cliente sobre el espacio
	 * @param cliente a crear
	 * @return cliente 
	 */
	@Override
	public Cliente crearCliente(Cliente cliente) {
		if (getGigaSpace().readIfExists(cliente) == null) {
			getGigaSpace().write(cliente);
			return cliente;
		} else {
			return null;
		}
	}
	
	/**
	 * Metodo que crear una Cuenta sobre el espacio
	 * @param cuenta a crear
	 * @return cuenta 
	 */
	@Override
	public Cuenta crearCuenta(Cuenta cuenta) {
		if (getGigaSpace().readIfExists(cuenta) == null) {
			getGigaSpace().write(cuenta);
			return cuenta;
		} 
		else
			return null;
	}

	/**
	 * Metodo que crear un Movimiento sobre el espacio
	 * @param movimiento a crear
	 * @return movimiento 
	 */
	@Override
	public void crearMovimiento(Movimiento movimiento) {
		getGigaSpace().write(movimiento);

	}

	@Override
	public List<Cliente> getClientes() {
		return Arrays.asList(getGigaSpace().readMultiple(new Cliente()));
	}

	@Override
	public List<Cuenta> getCuentas() {
		return Arrays.asList(getGigaSpace().readMultiple(new Cuenta()));
	}

	public GigaSpace getGigaSpace() {
		if (gigaSpace == null) 
			iniciarGs();

		return gigaSpace;
	}

	private void iniciarGs() {
		System.out.println("Espacio nulo");
		IJSpace space = new UrlSpaceConfigurer("jini://*/*/space").space();
		// use gigaspace wrapper to for simpler API
		this.gigaSpace = new GigaSpaceConfigurer(space).gigaSpace();
	}

	@Override
	public List<Cuenta> obtenerCuentasdeCliente(Cliente cliente) {
		SQLQuery<Cuenta> query = new SQLQuery<Cuenta>(Cuenta.class, "titular.telefono = ? ");
		query.setParameter(1, cliente.getTelefono());
		
		List<Cuenta> cuentas = Arrays.asList(getGigaSpace().readMultiple(query));
		
		return cuentas;

	}

	@Override
	public List<Movimiento> obtenerMovimientosReporte(Reporte reporte) {

		SQLQuery<Movimiento> query = new SQLQuery<Movimiento>(Movimiento.class, "fecha BETWEEN ? AND ?");
		query.setParameter(1, reporte.getFechaInicial());
		query.setParameter(2, reporte.getFechaFinal());

		return Arrays.asList(getGigaSpace().readMultiple(query));

	}
	
	@Override
	public List<Movimiento> obtenerMovimientosReporte(Reporte reporte, Cuenta cuenta, Boolean tipoDebito) {

		SQLQuery<Movimiento> query = new SQLQuery<Movimiento>(Movimiento.class, "fecha BETWEEN ? AND ? AND tipoDebito = ? AND cuentaAfectada.numero = ?");
		query.setParameter(1, reporte.getFechaInicial());
		query.setParameter(2, reporte.getFechaFinal());
		query.setParameter(3, tipoDebito);
		query.setParameter(4, cuenta.getNumero());

		return Arrays.asList(getGigaSpace().readMultiple(query));

	}
	
	@Override
	public Reporte generarReporte(Reporte reporte) {
		
		reporte.setCuentasCliente(obtenerCuentasdeCliente(reporte.getCliente()));
		for(Cuenta cuenta : reporte.getCuentasCliente()) {
			cuenta.setCreditos(obtenerMovimientosReporte(reporte, cuenta, Boolean.FALSE));
			cuenta.setDebitos(obtenerMovimientosReporte(reporte, cuenta, Boolean.TRUE));
		}
		
		return reporte;
	}
	
	@Override
	public void eliminarCliente(String id) {
		Cliente cliente = new Cliente();
		cliente.setTelefono(id);
		getGigaSpace().clear(getGigaSpace().read(cliente));
	}
	
	@Override
	public void eliminarCuenta(String id) {
		Cuenta cuenta = new Cuenta();
		cuenta.setNumero(id);
		getGigaSpace().clear(getGigaSpace().take(cuenta));
	}
	
	
	
	


}
