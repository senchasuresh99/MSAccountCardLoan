package com.suresh.cards.service;

import com.suresh.cards.entities.Cards;
import com.suresh.cards.repository.CardRepository;
import com.suresh.cards.responsedto.CardResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class CardServiceImpl implements CardService {

    private static final Logger log = LoggerFactory.getLogger(CardServiceImpl.class);
    private static final String PROVIDED_MOBILE_NUMBER_IS_NULL = "Provided mobile number is null";
    private final CardRepository cardRepository;
    public CardServiceImpl(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }


    @Override
    public CardResponse createCard(CardResponse cardResponse) throws IllegalAccessException {
        log.info("Start createCard method in CardServiceImpl");
        cardRepository.findByMobileNumber(cardResponse.getMobileNumber()).ifPresent(existingCard -> {
            throw new IllegalArgumentException("Card already exists for mobile number: " + cardResponse.getMobileNumber());
        });
        Cards cards = new Cards();
        BeanUtils.copyProperties(cardResponse, cards);
        String cardNumber = String.valueOf(
                ThreadLocalRandom.current().nextLong(1_000_000_000_0000L, 10_000_000_000_0000L)
        );
        cards.setCardNumber(cardNumber);
        Cards savedCard = cardRepository.save(cards);
        CardResponse response = new CardResponse();
        BeanUtils.copyProperties(savedCard, response);
        log.info("End createCard method in CardServiceImpl");
        return response;
    }

    @Override
    public CardResponse getCards(String mobileNumber) {
        if(mobileNumber != null) {
            Cards cards = cardRepository.findByMobileNumber(mobileNumber).orElseThrow(() -> new RuntimeException("Cards Not Found for Mobile Number " + mobileNumber));
            CardResponse cardResponse = new CardResponse();
            BeanUtils.copyProperties(cards, cardResponse);
            return cardResponse;
        } else {
            log.error(PROVIDED_MOBILE_NUMBER_IS_NULL);
            throw new IllegalArgumentException(PROVIDED_MOBILE_NUMBER_IS_NULL);
        }
    }

    @Override
    public CardResponse updateCard(CardResponse cardResponse) {
        if (cardResponse == null) {
            log.debug("Provided cardResponse is null");
            throw new IllegalArgumentException("Provided cardResponse is null");
        }
        if (cardResponse.getMobileNumber() == null) {
            log.error(PROVIDED_MOBILE_NUMBER_IS_NULL);
            throw new IllegalArgumentException(PROVIDED_MOBILE_NUMBER_IS_NULL);
        }

        Cards existingCard = cardRepository.findByMobileNumber(cardResponse.getMobileNumber())
                .orElseThrow(() -> new RuntimeException("Card Not Found for Mobile Number " + cardResponse.getMobileNumber()));

        // Prevent overwriting the existing cardNumber
        BeanUtils.copyProperties(cardResponse, existingCard, "cardNumber");

        Cards updatedCard = cardRepository.save(existingCard);
        CardResponse response = new CardResponse();
        BeanUtils.copyProperties(updatedCard, response);
        return response;
    }



    @Override
    public Boolean deleteCard(String mobileNumber) {
        if(mobileNumber != null){
            Optional<Cards> cardsOptional = cardRepository.findByMobileNumber(mobileNumber);
            if(cardsOptional.isPresent()){
                cardRepository.delete(cardsOptional.get());
                return true;
            } else {
                log.info("No cards found for mobile number: {}", mobileNumber);
                return false;
            }
        } else {
            log.debug(PROVIDED_MOBILE_NUMBER_IS_NULL);
            throw new IllegalArgumentException(PROVIDED_MOBILE_NUMBER_IS_NULL);
        }
    }
}
