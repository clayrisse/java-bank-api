package com.project.bankapi.model.mUser;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
    private String street;
    private String zipcode;
    private String city;
    private String country;
}
