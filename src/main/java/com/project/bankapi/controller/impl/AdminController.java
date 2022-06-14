package com.project.bankapi.controller.impl;

import com.project.bankapi.controller.i.IAdminController;
import com.project.bankapi.dto.CheckingAcDTO;
import com.project.bankapi.model.mProduct.Account;
import com.project.bankapi.model.mProduct.CheckStudentAc;
import com.project.bankapi.model.mProduct.CheckingAc;
import com.project.bankapi.model.mProduct.Product;
import com.project.bankapi.model.mUser.AccountHolder;
import com.project.bankapi.model.mUser.User;
import com.project.bankapi.repository.*;
import com.project.bankapi.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/admin/")
public class AdminController implements IAdminController {

    @Autowired    AccountHolderService accountHolderService;
    @Autowired    AdminService adminService;
    @Autowired    CreditCardService creditCardService;
    @Autowired    ThirdPartyService thirdPartyService;
    @Autowired    AccountService accountService;
    @Autowired    ProductService productService;
    @Autowired    UserService userService;
//-------------------------------------------------

    @Autowired    AccountHolderRepository accountHolderRepository;
    @Autowired    AdminRepository adminRepository;
    @Autowired    CheckingAcRepository checkingAcRepository;
    @Autowired    CheckStudentAcRepository checkStudentAcRepository;
    @Autowired    CreditCardRepository creditCardRepository;
    @Autowired    SavingsAcRepository savingsAcRepository;
    @Autowired    ThirdPartyRepository thirdPartyRepository;
    @Autowired    AccountRepository accountRepository;
    @Autowired    ProductRepository productRepository;
    @Autowired    UserRepository userRepository;

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();



    @PostMapping("create/holder")

    public AccountHolder createNewHolder(@RequestBody AccountHolder accountHolder, @AuthenticationPrincipal UserDetails userDetails) {
        User admin = new User (userDetails.getUsername(), userDetails.getPassword());
        System.err.println(admin.getUsername() + " --- " + admin.getPassword());
        //todo orrrrrrrrrrrrr password doesn't match the password to  the admins
        if (userRepository.findByUsername(admin.getUsername()).isPresent()) {
            AccountHolder holder = accountHolderRepository.save(new AccountHolder(accountHolder.getUsername(), passwordEncoder.encode(AccountHolder.defaultPassword)));
            holder.setRole("HOLDER");
            return accountHolderRepository.save(holder);
        }
        throw new ResponseStatusException( HttpStatus.BAD_REQUEST, "Username already exists");
    }

    @PostMapping("product/create/checking-account") // esto debe tener autorizacion de admin

    public Product createCheckingAccount(@RequestBody CheckingAcDTO checkingDTO, @AuthenticationPrincipal UserDetails userDetails) {
        User admin = new User (userDetails.getUsername(), userDetails.getPassword());
        System.err.println(admin.getUsername() + " --- " + admin.getPassword());
        if (userRepository.findByUsername(admin.getUsername()).isPresent()) {
            if (accountHolderRepository.findById(checkingDTO.getFirstownerId()).isPresent()){
                System.err.println("------" + checkingDTO.getFirstownerId() + "----- " + checkingDTO.getSecondownerId() );
                if (checkingDTO.getFirstownerId() == checkingDTO.getSecondownerId()) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can't use the same account holder for both primary and secundary owner");
                }
                if ( checkingDTO.getSecondownerId() == 0 || accountHolderRepository.findById(checkingDTO.getSecondownerId()).isPresent()){
                    AccountHolder holder = accountHolderRepository.findById(checkingDTO.getFirstownerId()).get();
                    AccountHolder holder2;
                    CheckingAc checkingAc;
                    CheckStudentAc checkStudentAc;

                    if (checkingDTO.getAge()>24) {
                        if (checkingDTO.getSecondownerId() == 0) {
                            checkingAc = checkingAcRepository.save(new CheckingAc(checkingDTO.getBalanceAmount(), holder ));
                        } else {
                            holder2 = accountHolderRepository.findById(checkingDTO.getSecondownerId()).get();
                            checkingAc = checkingAcRepository.save(new CheckingAc(checkingDTO.getBalanceAmount(), holder, holder2 ));
                        }
                        return checkingAc;

                    } else {
                        if (checkingDTO.getSecondownerId() == 0) {
                            checkStudentAc = checkStudentAcRepository.save(new CheckStudentAc(checkingDTO.getBalanceAmount(), holder));
                        } else {
                            holder2 = accountHolderRepository.findById(checkingDTO.getSecondownerId()).get();
                            checkStudentAc = checkStudentAcRepository.save(new CheckStudentAc(checkingDTO.getBalanceAmount(), holder, holder2));
                        }
                        return checkStudentAc;

                    }
                }
                throw new ResponseStatusException( HttpStatus.BAD_REQUEST, "Can't find secondary account holder");
            }
            throw new ResponseStatusException( HttpStatus.BAD_REQUEST, "Can't find primary account holder");
        }
        //todo orrrrrrrrrrrrr password doent match the password to  the admin
        throw new ResponseStatusException( HttpStatus.BAD_REQUEST, "Admin Username not found");
    }

}
