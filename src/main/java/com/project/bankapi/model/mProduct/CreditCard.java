package com.project.bankapi.model.mProduct;

import com.project.bankapi.model.mUser.AccountHolder;

import javax.persistence.*;

@Entity
@PrimaryKeyJoinColumn(name = "productId")
public class CreditCard {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long creditLimit; // = def100  up to 100000 inic
    private double interestRate; // =def0.2 up to >0.1inic


}
