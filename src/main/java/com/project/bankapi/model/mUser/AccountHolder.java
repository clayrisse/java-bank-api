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

//    private Date birth;
//    @Embedded
//    private Address primaryAddress;
//    @Embedded
//    @AttributeOverrides({
//            @AttributeOverride(name = "street", column = @Column(name = "mailStreet")),
//            @AttributeOverride(name = "zipcode", column = @Column(name = "mailZipcode")),
//            @AttributeOverride(name = "city", column = @Column(name = "mailCity")),
//            @AttributeOverride(name = "country", column = @Column(name = "mail_Country"))
//    })
//    private Address mailAddress;


    @OneToMany(mappedBy = "firstowner") //primaros y secundarios
    private List<Product> firstownerList = new ArrayList<>();



    @OneToMany(mappedBy = "secondowner") //primaros y secundarios
    private List<Product> secondownerList;


    //todo esto es para ver como es mejor resolverlo???
//    @ManyToMany(mappedBy = "owners")
//    private List<Product> productsList;


//    public AccountHolder(Address primaryAddress) {
//        this.primaryAddress = primaryAddress;
//    }


    public AccountHolder() { }

    public AccountHolder(String username, String password) {
        super(username, password);
    }

    public AccountHolder(String username) {

        super(username, defaultPassword);
    }
}
