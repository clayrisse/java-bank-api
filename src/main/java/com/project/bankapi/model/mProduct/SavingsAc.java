package com.project.bankapi.model.mProduct;

import javax.persistence.*;

@Entity
@PrimaryKeyJoinColumn(name = "savingsId")
public class SavingsAc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    //        NO monthlyMaintenanceFee
    private double interestRate =0.0025; //def, up to 0.5inic

}
