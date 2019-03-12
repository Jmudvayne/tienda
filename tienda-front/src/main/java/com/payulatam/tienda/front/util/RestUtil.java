package com.payulatam.tienda.front.util;



import org.springframework.web.client.RestTemplate;

import com.payulatam.tienda.common.services.ResponseMetodosPago;
import com.payulatam.tienda.common.services.ResponseToken;
import com.payulatam.tienda.common.services.RestURIConstants;
import com.payulatam.tienda.common.services.SolicitudToken;



/**
 * Esta clase se encarga de hacer los llamados al servicio Rest
 * @author: Jorge Riveros
 * @version: 06/03/2019
 */
public class RestUtil {
	// Variable que representa la plantilla utilizada para realizar los llamados
	private static RestTemplate restTemplate = new RestTemplate();
	// Variable que representa la URL del servicio
	public static final String SERVER = "http://localhost:8086"+ RestURIConstants.PATH;
	
	
	/**
     * Metodo que obtiene los métodos de pago disponibles por PayU     * 
     */
	public static ResponseMetodosPago getMetodosPago(){
		return restTemplate.getForObject(SERVER + RestURIConstants.GET_ALL_METHODPAY, ResponseMetodosPago.class);
	}
	
	/**
     * Metodo que hace el llamado para tokenizar 
     */
	public static ResponseToken tokenizarTarjeta(SolicitudToken solicitud){
		return  restTemplate.postForObject(SERVER + RestURIConstants.TOKEN_PROCESS, solicitud, ResponseToken.class);
	}

}
