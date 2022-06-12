package com.project.bankapi.model.cAccount;

import javax.persistence.*;

@Entity
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Inheritance(strategy = InheritanceType.JOINED)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
//    @ManyToOne
//    @JoinColumn(name = "owner_1_id")
//    private AccountHolder owner1;
//    private String owner1;
    private String owner2;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "currency", column = @Column(name = "bal_currency")),
            @AttributeOverride(name = "amount", column = @Column(name = "bal_amount"))
    })
    private Money balance; //--- if balance < minbalance -->balance-penaltyFee
    public static int penaltyFee; // = 40
    public static long accountNumGenerator;



}
