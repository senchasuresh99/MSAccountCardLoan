package com.suresh.cards.service;

import com.suresh.cards.responsedto.CardResponse;

public interface CardService {
    CardResponse createCard(CardResponse cardResponse) throws IllegalAccessException;
    CardResponse getCards(String mobileNumber);
    CardResponse updateCard(CardResponse cardResponse);
    Boolean deleteCard(String mobileNumber);
}
