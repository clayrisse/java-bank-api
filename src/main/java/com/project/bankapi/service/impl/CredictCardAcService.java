package com.project.bankapi.service.impl;

import com.project.bankapi.repository.CheckStudentAcRepository;
import com.project.bankapi.repository.CheckingAcRepository;
import com.project.bankapi.repository.CreditCardAcRepository;
import com.project.bankapi.repository.SavingsAcRepository;
import com.project.bankapi.repository.AccountHolderRepository;
import com.project.bankapi.repository.AdminRepository;
import com.project.bankapi.repository.ThirdPartyRepository;
import com.project.bankapi.service.ICredictCardAcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CredictCardAcService implements ICredictCardAcService {

    @Autowired    AccountHolderRepository accountHolderRepository;
    @Autowired    AdminRepository adminRepository;
    @Autowired    CheckingAcRepository checkingAcRepository;
    @Autowired    CheckStudentAcRepository checkStudentAcRepository;
    @Autowired    CreditCardAcRepository creditCardAcRepository;
    @Autowired    SavingsAcRepository savingsAcRepository;
    @Autowired    ThirdPartyRepository thirdPartyRepository;



}
