package com.project.bankapi.model.cAccount;

import com.project.bankapi.model.cUser.AccountHolder;

import javax.persistence.*;

//@Entity
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
//public class Account {
@MappedSuperclass
public abstract class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
//    private String owner1;
//    @ManyToOne
//    @JoinColumn(name = "owner_1_id")
//    private AccountHolder owner1;
    private String owner2;
    private long balance; //--- if balance < minbalance -->balance-penaltyFee
    public static int penaltyFee; // = 40
    public static long accountNumGenerator;


}
