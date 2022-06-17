package com.project.bankapi.model.mUser;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.bankapi.model.mProduct.Product;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Year;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


@Entity
//@Data
//@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "pepitoId")
public class AccountHolder extends User{

    public static final String defaultPassword = "1234";
    private LocalDate birthDate;
    private long age;
    @Embedded
    private Address primaryAddress;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "street", column = @Column(name = "mailStreet")),
            @AttributeOverride(name = "zipcode", column = @Column(name = "mailZipcode")),
            @AttributeOverride(name = "city", column = @Column(name = "mailCity")),
            @AttributeOverride(name = "country", column = @Column(name = "mailCountry"))
    })
    private Address mailAddress;

    @JsonIgnore
    @OneToMany(mappedBy = "firstowner")
    private List<Product> firstownerList = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "secondowner")
    private List<Product> secondownerList = new ArrayList<>();


//--------------------------------constructors
    public AccountHolder() {}

    public AccountHolder(String username, String fullName) {
        super(username, fullName, defaultPassword);
    }

    public AccountHolder(String username, String fullName, String password) {
        super(username, fullName, password);
    }

    public AccountHolder(String username,  Address primaryAddress,  String fullName) {
        super(username, fullName, defaultPassword);
//        this.birth = birth;
        this.primaryAddress = primaryAddress;
    }

    public AccountHolder(String username,  Address primaryAddress, Address mailAddress, String fullName) {
        super(username, fullName, defaultPassword);
        this.primaryAddress = primaryAddress;
        this.mailAddress = mailAddress;
    }

    public long getAge() {
        setAge();
        return age;
    }

    public void setAge() {
        this.age = ChronoUnit.YEARS.between(birthDate, LocalDate.now());
    }

    public LocalDate getBirthDate() {
        setAge();
        return birthDate;
    }
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        setAge();
    }

    public Address getPrimaryAddress() { return primaryAddress; }
    public void setPrimaryAddress(Address primaryAddress) { this.primaryAddress = primaryAddress; }

    public Address getMailAddress() { return mailAddress; }
    public void setMailAddress(Address mailAddress) { this.mailAddress = mailAddress; }

    public List<Product> getFirstownerList() { return firstownerList; }
    public void setFirstownerList(List<Product> firstownerList) { this.firstownerList = firstownerList; }

    public List<Product> getSecondownerList() { return secondownerList; }
    public void setSecondownerList(List<Product> secondownerList) { this.secondownerList = secondownerList; }

}
