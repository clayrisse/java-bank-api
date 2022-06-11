package com.project.bankapi.controller.impl;

import com.project.bankapi.controller.IThirdPartyController;
import com.project.bankapi.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class ThirdPartyController implements IThirdPartyController {

    @Autowired    AccountHolderService accountHolderService;
    @Autowired    AdminService adminService;
    @Autowired    CheckingAcService checkingAcService;
    @Autowired    CheckStudentAcService checkStudentAcService;
    @Autowired    CredictCardAcService credictCardAcService;
    @Autowired    SavingsAcService service;
    @Autowired    ThirdPartyService thirdPartyService;


}