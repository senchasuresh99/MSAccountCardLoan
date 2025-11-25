package com.suresh.cards.controller;

import com.suresh.cards.responsedto.CardResponse;
import com.suresh.cards.service.CardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cards")
public class CardsRestController {

    private final CardService cardService;
    public CardsRestController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping
    public ResponseEntity<CardResponse> createCard(@RequestBody CardResponse cardResponse) throws IllegalAccessException {
        return new ResponseEntity<>(cardService.createCard(cardResponse), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<CardResponse> getCards(@RequestParam String mobileNumber){
        return new ResponseEntity<>(cardService.getCards(mobileNumber), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<CardResponse> updateCard(@RequestBody CardResponse cardResponse) throws IllegalAccessException {
        return new ResponseEntity<>(cardService.updateCard(cardResponse),HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Boolean> deleteCard(@RequestParam String mobileNumber) {
        return new ResponseEntity<>(cardService.deleteCard(mobileNumber), HttpStatus.OK);
    }
}
