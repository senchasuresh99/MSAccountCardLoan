package com.suresh.accounts.accountcustomerresponsedto;

import com.suresh.accounts.dto.AccountsDto;
import jakarta.validation.constraints.Pattern;

public class AccountCardLoanResponse {
    private String name;
    private String email;
    @Pattern(regexp = "^[0-9]{10}$", message = "Mobile number must be exactly 10 digits")
    private String mobileNumber;
    private AccountsDto accountsDto;
    private CardResponse cardResponse;
    private LoansResponse loansResponse;

    public AccountCardLoanResponse() {
    }

    public AccountCardLoanResponse(String name, String email, String mobileNumber, AccountsDto accountsDto, CardResponse cardResponse, LoansResponse loansResponse) {
        this.name = name;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.accountsDto = accountsDto;
        this.cardResponse = cardResponse;
        this.loansResponse = loansResponse;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public @Pattern(regexp = "^[0-9]{10}$", message = "Mobile number must be exactly 10 digits") String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(@Pattern(regexp = "^[0-9]{10}$", message = "Mobile number must be exactly 10 digits") String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public AccountsDto getAccountsDto() {
        return accountsDto;
    }

    public void setAccountsDto(AccountsDto accountsDto) {
        this.accountsDto = accountsDto;
    }

    public CardResponse getCardResponse() {
        return cardResponse;
    }

    public void setCardResponse(CardResponse cardResponse) {
        this.cardResponse = cardResponse;
    }

    public LoansResponse getLoansResponse() {
        return loansResponse;
    }

    public void setLoansResponse(LoansResponse loansResponse) {
        this.loansResponse = loansResponse;
    }

    @Override
    public String toString() {
        return "AccountCustomerResponse{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", accountsDto=" + accountsDto +
                ", cardResponse=" + cardResponse +
                ", loansResponse=" + loansResponse +
                '}';
    }
}
