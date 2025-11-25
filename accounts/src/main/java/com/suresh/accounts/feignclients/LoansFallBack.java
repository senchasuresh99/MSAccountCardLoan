package com.suresh.accounts.feignclients;

import com.suresh.accounts.accountcustomerresponsedto.LoansResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class LoansFallBack implements LoansFeignClient {

    @Override
    public ResponseEntity<LoansResponse> getLoans(String mobileNumber) {
        LoansResponse loansResponse = new LoansResponse();
        loansResponse.setStatus("Loans service is currently unavailable. Please try again later.");
        return ResponseEntity.ok(loansResponse);
    }
}
