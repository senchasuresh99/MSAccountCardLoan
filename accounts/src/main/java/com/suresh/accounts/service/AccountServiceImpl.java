package com.suresh.accounts.service;

import com.suresh.accounts.accountcustomerresponsedto.AccountCustomerResponse;
import com.suresh.accounts.dto.AccountsDto;
import com.suresh.accounts.entities.Accounts;
import com.suresh.accounts.entities.Customer;
import com.suresh.accounts.repository.AccountRepository;
import com.suresh.accounts.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class AccountServiceImpl implements AccountService{

    private static final Logger log = LoggerFactory.getLogger(AccountServiceImpl.class);

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final CustomerServiceImpl customerServiceImpl;

    private static final String MOBILE_NUMBER_NOT_FOUND = "Mobile Number Not Found ";

    public AccountServiceImpl(AccountRepository accountRepository, CustomerRepository customerRepository, CustomerServiceImpl customerServiceImpl) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
        this.customerServiceImpl = customerServiceImpl;
    }

    @Override
    @Transactional
    public AccountCustomerResponse createAccount(AccountCustomerResponse accountCustomerResponse) {
        if (accountCustomerResponse == null) {
            throw new IllegalArgumentException("accountCustomerResponse must not be null");
        }
        Customer customer = customerServiceImpl.getCustomer(accountCustomerResponse);
        Accounts accounts = createAccount(customer);
        accounts = accountRepository.save(accounts);
        log.info("Account created with account number: {}", accounts.getAccountNumber());
        AccountCustomerResponse customerResponse = new AccountCustomerResponse();
        BeanUtils.copyProperties(customer, customerResponse);

        AccountsDto accountsDto = new AccountsDto();
        BeanUtils.copyProperties(accounts, accountsDto);
        customerResponse.setAccountsDto(accountsDto);
        log.info("AccountCustomerResponse created for mobile number: {}", customerResponse.getMobileNumber());
        return customerResponse;
    }

    @Override
    public AccountCustomerResponse getAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(()-> new RuntimeException(MOBILE_NUMBER_NOT_FOUND + mobileNumber));
        log.info("Customer found: {}", customer);
        Accounts accounts = accountRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(() -> new RuntimeException("Account Not Found for Mobile Number "+mobileNumber));
        log.info("Accounts found: {}", accounts);
        AccountCustomerResponse accountCustomerResponse = new AccountCustomerResponse();
        BeanUtils.copyProperties(customer, accountCustomerResponse);
        AccountsDto accountsDto = new AccountsDto();
        BeanUtils.copyProperties(accounts, accountsDto);
        accountCustomerResponse.setAccountsDto(accountsDto);
        return accountCustomerResponse;
    }

    @Override
    @Transactional
    public AccountCustomerResponse updateAccount(AccountCustomerResponse customerDto) {
        if (customerDto == null) {
            throw new IllegalArgumentException("customerDto must not be null");
        }
        log.info("Updating account for mobile number: {}", customerDto.getMobileNumber());
        var accountDto = customerDto.getAccountsDto();
        if (accountDto == null) {
            throw new IllegalArgumentException("accountsDto must not be null");
        }

        final Accounts accounts = accountRepository.findById(accountDto.getAccountNumber())
                .orElseThrow(() -> new RuntimeException("Account Not Found for Account Number " + accountDto.getAccountNumber()));
        log.info("Existing account found: {}", accounts);
        BeanUtils.copyProperties(accountDto, accounts);
        final Accounts saved = accountRepository.save(accounts);
        log.info("Account updated: {}", saved);
        final Customer customer = customerRepository.findById(saved.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer Not Found for Customer ID " + saved.getCustomerId()));
        BeanUtils.copyProperties(customerDto, customer);
        final Customer savedCustomer = customerRepository.save(customer);
        log.info("Customer updated: {}", savedCustomer);
        final AccountCustomerResponse response = new AccountCustomerResponse();
        BeanUtils.copyProperties(savedCustomer, response);
        log.info("Preparing response for updated account");
        final AccountsDto updatedDto = new AccountsDto();
        BeanUtils.copyProperties(saved, updatedDto);
        response.setAccountsDto(updatedDto);

        return response;
    }

    public Boolean deleteAccounts(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(()-> new RuntimeException(MOBILE_NUMBER_NOT_FOUND + mobileNumber));
        Optional<Accounts> accounts = accountRepository.findByCustomerId(customer.getCustomerId());
        if(accounts.isPresent()){
            accountRepository.delete(accounts.get());
            customerRepository.deleteById(customer.getCustomerId());
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public Boolean deleteAccount(String mobileNumber) {
        if (mobileNumber == null || mobileNumber.isBlank()) {
            throw new IllegalArgumentException("mobileNumber must not be null or blank");
        }
        Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new RuntimeException(MOBILE_NUMBER_NOT_FOUND + mobileNumber));
        log.info("Customer found for deletion: {}", customer);
        return accountRepository.findByCustomerId(customer.getCustomerId())
                .map(account -> {
                    accountRepository.delete(account);
                    customerRepository.deleteById(customer.getCustomerId());
                    return true;
                })
                .orElse(false);
    }


    private Accounts createAccount(Customer customer){
        Accounts accounts = new Accounts();
        accounts.setAccountType("SAVING");
        accounts.setBranchAdders("HYD");
        accounts.setCustomerId(customer.getCustomerId());
        long accountNumber = ThreadLocalRandom.current().nextLong(1_000_000L, 100_000_000L);
        accounts.setAccountNumber(accountNumber);
        return accounts;
    }
}
