package com.project.bankapi.model.mUser;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
    private String street;
    private String zipcode;
    private String city;
    private String country;

//--------------------------------constructors
    public Address() {}

    public Address(String street, String zipcode, String city, String country) {
        this.street = street;
        this.zipcode = zipcode;
        this.city = city;
        this.country = country;
    }

//--------------------------------getters & setters
    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }

    public String getZipcode() { return zipcode; }
    public void setZipcode(String zipcode) { this.zipcode = zipcode; }

    public String getCity() { return city;  }
    public void setCity(String city) { this.city = city; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
}
