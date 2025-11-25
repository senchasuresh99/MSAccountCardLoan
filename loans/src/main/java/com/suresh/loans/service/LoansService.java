package com.suresh.loans.service;

import com.suresh.loans.responsedto.LoansResponse;

public interface LoansService {
    LoansResponse createLoan(LoansResponse loansResponse);

    LoansResponse getLoans(String mobileNumber);

    Boolean deleteLoan(String mobileNumber);

    LoansResponse updateLone(LoansResponse loansResponse);
}
