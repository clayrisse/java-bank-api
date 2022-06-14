package com.project.bankapi.repository;

import com.project.bankapi.model.mProduct.CheckingAc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckingAcRepository extends JpaRepository<CheckingAc, Long> {
}
