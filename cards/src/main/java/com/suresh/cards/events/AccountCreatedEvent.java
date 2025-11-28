package com.suresh.cards.events;

import java.io.Serializable;

public class AccountCreatedEvent implements Serializable {
    private static final long serialVersionUID = 1L;
    private  Long accountNumber;
    private  Long coustomerId;
    private  String mobileNumber;

    public AccountCreatedEvent(){

    }
    public AccountCreatedEvent(Long accountNumber, Long coustomerId, String mobileNumber) {
        this.accountNumber = accountNumber;
        this.coustomerId = coustomerId;
        this.mobileNumber = mobileNumber;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Long getCoustomerId() {
        return coustomerId;
    }

    public void setCoustomerId(Long coustomerId) {
        this.coustomerId = coustomerId;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}
