package com.payulatam.tienda.common;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Clase que representa los datos de una tarjeta de crédito con la que se
 * realiza un pago
 * 
 * @author jorge.riveros
 *
 */
@XmlRootElement
public class CreditCardData {

	/**
	 * Número de la tarjeta de crédito
	 */
	@NotNull(message = "Por favor ingrese el número de la tarjeta")
	@Size(min = 13, max = 20, message = "Número de tarjeta no válido")
	private String cardNumber;

	/**
	 * Fecha de expiración de la tarjeta de crédito
	 */
	@NotNull(message = "Por favor ingrese la fecha de expiración de la tarjeta")
	@Pattern(regexp = "^[2][0-9]{3}\\/[0-9]{2}", message = "Por favor utilice el formato YYYY/MM para la fecha de expiración")
	private String cardExpirationDate;

	/**
	 * Código de seguridad
	 */
	@Null
	@Size(min = 3, max = 3, message = "Código de seguridad no válido")
	private String securityCode;

	/**
	 * Método de pago
	 */
	@NotNull(message = "Por favor ingrese un método de pago")
	private String paymentMethod;

	/**
	 * Constructor sin argumentos
	 */
	public CreditCardData() {
		this.cardNumber = "";
		this.cardExpirationDate = "";
		this.securityCode = "";
		this.paymentMethod = "";
		

	}

	/**
	 * Constructor con todos los atributos
	 * @param cardNumber
	 * @param cardExpirationDate
	 * @param securityCode
	 * @param paymentMethod
	 */
	public CreditCardData(String cardNumber, String cardExpirationDate, String securityCode, String paymentMethod) {
		super();
		this.cardNumber = cardNumber;
		this.cardExpirationDate = cardExpirationDate;
		this.securityCode = securityCode;
		this.paymentMethod = paymentMethod;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCardExpirationDate() {
		return cardExpirationDate;
	}

	public void setCardExpirationDate(String cardExpirationDate) {
		this.cardExpirationDate = cardExpirationDate;
	}

	public String getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

}
