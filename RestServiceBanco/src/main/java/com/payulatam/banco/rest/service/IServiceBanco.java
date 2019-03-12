package com.payulatam.banco.rest.service;

import java.util.List;

import com.payulatam.bank.common.Cliente;
import com.payulatam.bank.common.Cuenta;
import com.payulatam.bank.common.Movimiento;
import com.payulatam.bank.common.Reporte;

/*
 * Interfaz que representa las operaciones validas en el servicio
 */
public interface IServiceBanco {

	public Cliente crearCliente(Cliente cliente);

	public Cuenta crearCuenta(Cuenta cuenta);

	public void crearMovimiento(Movimiento movimiento);

	public List<Cliente> getClientes();

	public List<Cuenta> getCuentas();

	public List<Cuenta> obtenerCuentasdeCliente(Cliente cliente);

	public List<Movimiento> obtenerMovimientosReporte(Reporte reporte, Cuenta cuenta, Boolean tipoDebito);

	public List<Movimiento> obtenerMovimientosReporte(Reporte reporte);

	public Reporte generarReporte(Reporte reporte);

	public void eliminarCliente(String id);

	public void eliminarCuenta(String id);

}
