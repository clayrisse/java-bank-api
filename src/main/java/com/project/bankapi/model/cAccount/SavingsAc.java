package com.project.bankapi.model.cAccount;

import javax.persistence.*;

@Entity
@PrimaryKeyJoinColumn(name = "savingsId")
public class SavingsAc extends Account{

    //        NO monthlyMaintenanceFee
    private double interestRate =0.0025; //def, up to 0.5inic

}
