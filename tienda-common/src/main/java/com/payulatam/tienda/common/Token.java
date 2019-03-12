package com.payulatam.tienda.common;

import com.gigaspaces.annotation.pojo.SpaceClass;
import com.gigaspaces.annotation.pojo.SpaceId;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.OneToOne;

/**
 * Clase que representa un objeto token
 */
@Entity
@Table(name="TOKEN")
@SpaceClass
@XmlRootElement
public class Token {

    @Id
    private String id;

    private String maskedNumber;

    private String  paymentMethod;
    
    private String  tokenId;
    
    private String name;
    
    private Customer customer;
    

    /**
     * Constructs a new Token object.
     */
    public Token() {

    }

        
    /**
     * The id of this object.
     */
    @SpaceId(autoGenerate=true)
    public String getId() {
        return id;
    }

    /**
     * The id of this object. Its value will be auto generated when it is written
     * to the space.
     */
    public void setId(String id) {
        this.id = id;
    }

   
    /**
     * The card maskedNumber
     */
    public String getMaskedNumber() {
		return maskedNumber;
	}


	public void setMaskedNumber(String maskedNumber) {
		this.maskedNumber = maskedNumber;
	}

	/**
     * The paymentMethod
     */
	public String getPaymentMethod() {
		return paymentMethod;
	}


	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	/**
     * The tokenId
     */
	public String getTokenId() {
		return tokenId;
	}


	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	
	/**
	 * The name of token
	 */ 
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	
	/**
	 * The customer who owns the token
	 */
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="idCustomer")
	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public String toString() {
        return "id[" + id + "] maskedNumber[" + maskedNumber+ "] paymentMethod[" + paymentMethod+ "] tokenId[" + tokenId+ "]";
    }
}
