package com.suresh.accounts.service;

import com.suresh.accounts.accountcustomerresponsedto.AccountCustomerResponse;

public interface CustomerService {

    AccountCustomerResponse fetchCustomerDetails(String mobileNumber);
}
