package com.project.bankapi.repository;

import com.project.bankapi.model.mProduct.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
