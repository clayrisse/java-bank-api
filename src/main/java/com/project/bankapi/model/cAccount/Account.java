package com.project.bankapi.model.cAccount;

import com.project.bankapi.enums.Status;

import javax.persistence.*;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@PrimaryKeyJoinColumn(name = "accountId")
public class Account extends Product {

    private String secretKey;
    private Date creationDate;
    @Enumerated(EnumType.STRING)
    private Status status;
    private int minimumBalance;
    private int monthlyMaintenanceFee;
}
