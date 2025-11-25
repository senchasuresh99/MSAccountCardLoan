package com.suresh.accounts.dto;


public class AccountsDto {
    private Long accountNumber;
    private String accountType;
    private String branchAdders;

    public AccountsDto() {
    }

    public AccountsDto(Long accountNumber, String accountType, String branchAdders) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.branchAdders = branchAdders;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getBranchAdders() {
        return branchAdders;
    }

    public void setBranchAdders(String branchAdders) {
        this.branchAdders = branchAdders;
    }
}
