package com.payulatam.tienda.front.util;



import java.io.IOException;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import com.payulatam.tienda.common.request.SolicitudToken;
import com.payulatam.tienda.common.response.ResponseMetodosPago;
import com.payulatam.tienda.common.response.ResponseToken;

import com.payulatam.tienda.common.services.RestURIConstants;



/**
 * Esta clase se encarga de hacer los llamados al servicio Rest
 * @author: Jorge Riveros
 * @version: 06/03/2019
 */
public class RestUtil {
	// Variable que representa la plantilla utilizada para realizar los llamados
	private static RestTemplate restTemplate;
	// Variable que representa la URL del servicio
	public static final String SERVER = "http://localhost:8086"+ RestURIConstants.PATH;
	
	
	/**
     * Metodo que obtiene los métodos de pago disponibles por PayU     * 
     */
	public static ResponseMetodosPago getMetodosPago(){

		return getRestTemplate().getForObject(SERVER + RestURIConstants.GET_ALL_METHODPAY, ResponseMetodosPago.class);
	}
	
	/**
     * Metodo que hace el llamado para tokenizar 
     */
	public static ResponseToken tokenizarTarjeta(SolicitudToken solicitud){
		
		return  getRestTemplate().postForObject(SERVER + RestURIConstants.TOKEN_PROCESS, solicitud, ResponseToken.class);
		
	}
	
	/**
     * Metodo que obtiene los métodos de pago disponibles por PayU     * 
     */
	public static ResponseToken getTokens(){
		return getRestTemplate().getForObject(SERVER + RestURIConstants.GET_ALL_TOKENS, ResponseToken.class);
	}
	
	
	private static RestTemplate getRestTemplate() {
		if(restTemplate== null) {
			restTemplate = new RestTemplate();			
			restTemplate.setErrorHandler(new ResponseErrorHandler() {
				
				@Override
				public boolean hasError(ClientHttpResponse response) throws IOException {					
					return false;
				}
				
				@Override
				public void handleError(ClientHttpResponse response) throws IOException {					
				}
			});
		}
		return restTemplate;
		
	}

}
