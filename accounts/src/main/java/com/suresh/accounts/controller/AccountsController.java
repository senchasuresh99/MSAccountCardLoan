package com.suresh.accounts.controller;

import com.suresh.accounts.accountcustomerresponsedto.AccountCustomerResponse;
import com.suresh.accounts.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountsController {

    private static final Logger log = LoggerFactory.getLogger(AccountsController.class);

    private final AccountService accountService;

    public AccountsController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<AccountCustomerResponse> createAccount(@RequestBody AccountCustomerResponse accountCustomerResponse){
        log.info("Creating account for mobile number: {}", accountCustomerResponse.getMobileNumber());
        return new ResponseEntity<>(accountService.createAccount(accountCustomerResponse), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<AccountCustomerResponse> getAccount(@RequestParam String mobileNumber){
        log.info("Fetching account for mobile number: {}", mobileNumber);
        return new ResponseEntity<>(accountService.getAccount(mobileNumber), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<AccountCustomerResponse> updateAccount(@RequestBody AccountCustomerResponse accountCustomerResponse){
        log.info("Updating account for mobile number: {}", accountCustomerResponse.getMobileNumber());
        return new ResponseEntity<>(accountService.updateAccount(accountCustomerResponse), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Boolean> deleteAccount(@RequestParam String mobileNumber){
        log.info("Deleting account for mobile number: {}", mobileNumber);
        return new ResponseEntity<>(accountService.deleteAccount(mobileNumber),HttpStatus.OK);
    }
}
