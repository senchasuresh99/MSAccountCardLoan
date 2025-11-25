package com.suresh.loans.repository;

import com.suresh.loans.entities.Loans;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoanRepository extends JpaRepository<Loans, Long> {
    Optional<Loans> findByMobileNumber(String mobileNumber);

    Optional<Object> findByLonaNumber(String lonaNumber);
}
