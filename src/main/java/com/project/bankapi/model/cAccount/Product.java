package com.project.bankapi.model.cAccount;

import com.project.bankapi.model.cUser.AccountHolder;

import javax.persistence.*;
import java.util.Set;

@Entity
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Inheritance(strategy = InheritanceType.JOINED)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "currency", column = @Column(name = "bal_currency")),
            @AttributeOverride(name = "amount", column = @Column(name = "bal_amount"))
    })
    private Money balance; //--- if balance < minbalance -->balance-penaltyFee
    public static int penaltyFee; // = 40
    public static long accountNumGenerator;


    @ManyToOne
    @JoinColumn(name = "owner_1_id")
    private AccountHolder owner1;
//    private AccountHolder owner2;

    @ManyToMany
    @JoinTable(
            name = "accounts_owners",
            joinColumns = {@JoinColumn(name = "productId")},
            inverseJoinColumns = {@JoinColumn(name = "ownersId")}
    )
    private Set<AccountHolder> owners;

}
