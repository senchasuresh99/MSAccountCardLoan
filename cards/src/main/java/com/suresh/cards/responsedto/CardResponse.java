package com.suresh.cards.responsedto;

import jakarta.validation.constraints.Pattern;

public class CardResponse {

    @Pattern(regexp = "^[0-9]{10}$", message = "Mobile number must be exactly 10 digits")
    private String mobileNumber;
    @Pattern(regexp = "^[0-9]{14}$", message = "Card number must be exactly 14 digits")
    private String cardNumber;
    private String cardType;
    private Long totalLimit;
    private Long amountUsed;
    private Long availableAmount;

    public CardResponse() {
    }

    public CardResponse(String mobileNumber, String cardNumber, String cardType, Long totalLimit, Long amountUsed, Long availableAmount) {
        this.mobileNumber = mobileNumber;
        this.cardNumber = cardNumber;
        this.cardType = cardType;
        this.totalLimit = totalLimit;
        this.amountUsed = amountUsed;
        this.availableAmount = availableAmount;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public Long getTotalLimit() {
        return totalLimit;
    }

    public void setTotalLimit(Long totalLimit) {
        this.totalLimit = totalLimit;
    }

    public Long getAmountUsed() {
        return amountUsed;
    }

    public void setAmountUsed(Long amountUsed) {
        this.amountUsed = amountUsed;
    }

    public Long getAvailableAmount() {
        return availableAmount;
    }

    public void setAvailableAmount(Long availableAmount) {
        this.availableAmount = availableAmount;
    }

    @Override
    public String toString() {
        return "CardResponse{" +
                "mobileNumber='" + mobileNumber + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", cardType='" + cardType + '\'' +
                ", totalLimit=" + totalLimit +
                ", amountUsed=" + amountUsed +
                ", availableAmount=" + availableAmount +
                '}';
    }
}
