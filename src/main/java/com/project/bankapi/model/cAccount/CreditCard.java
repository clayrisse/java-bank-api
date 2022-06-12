package com.project.bankapi.model.cAccount;

import javax.persistence.Entity;

@Entity
public class CreditCard  extends Product{

    private long creditLimit; // = def100  up to 100000 inic
    private double interestRate; // =def0.2 up to >0.1inic

}
