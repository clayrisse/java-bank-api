package com.project.bankapi.dto;

import java.time.LocalDate;
import java.util.Date;

public class HolderDTO {
    private String username;
    private String password;
    private String fullName;
    private String street;
    private String zipcode;
    private String city;
    private String country;
    private String streetMail;
    private String zipcodeMail;
    private String cityMail;
    private String countryMail;

    private LocalDate birthDate;


    public HolderDTO(String username
                    , String password
                    , String fullName
                    , String street, String zipcode, String city, String country
                    , String streetMail, String zipcodeMail, String cityMail, String countryMail
                    , LocalDate birthDate
    ) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.street = street;
        this.zipcode = zipcode;
        this.city = city;
        this.country = country;
        this.streetMail = streetMail;
        this.zipcodeMail = zipcodeMail;
        this.cityMail = cityMail;
        this.countryMail = countryMail;
        this.birthDate = birthDate;

    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }

    public String getZipcode() { return zipcode; }
    public void setZipcode(String zipcode) { this.zipcode = zipcode; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public String getStreetMail() { return streetMail; }
    public void setStreetMail(String streetMail) { this.streetMail = streetMail; }

    public String getZipcodeMail() { return zipcodeMail; }
    public void setZipcodeMail(String zipcodeMail) { this.zipcodeMail = zipcodeMail; }

    public String getCityMail() { return cityMail; }
    public void setCityMail(String cityMail) { this.cityMail = cityMail; }

    public String getCountryMail() { return countryMail; }
    public void setCountryMail(String countryMail) { this.countryMail = countryMail; }

    public LocalDate getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }
}
