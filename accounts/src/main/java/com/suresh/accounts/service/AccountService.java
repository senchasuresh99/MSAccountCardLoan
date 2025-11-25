package com.suresh.accounts.service;

import com.suresh.accounts.accountcustomerresponsedto.AccountCustomerResponse;

public interface AccountService {
    AccountCustomerResponse createAccount(AccountCustomerResponse accountCustomerResponse);
    AccountCustomerResponse getAccount(String mobileNumber);
    AccountCustomerResponse updateAccount(AccountCustomerResponse customerDto);
    Boolean deleteAccount(String mobileNumber);
}
