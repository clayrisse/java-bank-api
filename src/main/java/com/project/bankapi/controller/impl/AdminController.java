package com.project.bankapi.controller.impl;

import com.project.bankapi.controller.i.IAdminController;
import com.project.bankapi.dto.CheckingAcDTO;
import com.project.bankapi.dto.HolderDTO;
import com.project.bankapi.model.mProduct.Account;
import com.project.bankapi.model.mProduct.CheckStudentAc;
import com.project.bankapi.model.mProduct.CheckingAc;
import com.project.bankapi.model.mProduct.Product;
import com.project.bankapi.model.mUser.AccountHolder;
import com.project.bankapi.model.mUser.Address;
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



//    @PostMapping("create/holder")
//    public AccountHolder createNewHolder(@RequestBody AccountHolder accountHolder, @AuthenticationPrincipal UserDetails userDetails) {
//        User admin = new User (userDetails.getUsername(), userDetails.getPassword());
//        System.err.println(admin.getUsername() + " --- " + admin.getPassword());
//        //todo orrrrrrrrrrrrr password doesn't match the password to  the admins
//
//        //comprobarsi el account holder existe
//        if (userRepository.findByUsername(admin.getUsername()).isPresent()) {
//            AccountHolder holder = accountHolderRepository.save(new AccountHolder(accountHolder.getUsername(), passwordEncoder.encode(AccountHolder.defaultPassword)));
//            holder.setRole("HOLDER");
//            return accountHolderRepository.save(holder);
//        }
//        throw new ResponseStatusException( HttpStatus.BAD_REQUEST, "Username already exists");
//    }

    @PostMapping("create/holder")

    public AccountHolder createNewHolder(@RequestBody HolderDTO holderDTO, @AuthenticationPrincipal UserDetails userDetails) {
        User admin = new User (userDetails.getUsername(), userDetails.getPassword());
        if (userRepository.findByUsername(admin.getUsername()).isPresent()) {         //checkea si viene admin
            if (userRepository.findByUsername(holderDTO.getUsername()).isEmpty()) {   //checkea si existe el username paa poder asignarlo
                Address address = new Address(holderDTO.getStreet(), holderDTO.getZipcode(), holderDTO.getCity(), holderDTO.getCountry());
                Address mailAddress = holderDTO.getStreetMail() == null ? address : new Address(holderDTO.getStreetMail(), holderDTO.getZipcodeMail(), holderDTO.getCityMail(), holderDTO.getCountryMail());
                AccountHolder holder = accountHolderRepository.save(new AccountHolder(holderDTO.getUsername(), address, mailAddress));
                holder.setRole("HOLDER");
//                return accountHolderRepository.save(holder);    //todo porque hacer esto asi?
                return holder;
            }
            throw new ResponseStatusException( HttpStatus.BAD_REQUEST, "Username for account holder is already in use");
        }
        throw new ResponseStatusException( HttpStatus.BAD_REQUEST, "Username already exists");
    }



    @PostMapping("product/create/checking-account") // esto debe tener autorizacion de admin

    public Product createCheckingAccount(@RequestBody CheckingAcDTO checkingDTO, @AuthenticationPrincipal UserDetails userDetails) {
        User admin = new User (userDetails.getUsername(), userDetails.getPassword());
        System.err.println(admin.getUsername() + " --- " + admin.getPassword());
        if (userRepository.findByUsername(admin.getUsername()).isPresent()) {
            if (accountHolderRepository.findById(checkingDTO.getFirstownerId()).isPresent()) {
                System.err.println("------" + checkingDTO.getFirstownerId() + "----- " + checkingDTO.getSecondownerId());
                if (checkingDTO.getFirstownerId() == checkingDTO.getSecondownerId()) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can't use the same account holder for both primary and secundary owner");
                }
                //--------------------------------------------------
//                if ( checkingDTO.getSecondownerId() == 0 || accountHolderRepository.findById(checkingDTO.getSecondownerId()).isPresent()){
//                    AccountHolder holder = accountHolderRepository.findById(checkingDTO.getFirstownerId()).get();
//                    AccountHolder holder2;
//                    CheckingAc checkingAc;
//                    CheckStudentAc checkStudentAc;
//                    if (checkingDTO.getAge()>24) {
//                        if (checkingDTO.getSecondownerId() == 0) {
//                            checkingAc = checkingAcRepository.save(new CheckingAc(checkingDTO.getBalanceAmount(), holder));
//                        } else {
//                            holder2 = accountHolderRepository.findById(checkingDTO.getSecondownerId()).get();
//                            System.err.println("-------" + checkingDTO.getCheckDTOMinBalance() + "  " + checkingDTO.getCheckDTOMonthMaintenanceFee());
//                            if (checkingDTO.getCheckDTOMinBalance() > 0 || checkingDTO.getCheckDTOMonthMaintenanceFee() > 0) {
//                                System.err.println("hizo to do");
//                                checkingAc = checkingAcRepository.save(new CheckingAc(checkingDTO.getBalanceAmount(), holder, holder2, checkingDTO.getCheckDTOMinBalance(), checkingDTO.getCheckDTOMonthMaintenanceFee()));
//                            } else {
//                                checkingAc = checkingAcRepository.save(new CheckingAc(checkingDTO.getBalanceAmount(), holder, holder2));
//                                System.err.println("hizo 1");
//                            }
//                        }
//                        return checkingAc;
//                    } else {
//                        if (checkingDTO.getSecondownerId() == 0) {
//                            checkStudentAc = checkStudentAcRepository.save(new CheckStudentAc(checkingDTO.getBalanceAmount(), holder));
//                        } else {
//                            holder2 = accountHolderRepository.findById(checkingDTO.getSecondownerId()).get();
//                            checkStudentAc = checkStudentAcRepository.save(new CheckStudentAc(checkingDTO.getBalanceAmount(), holder, holder2));
//                        }
//                        return checkStudentAc;
//                    }
//                }
//
                //-----------------------------


               if ( checkingDTO.getSecondownerId() == 0 || accountHolderRepository.findById(checkingDTO.getSecondownerId()).isPresent()){
                    AccountHolder holder = accountHolderRepository.findById(checkingDTO.getFirstownerId()).get();
                    if (checkingDTO.getAge()>24) {
                        CheckingAc checkingAc = new CheckingAc();
                        if (checkingDTO.getCheckDTOMinBalance() > 0) checkingAc.setMinimumBalance(checkingDTO.getCheckDTOMinBalance());
                        if (checkingDTO.getCheckDTOMonthMaintenanceFee() > 0) checkingAc.setMonthlyMaintenanceFee(checkingDTO.getCheckDTOMonthMaintenanceFee());
                        checkingAc.setFirstowner(holder);
                        checkingAc.setBalance(checkingDTO.getBalanceAmount());
                        if (checkingDTO.getSecondownerId() != 0) {
                            checkingAc.setSecondowner(accountHolderRepository.findById(checkingDTO.getSecondownerId()).get());
                        }
                        return checkingAcRepository.save(checkingAc);
                    } else {
                        CheckStudentAc checkStudentAc = new CheckStudentAc();
                        checkStudentAc.setBalance(checkingDTO.getBalanceAmount());
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

}
