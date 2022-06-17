package com.project.bankapi.model.mProduct;

import javax.persistence.*;

@Entity
//@Data
//@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "productId")
public class SavingsAc extends Account{

    private static int savingsMinBalance = 1000; // , no <100inic
    private double interestRate = 0.0025; //def, up to 0.5inic


    public SavingsAc() { super(savingsMinBalance); }


    public double getInterestRate() { return interestRate; }
    public void setInterestRate(double interestRate) {
        if (interestRate < 0.0025) throw new IllegalArgumentException("La tasa de interés no puede ser menor a 0.0025");
        if (interestRate > 0.5) throw new IllegalArgumentException("La tasa de interés no puede ser mayor a 0.5");
        this.interestRate = interestRate;
    }

    public static int getSavingsMinBalance() { return savingsMinBalance; }
    public static void setSavingsMinBalance(int savingsMinBalance) {
        SavingsAc.savingsMinBalance = savingsMinBalance;
    }

    @Override
    public void setMinimumBalance(int minimumBalance) {
        if (minimumBalance < 100) throw new IllegalArgumentException("El mínimo balance no puede ser menor a 100");
        if (minimumBalance > 1000) throw new IllegalArgumentException("El máximo balance no puede ser mayor a 1000");
        super.setMinimumBalance(minimumBalance);
    }
}
