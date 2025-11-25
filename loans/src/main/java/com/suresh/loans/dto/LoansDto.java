package com.suresh.loans.dto;

import jakarta.validation.constraints.Pattern;

public class LoansDto {
    @Pattern(regexp = "^[0-9]{10}$", message = "Mobile number must be 10 digits")
    private String mobileNumber;
    private String loanType;
    private String lonaNumber;
    private long totalLoan;
    private long amountPaid;
    private long outstandingAmount;

    public LoansDto() {
    }

    public LoansDto(String mobileNumber, String loanType, String lonaNumber, long totalLoan, long amountPaid, long outstandingAmount) {
        this.mobileNumber = mobileNumber;
        this.loanType = loanType;
        this.lonaNumber = lonaNumber;
        this.totalLoan = totalLoan;
        this.amountPaid = amountPaid;
        this.outstandingAmount = outstandingAmount;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public String getLonaNumber() {
        return lonaNumber;
    }

    public void setLonaNumber(String lonaNumber) {
        this.lonaNumber = lonaNumber;
    }

    public long getTotalLoan() {
        return totalLoan;
    }

    public void setTotalLoan(long totalLoan) {
        this.totalLoan = totalLoan;
    }

    public long getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(long amountPaid) {
        this.amountPaid = amountPaid;
    }

    public long getOutstandingAmount() {
        return outstandingAmount;
    }

    public void setOutstandingAmount(long outstandingAmount) {
        this.outstandingAmount = outstandingAmount;
    }
}
