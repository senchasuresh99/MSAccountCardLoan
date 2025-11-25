package com.suresh.cards.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Pattern;

@Entity
public class Cards {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cardId;
    @Pattern(regexp = "^[0-9]{10}$", message = "Mobile number must be exactly 10 digits")
    private String mobileNumber;
    @Pattern(regexp = "^[0-9]{14}$", message = "Card number must be exactly 14 digits")
    private String cardNumber;
    private String cardType;
    private Long totalLimit;
    private Long amountUsed;
    private Long availableAmount;

    public Cards() {
    }

    public Cards(Long cardId, String mobileNumber, String cardNumber, String cardType, Long totalLimit, Long amountUsed, Long availableAmount) {
        this.cardId = cardId;
        this.mobileNumber = mobileNumber;
        this.cardNumber = cardNumber;
        this.cardType = cardType;
        this.totalLimit = totalLimit;
        this.amountUsed = amountUsed;
        this.availableAmount = availableAmount;
    }

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
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
}
