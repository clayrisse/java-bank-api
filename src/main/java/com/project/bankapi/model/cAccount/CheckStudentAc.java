package com.project.bankapi.model.cAccount;

import javax.persistence.*;

@Entity
@PrimaryKeyJoinColumn(name = "studentId")
public class CheckStudentAc extends Account {

//        NO  minimumBalance
//        NO monthlyMaintenanceFee

}
