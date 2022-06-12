package com.project.bankapi.controller.impl;

import com.project.bankapi.controller.ICreditCardController;
import com.project.bankapi.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class CreditCardController implements ICreditCardController {

    @Autowired    AccountHolderService accountHolderService;
    @Autowired    AdminService adminService;
    @Autowired    CheckingAcService checkingAcService;
    @Autowired    CheckStudentAcService checkStudentAcService;
    @Autowired    CreditCardService creditCardService;
    @Autowired    SavingsAcService service;
    @Autowired    ThirdPartyService thirdPartyService;
    @Autowired    AccountService accountService;
    @Autowired    ProductService productService;

}