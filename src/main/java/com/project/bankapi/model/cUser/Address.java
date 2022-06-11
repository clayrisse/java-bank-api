package com.project.bankapi.model.cUser;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
    private String street;
    private String zipcode;
    private String city;
    private String country;
}
