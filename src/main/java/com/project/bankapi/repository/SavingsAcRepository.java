package com.project.bankapi.repository;

import com.project.bankapi.model.mProduct.SavingsAc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SavingsAcRepository extends JpaRepository <SavingsAc, Long> {
}
