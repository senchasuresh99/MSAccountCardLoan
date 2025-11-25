package com.suresh.accounts.service;

import com.suresh.accounts.accountcustomerresponsedto.AccountCardLoanResponse;
import org.jetbrains.annotations.NotNull;

public interface CustomerService {

    @NotNull AccountCardLoanResponse fetchCustomerDetails(String mobileNumber);
}
