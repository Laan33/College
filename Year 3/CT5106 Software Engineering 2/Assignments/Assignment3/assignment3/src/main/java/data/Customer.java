/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Table;

/**
 *
 * @author catha
 */
@Entity
@Table(name = "Customer")
public class Customer implements Serializable {

    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
    @Column(name="phone")
    private String phone;
    @Column(name="email")
    private String email;
    @Column(name="country")
    private String country;
    @Column(name="postCode")
    private String postCode;
    @Column(name="creditLimit")
    private float creditLimit;
    
    public Customer() {
    }

    public Customer(int customerID, String name, String address, String phone, String email, String country, String postCode, float creditLimit) {
        this.id = customerID;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.country = country;
        this.postCode = postCode;
        this.creditLimit = creditLimit;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        if(id > 0) {
            this.id = id;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name.length() > 0) {
            this.name = name;
        }
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if(address.length() > 0) {
            this.address = address;
        }
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if(phone.length() > 0) {
            this.phone = phone;
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if(email.length() > 0) {
            this.email = email;
        }
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        if(country.length() > 0) {
            this.country = country;
        }
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        if(postCode.length() > 0) {
            this.postCode = postCode;
        }
    }

    public float getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(float creditLimit) {
        if(creditLimit > 0) {
            this.creditLimit = creditLimit;
        }
    }


}
