package com.suresh.loans.controller;

import com.suresh.loans.responsedto.LoansResponse;
import com.suresh.loans.service.LoansService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/loans")
public class LoansRestController {

    private final LoansService loansService;
    public LoansRestController(LoansService loansService) {
        this.loansService = loansService;
    }

    @PostMapping
    public ResponseEntity<LoansResponse> createLoan(@RequestBody LoansResponse loansResponse){
        return new ResponseEntity<>(loansService.createLoan(loansResponse), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<LoansResponse> getLoans(@RequestParam String mobileNumber){
        return new ResponseEntity<>(loansService.getLoans(mobileNumber), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Boolean> deleteLoan(@RequestParam String mobileNumber){
        return new ResponseEntity<>(loansService.deleteLoan(mobileNumber), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<LoansResponse> updateLoan(@RequestBody LoansResponse loansResponse){
        return new ResponseEntity<>(loansService.updateLone(loansResponse),HttpStatus.OK);
    }

}
