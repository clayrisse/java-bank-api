package com.project.bankapi.repository;

import com.project.bankapi.model.cAccount.CredictCardAc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardAcRepository extends JpaRepository<CredictCardAc, Long> {
}
