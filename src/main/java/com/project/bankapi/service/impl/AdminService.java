package com.project.bankapi.service.impl;

import com.project.bankapi.dto.CheckingAcDTO;
import com.project.bankapi.dto.CreditCardDTO;
import com.project.bankapi.dto.HolderDTO;
import com.project.bankapi.dto.SavingsDTO;
import com.project.bankapi.model.mProduct.*;
import com.project.bankapi.model.mUser.AccountHolder;
import com.project.bankapi.model.mUser.Address;
import com.project.bankapi.model.mUser.User;
import com.project.bankapi.repository.*;
import com.project.bankapi.service.i.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

@Service
public class AdminService implements IAdminService {

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

//    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public AccountHolder createNewHolder(HolderDTO holderDTO, UserDetails userDetails) {
        User admin = new User (userDetails.getUsername(), userDetails.getPassword());
        if (userRepository.findByUsername(admin.getUsername()).isPresent()) {         //checkea si viene admin
            if (userRepository.findByUsername(holderDTO.getUsername()).isEmpty()) {   //checkea si existe el username paa poder asignarlo
                Address address = new Address(holderDTO.getStreet(), holderDTO.getZipcode(), holderDTO.getCity(), holderDTO.getCountry());
                Address mailAddress = holderDTO.getStreetMail() == null ? address : new Address(holderDTO.getStreetMail(), holderDTO.getZipcodeMail(), holderDTO.getCityMail(), holderDTO.getCountryMail());
                AccountHolder holder = accountHolderRepository.save(new AccountHolder(holderDTO.getUsername(), address, mailAddress, holderDTO.getFullName()));
                holder.setRole("HOLDER");
                holder.setBirthDate(holderDTO.getBirthDate());
                return accountHolderRepository.save(holder);    //todo porque hacer esto asi?
//                return holder;
            }
            throw new ResponseStatusException( HttpStatus.BAD_REQUEST, "Username for account holder is already in use");
        }
        throw new ResponseStatusException( HttpStatus.BAD_REQUEST, "Username already exists");
    }

    public Product createCheckingAccount(CheckingAcDTO checkingDTO, UserDetails userDetails) {
        User admin = new User (userDetails.getUsername(), userDetails.getPassword());
        System.err.println(admin.getUsername() + " --- " + admin.getPassword());
        if (userRepository.findByUsername(admin.getUsername()).isPresent()) {
            if (accountHolderRepository.findById(checkingDTO.getFirstownerId()).isPresent()) {
                System.err.println("------" + checkingDTO.getFirstownerId() + "----- " + checkingDTO.getSecondownerId());
                if (checkingDTO.getFirstownerId() == checkingDTO.getSecondownerId()) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can't use the same account holder for both primary and secondary owner");
                }
                if ( checkingDTO.getSecondownerId() == 0 || accountHolderRepository.findById(checkingDTO.getSecondownerId()).isPresent()){
                    AccountHolder holder = accountHolderRepository.findById(checkingDTO.getFirstownerId()).get();

                    if (holder.getAge()>24) {
                        CheckingAc checkingAc = new CheckingAc();
                        if (checkingDTO.getCheckDTOMinBalance() > 0) checkingAc.setMinimumBalance(checkingDTO.getCheckDTOMinBalance());
                        if (checkingDTO.getCheckDTOMonthMaintenanceFee() > 0) checkingAc.setMonthlyMaintenanceFee(checkingDTO.getCheckDTOMonthMaintenanceFee());
                        checkingAc.setFirstowner(holder);
                        checkingAc.setFirstBalance(checkingDTO.getBalanceAmount());
                        if (checkingDTO.getSecondownerId() != 0) {
                            checkingAc.setSecondowner(accountHolderRepository.findById(checkingDTO.getSecondownerId()).get());
                        }
                        return checkingAcRepository.save(checkingAc);
                    } else {
                        CheckStudentAc checkStudentAc = new CheckStudentAc();
                        checkStudentAc.setFirstBalance(checkingDTO.getBalanceAmount());
                        checkStudentAc.setFirstowner(holder);
                        if (checkingDTO.getSecondownerId() != 0) {
                            checkStudentAc.setSecondowner(accountHolderRepository.findById(checkingDTO.getSecondownerId()).get());
                        }
                        return checkStudentAcRepository.save(checkStudentAc);
                    }
                }
                throw new ResponseStatusException( HttpStatus.BAD_REQUEST, "Can't find secondary account holder");
            }
            throw new ResponseStatusException( HttpStatus.BAD_REQUEST, "Can't find primary account holder");
        }
        throw new ResponseStatusException( HttpStatus.BAD_REQUEST, "Admin Username not found");
    }

    public Product createSavingsAccount(SavingsDTO savingsDTO, UserDetails userDetails) {
        User admin = new User (userDetails.getUsername(), userDetails.getPassword());
        if (userRepository.findByUsername(admin.getUsername()).isPresent()) {
            if (accountHolderRepository.findById(savingsDTO.getFirstownerId()).isPresent()) {
                System.err.println("------" + savingsDTO.getFirstownerId() + "----- " + savingsDTO.getSecondownerId());
                if (savingsDTO.getFirstownerId() == savingsDTO.getSecondownerId()) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can't use the same account holder for both primary and secondary owner");
                }
                if ( savingsDTO.getSecondownerId() == 0 || accountHolderRepository.findById(savingsDTO.getSecondownerId()).isPresent()){
                    AccountHolder holder = accountHolderRepository.findById(savingsDTO.getFirstownerId()).get();
                    SavingsAc savingsAc = new SavingsAc();
                    System.err.println("ffffffffffffffffffffffffffffffffffffffff" + savingsDTO.getSavingsDTOMinBalance());
                    if (savingsDTO.getSavingsDTOMinBalance() > 0) savingsAc.setMinimumBalance(savingsDTO.getSavingsDTOMinBalance());
                    if (savingsDTO.getInterestDTORate() > 0) savingsAc.setInterestRate(savingsDTO.getInterestDTORate());
                    savingsAc.setFirstowner(holder);
                    savingsAc.setFirstBalance(savingsDTO.getBalanceAmount());
                    if (savingsDTO.getSecondownerId() != 0) {
                        savingsAc.setSecondowner(accountHolderRepository.findById(savingsDTO.getSecondownerId()).get());
                    }
                    return savingsAcRepository.save(savingsAc);
                }
                throw new ResponseStatusException( HttpStatus.BAD_REQUEST, "Can't find secondary account holder");
            }
            throw new ResponseStatusException( HttpStatus.BAD_REQUEST, "Can't find primary account holder");
        }
        throw new ResponseStatusException( HttpStatus.BAD_REQUEST, "Admin Username not found");
    }

    public Product createCreditCard(CreditCardDTO creditCardDTO, UserDetails userDetails) {
        User admin = new User (userDetails.getUsername(), userDetails.getPassword());
        if (userRepository.findByUsername(admin.getUsername()).isPresent()) {
            if (accountHolderRepository.findById(creditCardDTO.getFirstownerId()).isPresent()) {
                System.err.println("------" + creditCardDTO.getFirstownerId() + "----- " + creditCardDTO.getSecondownerId());
                if (creditCardDTO.getFirstownerId() == creditCardDTO.getSecondownerId()) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can't use the same account holder for both primary and secondary owner");
                }
                if ( creditCardDTO.getSecondownerId() == 0 || accountHolderRepository.findById(creditCardDTO.getSecondownerId()).isPresent()){
                    AccountHolder holder = accountHolderRepository.findById(creditCardDTO.getFirstownerId()).get();
                    CreditCard creditCard = new CreditCard();
                    if (creditCardDTO.getCreditLimit() > 0) creditCard.setCreditLimit(creditCardDTO.getCreditLimit());
                    if (creditCardDTO.getInterestRate() > 0) creditCard.setInterestRate(creditCardDTO.getInterestRate());
                    creditCard.setFirstowner(holder);
                    creditCard.setBalance(new BigDecimal("100")); //todo retirar esta linea que es solo para testear
                    if (creditCardDTO.getSecondownerId() != 0) {
                        creditCard.setSecondowner(accountHolderRepository.findById(creditCardDTO.getSecondownerId()).get());
                    }
                    return creditCardRepository.save(creditCard);
                }
                throw new ResponseStatusException( HttpStatus.BAD_REQUEST, "Can't find secondary account holder");
            }
            throw new ResponseStatusException( HttpStatus.BAD_REQUEST, "Can't find primary account holder");
        }
        throw new ResponseStatusException( HttpStatus.BAD_REQUEST, "Admin Username not found");
    }

}
