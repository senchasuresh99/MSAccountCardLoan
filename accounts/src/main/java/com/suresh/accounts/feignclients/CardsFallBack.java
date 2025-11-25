package com.suresh.accounts.feignclients;

import com.suresh.accounts.accountcustomerresponsedto.CardResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CardsFallBack implements CardsFeignClient {
    @Override
    public ResponseEntity<CardResponse> getCards(String mobileNumber) {
        CardResponse cardResponse = new CardResponse();
        cardResponse.setStatus("Cards Service is down. Please try after sometime");
        return ResponseEntity.ok(cardResponse);
    }
}
