package com.project.bankapi.model.cUser;

import com.project.bankapi.model.cAccount.Product;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class AccountHolder extends User{

    private Date birth;
    @Embedded
    private Address primaryAddress;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "street", column = @Column(name = "mailStreet")),
            @AttributeOverride(name = "zipcode", column = @Column(name = "mailZipcode")),
            @AttributeOverride(name = "city", column = @Column(name = "mailCity")),
            @AttributeOverride(name = "country", column = @Column(name = "mail_Country"))
    })
    private Address mailAddress;

//    @OneToMany
//    private List<Product> productsList;

}
