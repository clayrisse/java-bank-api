package com.project.bankapi.repository;

import com.project.bankapi.model.mProduct.CheckStudentAc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckStudentAcRepository extends JpaRepository<CheckStudentAc, Long> {
}
