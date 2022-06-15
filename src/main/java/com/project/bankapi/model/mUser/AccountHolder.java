package com.project.bankapi.model.mUser;

import com.project.bankapi.model.mProduct.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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

//    private Date birthDate;
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

    @OneToMany(mappedBy = "firstowner") //primaros y secundarios
    private List<Product> firstownerList = new ArrayList<>();

    @OneToMany(mappedBy = "secondowner") //primaros y secundarios
    private List<Product> secondownerList;


//--------------------------------constructors
    public AccountHolder() { }

    public AccountHolder(String username) {
        super(username, defaultPassword);
    }

    public AccountHolder(String username, String password) {
        super(username, password);
    }

    public AccountHolder(String username,  Address primaryAddress) {
        super(username, defaultPassword);
//        this.birth = birth;
        this.primaryAddress = primaryAddress;
    }

    public AccountHolder(String username,  Address primaryAddress, Address mailAddress) {
        super(username, defaultPassword);
        this.primaryAddress = primaryAddress;
        this.mailAddress = mailAddress;
    }
}
