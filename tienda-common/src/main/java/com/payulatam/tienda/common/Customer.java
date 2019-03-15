package com.payulatam.tienda.common;

import com.gigaspaces.annotation.pojo.SpaceClass;
import com.gigaspaces.annotation.pojo.SpaceId;
import com.gigaspaces.annotation.pojo.SpaceRouting;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.persistence.Id;

/**
 * A simple object used to work with the Space. Important properties include the id
 * of the object, a type (used to perform routing when working with partitioned space),
 * the raw data and processed data, and a boolean flag indicating if this Data object
 * was processed or not.
 */
@Entity
@Table(name="CUSTOMER")
@SpaceClass
@XmlRootElement
public class Customer implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String idCustomer;

	private String name;

	private String  dni;
    
	private String  city;
    
    private String  address;
    
    private String  celphone;

    private Boolean processed;

    /**
     * Constructs a new Data object.
     */
    public Customer() {

    }
    
    public Customer(String id, String name, String dni, String city, String address, String celphone,
			Boolean processed) {
		super();
		this.idCustomer = id;
		this.name = name;
		this.dni = dni;
		this.city = city;
		this.address = address;
		this.celphone = celphone;
		this.processed = processed;
	}



	/**
     * The id of this object.
     */
    @SpaceId(autoGenerate=true)
    public String getIdCustomer() {
        return idCustomer;
    }

    /**
     * The id of this object. Its value will be auto generated when it is written
     * to the space.
     */
    public void setIdCustomer(String id) {
        this.idCustomer = id;
    }

   
    

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCelphone() {
		return celphone;
	}

	public void setCelphone(String celphone) {
		this.celphone = celphone;
	}

	public Boolean getProcessed() {
		return processed;
	}

	public void setProcessed(Boolean processed) {
		this.processed = processed;
	}

	public String toString() {
        return "id[" + idCustomer + "] name[" + name+ "] city[" + city+ "] direccion[" + address+ "]";
    }
}
