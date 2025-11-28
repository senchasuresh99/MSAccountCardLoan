package com.suresh.cards.listeners;

import com.suresh.cards.entities.Cards;
import com.suresh.cards.events.AccountCreatedEvent;
import com.suresh.cards.repository.CardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class AccountsListener {

    private static final Logger log = LoggerFactory.getLogger(AccountsListener.class);

    private CardRepository cardRepository;
    public AccountsListener(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @KafkaListener(topics = "AccountCreatedTopic", groupId = "cards-group", containerFactory = "kafkaListenerContainerFactory")
    public void accountsListenerEvents(AccountCreatedEvent accountCreatedEvent) {
        try {
            log.info("Received Account Created Event for Account Number: {}", accountCreatedEvent.getAccountNumber());
            // Here you can add logic to process the event, e.g., create a card for the new account
            Cards newCard = new Cards();
            newCard.setMobileNumber(accountCreatedEvent.getMobileNumber());
            newCard.setCardNumber(String.valueOf(10000000000000L + (long)(Math.random() * 89999999999999L))); // Generate a random 14-digit card number
            newCard.setCardType("VISA");
            newCard.setTotalLimit(50000L); // Example limit
            newCard.setAmountUsed(0L);
            newCard.setAvailableAmount(50000L); // Initial available amount equals total limit
            cardRepository.save(newCard);
            log.info("Processed Account Created Event for Account Number: {}", accountCreatedEvent.getAccountNumber());
        } catch (Exception e) {
            log.error("Error processing Account Created Event for Account Number: {}", accountCreatedEvent.getAccountNumber(), e);
        }
    }
}
