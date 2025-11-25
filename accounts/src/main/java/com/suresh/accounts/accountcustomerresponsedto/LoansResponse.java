package com.suresh.accounts.accountcustomerresponsedto;

import jakarta.validation.constraints.Pattern;

public class LoansResponse {

    @Pattern(regexp = "^[0-9]{10}$", message = "Mobile number must be 10 digits")
    private String mobileNumber;
    private String loanType;
    private String lonaNumber;
    private long totalLoan;
    private long amountPaid;
    private long outstandingAmount;
    private String status;

    public LoansResponse() {
    }
    public LoansResponse(String mobileNumber, String loanType, String lonaNumber, long totalLoan, long amountPaid, long outstandingAmount, String status) {
        this.mobileNumber = mobileNumber;
        this.loanType = loanType;
        this.lonaNumber = lonaNumber;
        this.totalLoan = totalLoan;
        this.amountPaid = amountPaid;
        this.outstandingAmount = outstandingAmount;
        this.status = status;
    }

    public @Pattern(regexp = "^[0-9]{10}$", message = "Mobile number must be 10 digits") String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(@Pattern(regexp = "^[0-9]{10}$", message = "Mobile number must be 10 digits") String mobileNumber) {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "LoansResponse{" +
                "mobileNumber='" + mobileNumber + '\'' +
                ", loanType='" + loanType + '\'' +
                ", lonaNumber='" + lonaNumber + '\'' +
                ", totalLoan=" + totalLoan +
                ", amountPaid=" + amountPaid +
                ", outstandingAmount=" + outstandingAmount +
                ", status='" + status + '\'' +
                '}';
    }
}
