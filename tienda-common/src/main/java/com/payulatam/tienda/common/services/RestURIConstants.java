package com.payulatam.tienda.common.services;

/** 
* Constantes para los path de las capacidades del servicio 
* @author Jorge Riveros
* @author jorge.riveros@payulatam.com* 
*/
public class RestURIConstants {
	
	private  RestURIConstants() {
		
	}
	
	//Path base del servicio
	public static final String PATH = "/tienda";
	//Path para traer toda la lista de metodos de pago
	public static final String GET_ALL_METHODPAY = "/metodosPago";

	//Path para tokenizar
	public static final String TOKEN_PROCESS= "/tokenizar";
		
	/*Path para crear-modificar un cliente
	public static final String CREATE_CUSTOMER = "/cliente/crear";
	//Path para eliminar un cliente
	public static final String DELETE_CUSTOMER = "/cliente/eliminar/{id}";
	
	//Path para traer toda la lista de cuentas
	public static final String GET_ALL_ACCOUNT = "/cuentas";
	//Path para crear-modificar una cuenta
	public static final String CREATE_ACCOUNT = "/cuenta/crear";
	//Path para eliminar una cuenta
	public static final String DELETE_ACCOUNT = "/cuenta/eliminar/{id}";

	//Path para traer toda la lista de movimientos
	public static final String GET_ALL_MOV= "/movimientos";
	//Path para crear un movimientos sin procesar
	public static final String CREATE_MOV = "/movimiento/crear";
	//Path para obtener un reporte
	public static final String GET_REPORT = "/reporte";*/
	
	
}
