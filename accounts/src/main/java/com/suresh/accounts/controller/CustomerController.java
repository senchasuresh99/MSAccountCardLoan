package com.suresh.accounts.controller;

import com.suresh.accounts.accountcustomerresponsedto.AccountCardLoanResponse;
import com.suresh.accounts.accountcustomerresponsedto.AccountCustomerResponse;
import com.suresh.accounts.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CustomerController {

    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

    private final CustomerService customerService;
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/facthcustomerdetils")
    public ResponseEntity<AccountCardLoanResponse> fetchCustomerDetails(@RequestParam String mobileNumber) {
        log.info("Fetching customer details for mobile number: {}", mobileNumber);
        return new ResponseEntity<>(customerService.fetchCustomerDetails(mobileNumber), HttpStatus.OK);
    }
}
