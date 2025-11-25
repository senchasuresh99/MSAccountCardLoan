package com.suresh.loans.service;

import com.suresh.loans.entities.Loans;
import com.suresh.loans.repository.LoanRepository;
import com.suresh.loans.responsedto.LoansResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class LoansServiceImpl implements LoansService {

    private static final Logger log = LoggerFactory.getLogger(LoansServiceImpl.class);
    private static final String PROVIDED_MOBILE_NUMBER_IS_NULL = "Provided mobile number is null";
    private static final String LOAN_NOT_FOUND_FOR_MOBILE_NUMBER = "Loans Not Found for Mobile Number ";
    private final LoanRepository loanRepository;

    public LoansServiceImpl(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    @Override
    public LoansResponse createLoan(LoansResponse loansResponse) {
        java.util.Objects.requireNonNull(loansResponse, "loansResponse must not be null");
        String mobileNumber = loansResponse.getMobileNumber();
        java.util.Objects.requireNonNull(mobileNumber, "mobileNumber must not be null");

        // ensure mobile number is unique
        Optional<Loans> existing = loanRepository.findByMobileNumber(mobileNumber);
        if (existing.isPresent()) {
            throw new IllegalArgumentException("Mobile number already in use: " + mobileNumber);
        }

        Loans loans = new Loans();
        BeanUtils.copyProperties(loansResponse, loans);

        String loanNumber = String.valueOf(
                ThreadLocalRandom.current().nextLong(1_000_000L, 100_000_000L)
        );
        loans.setLonaNumber(loanNumber);

        Loans saved = loanRepository.save(loans);

        LoansResponse response = new LoansResponse();
        BeanUtils.copyProperties(saved, response);
        return response;
    }


    @Override
    public LoansResponse getLoans(String mobileNumber) {
        if(mobileNumber != null){
            Loans loans = loanRepository.findByMobileNumber(mobileNumber).orElseThrow(() -> new RuntimeException(LOAN_NOT_FOUND_FOR_MOBILE_NUMBER+mobileNumber));
            if(loans != null){
                LoansResponse loansResponse = new LoansResponse();
                BeanUtils.copyProperties(loans, loansResponse);
                return loansResponse;
            } else {
                log.info("No loans found for mobile number: {}", mobileNumber);
            }
        } else {
            log.debug(PROVIDED_MOBILE_NUMBER_IS_NULL);
        }
        return null;
    }

    @Override
    public Boolean deleteLoan(String mobileNumber) {
        if(mobileNumber != null){
            Loans loans = loanRepository.findByMobileNumber(mobileNumber).orElseThrow(() -> new RuntimeException(LOAN_NOT_FOUND_FOR_MOBILE_NUMBER+mobileNumber));
            if(loans != null){
                loanRepository.delete(loans);
                return true;
            } else {
                log.info("No loans found to delete for mobile number: {}", mobileNumber);
            }
        } else {
            log.info(PROVIDED_MOBILE_NUMBER_IS_NULL);
        }
        return false;
    }

    @Override
    public LoansResponse updateLone(LoansResponse loansResponse) {
        if (loansResponse == null) {
            throw new IllegalArgumentException("loanDto must not be null");
        }
        String mobileNumber = loansResponse.getMobileNumber();
        if (mobileNumber == null) {
            throw new IllegalArgumentException("mobileNumber must not be null");
        }
        Loans existingLoan = loanRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new RuntimeException(LOAN_NOT_FOUND_FOR_MOBILE_NUMBER + mobileNumber));
        BeanUtils.copyProperties(loansResponse, existingLoan, "lonaNumber");
        Loans updatedLoan = loanRepository.save(existingLoan);
        LoansResponse response = new LoansResponse();
        BeanUtils.copyProperties(updatedLoan, response);
        return response;
    }
}
