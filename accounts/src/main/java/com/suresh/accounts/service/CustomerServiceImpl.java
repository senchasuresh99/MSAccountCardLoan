package com.suresh.accounts.service;

import com.suresh.accounts.accountcustomerresponsedto.AccountCardLoanResponse;
import com.suresh.accounts.accountcustomerresponsedto.AccountCustomerResponse;
import com.suresh.accounts.accountcustomerresponsedto.CardResponse;
import com.suresh.accounts.accountcustomerresponsedto.LoansResponse;
import com.suresh.accounts.dto.AccountsDto;
import com.suresh.accounts.entities.Accounts;
import com.suresh.accounts.entities.Customer;
import com.suresh.accounts.feignclients.CardsFallBack;
import com.suresh.accounts.feignclients.CardsFeignClient;
import com.suresh.accounts.feignclients.LoansFallBack;
import com.suresh.accounts.feignclients.LoansFeignClient;
import com.suresh.accounts.repository.AccountRepository;
import com.suresh.accounts.repository.CustomerRepository;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService{
    private static final Logger log = LoggerFactory.getLogger(CustomerServiceImpl.class);

    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;
    private final CardsFeignClient cardsFeignClient;
    private final LoansFeignClient loansFeignClient;
    private final CardsFallBack cardsFallBack;
    private final LoansFallBack loansFallBack;
    public CustomerServiceImpl(CustomerRepository customerRepository, AccountRepository accountRepository, CardsFeignClient cardsFeignClient, LoansFeignClient loansFeignClient, CardsFallBack cardsFallBack, LoansFallBack loansFallBack) {
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
        this.cardsFeignClient = cardsFeignClient;
        this.loansFeignClient = loansFeignClient;
        this.cardsFallBack = cardsFallBack;
        this.loansFallBack = loansFallBack;
    }

    public @NotNull Customer getCustomer(AccountCustomerResponse accountCustomerResponse) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(accountCustomerResponse, customer);
        Optional<Customer> optional= customerRepository.findByMobileNumber(accountCustomerResponse.getMobileNumber());
        if(optional.isPresent()){
            throw new RuntimeException("Customer Already Registered With given Mobile Number" + accountCustomerResponse.getMobileNumber());
        }
        customer = customerRepository.save(customer);
        return customer;
    }

    @Override
    public @NotNull AccountCardLoanResponse fetchCustomerDetails(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(() -> new RuntimeException("Customer Not Found for Mobile Number " + mobileNumber));
        log.info("Customer found: {}", customer);
        Accounts accounts = accountRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(() -> new RuntimeException("Account Not Found for Customer ID " + customer.getCustomerId()));
        log.info("Accounts found: {}", accounts);
        CardResponse cardResponse = cardsFeignClient.getCards(mobileNumber).getBody();
        if(cardResponse ==null || cardResponse.getMobileNumber() == null) {
            cardResponse = new CardResponse();
            cardsFallBack.getCards(mobileNumber).getBody();
            cardResponse.setStatus("Cards Service is down. Please try after sometime");
            log.warn("CardResponse is null, using fallback");
        } else {
            cardResponse.setStatus("Success");
        }
        log.info("CardResponse received: {}", cardResponse);
        LoansResponse loansResponse = loansFeignClient.getLoans(mobileNumber).getBody();
        if(loansResponse == null || loansResponse.getMobileNumber() == null) {
            loansResponse = new LoansResponse();
            loansFallBack.getLoans(mobileNumber).getBody();
            loansResponse.setStatus("Loans service is currently unavailable. Please try again later.");
            log.warn("LoansResponse is null, using fallback");
        } else {
            loansResponse.setStatus("Success");
        }
        log.info("LoansResponse received: {}", loansResponse);
        return getAccountCustomerResponse(customer, accounts, cardResponse, loansResponse);
    }

    private static @NotNull AccountCardLoanResponse getAccountCustomerResponse(Customer customer, Accounts accounts, CardResponse cardResponse, LoansResponse loansResponse) {
        AccountCardLoanResponse accountCustomerResponse = new AccountCardLoanResponse();
        BeanUtils.copyProperties(customer, accountCustomerResponse);
        AccountsDto accountsDto = new AccountsDto();
        BeanUtils.copyProperties(accounts, accountsDto);
        accountCustomerResponse.setAccountsDto(accountsDto);
        accountCustomerResponse.setCardResponse(cardResponse);
        accountCustomerResponse.setLoansResponse(loansResponse);
        log.info("AccountCustomerResponse constructed: {}", accountCustomerResponse);
        return accountCustomerResponse;
    }
}
