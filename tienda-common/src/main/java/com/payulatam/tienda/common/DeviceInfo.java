package com.payulatam.tienda.common;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Clase que representa el dispositivo desde el que se hace una peticion de pago
 * 
 * @author jorge.riveros
 *
 */
@XmlRootElement
public class DeviceInfo {

	/**
	 * Direccion ip
	 */
	private String ipAddres;
	/**
	 * Session id del dispositivo
	 */
	private String sessionId;
	/**
	 * Cookie de la sesion
	 */
	private String cookie;
	/**
	 * user-agent
	 */
	private String userAgent;

	/**
	 * Constructor sin argumentos
	 */
	public DeviceInfo() {

	}

	/**
	 * Constructor con todos los atributos de la clase
	 * 
	 * @param ipAddres  dirección ip desde la que se hace la solicitud
	 * @param sessionId sessionId desde la que se hace la solicitud
	 * @param cookie    cookie de la sesion acutal
	 * @param userAgent userAgent del dispositivo
	 */
	public DeviceInfo(String ipAddres, String sessionId, String cookie, String userAgent) {
		super();
		this.ipAddres = ipAddres;
		this.sessionId = sessionId;
		this.cookie = cookie;
		this.userAgent = userAgent;
	}

	public String getIpAddres() {
		return ipAddres;
	}

	public void setIpAddres(String ipAddres) {
		this.ipAddres = ipAddres;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getCookie() {
		return cookie;
	}

	public void setCookie(String cookie) {
		this.cookie = cookie;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

}
