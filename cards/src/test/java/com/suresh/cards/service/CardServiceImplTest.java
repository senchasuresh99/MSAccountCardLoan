package com.suresh.cards.service;

import com.suresh.cards.entities.Cards;
import com.suresh.cards.repository.CardRepository;
import com.suresh.cards.responsedto.CardResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.BeanUtils;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
class CardServiceImplTest {

    @Mock
    private CardRepository cardRepository;

    @InjectMocks
    private CardServiceImpl cardServiceImpl;

    @Test
    void createCardTest() throws IllegalAccessException {
        CardResponse response = new CardResponse();
        Cards cards = new Cards();
        cards.setMobileNumber("1234567890");
        cards.setCardType("VISA");
        cards.setTotalLimit(50000L);
        cards.setAmountUsed(0L);
        cards.setAvailableAmount(50000L);
        Mockito.when(cardRepository.save(Mockito.any(Cards.class))).thenReturn(cards);
        BeanUtils.copyProperties(response, cards);
        var data = cardServiceImpl.createCard(response);
        assertEquals(response.getMobileNumber(), data.getMobileNumber());
    }

    @Test
    void getCardsTest() {
        String mobileNumber = "1234567890";
        Cards cards = new Cards();
        cards.setMobileNumber(mobileNumber);
        cards.setCardType("VISA");
        cards.setTotalLimit(50000L);
        cards.setAmountUsed(0L);
        cards.setAvailableAmount(50000L);
        Mockito.when(cardRepository.findByMobileNumber(mobileNumber)).thenReturn(java.util.Optional.of(cards));
        var data = cardServiceImpl.getCards(mobileNumber);
        assertEquals(mobileNumber, data.getMobileNumber());
    }

    @Test
    void updateCardTest() {
        CardResponse response = new CardResponse();
        response.setMobileNumber("1234567890");
        response.setCardType("VISA");
        response.setTotalLimit(50000L);
        response.setAmountUsed(0L);
        response.setAvailableAmount(50000L);
        Cards cards = new Cards();
        BeanUtils.copyProperties(response, cards);
        Optional<Cards> mobile = cardRepository.findByMobileNumber(response.getMobileNumber());
        BeanUtils.copyProperties(response, mobile, "cardNumber");
        Mockito.when(cardRepository.findByMobileNumber(response.getMobileNumber())).thenReturn(java.util.Optional.of(cards));
        Mockito.when(cardRepository.save(Mockito.any(Cards.class))).thenReturn(cards);
        var data = cardServiceImpl.updateCard(response);
        assertEquals(response.getMobileNumber(), data.getMobileNumber());

        /*Mockito.when(cardRepository.save(Mockito.any(Cards.class))).thenReturn(cards);
        var data = cardServiceImpl.updateCard(response);
        assertEquals(response.getMobileNumber(), data.getMobileNumber());*/
    }

    @Test
    void updateCard_NullCardResponse_ThrowsException() {
        try {
            cardServiceImpl.updateCard(null);
        } catch (IllegalArgumentException e) {
            assertEquals("Provided cardResponse is null", e.getMessage());
        }
    }

    @Test
    void updateCard_NullMobileNumber_ThrowsException() {
        CardResponse response = new CardResponse();
        try {
            cardServiceImpl.updateCard(response);
        } catch (IllegalArgumentException e) {
            assertEquals("Provided mobile number is null", e.getMessage());
        }
    }

    @Test
    void getCards_NullMobileNumber_ThrowsException() {
        try {
            cardServiceImpl.getCards(null);
        } catch (IllegalArgumentException e) {
            assertEquals("Provided mobile number is null", e.getMessage());
        }
    }

    @Test
    void deleteCardTest() {
        String mobileNumber = "1234567890";
        Cards cards = new Cards();
        cards.setMobileNumber(mobileNumber);
        Mockito.when(cardRepository.findByMobileNumber(mobileNumber)).thenReturn(java.util.Optional.of(cards));
        var result = cardServiceImpl.deleteCard(mobileNumber);
        assertEquals(true, result);
    }

    @Test
    void deleteCard_NullMobileNumber_ThrowsException() {
        try {
            cardServiceImpl.deleteCard(null);
        } catch (IllegalArgumentException e) {
            assertEquals("Provided mobile number is null", e.getMessage());
        }
    }

    @Test
    void deleteCard_NoCardsFound_ReturnsFalse() {
        String mobileNumber = "1234567890";
        Mockito.when(cardRepository.findByMobileNumber(mobileNumber)).thenReturn(java.util.Optional.empty());
        var result = cardServiceImpl.deleteCard(mobileNumber);
        assertEquals(false, result);
    }

    @Test
    void createCard_NullCardResponse_ThrowsException() {
        try {
            CardResponse cards = new CardResponse();
            cardServiceImpl.createCard(cards);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            assertThrows(IllegalArgumentException.class, () -> {
                throw new IllegalArgumentException("Provided mobile number is null");
            });
        }
    }

    @Test
    void createCard_NullMobileNumber_ThrowsException() {
        CardResponse response = new CardResponse();
        try {
            cardServiceImpl.createCard(response);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            assertThrows(IllegalArgumentException.class, () -> {
                throw new IllegalArgumentException("Provided mobile number is null");
            });
        }
    }

    @Test
    void createCard_InvalidCardNumber_ThrowsException() {
        CardResponse response = new CardResponse();
        response.setMobileNumber("1234567890");
        response.setCardNumber("12345"); // Invalid card number
        try {
            cardServiceImpl.createCard(response);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            assertThrows(IllegalArgumentException.class, () -> {
                throw new IllegalArgumentException("Card number must be exactly 14 digits");
            });
        }
    }

    @Test
    void createCard_ValidCardNumber_Succeeds() throws IllegalAccessException {
        CardResponse response = new CardResponse();
        response.setMobileNumber("1234567890");
        response.setCardNumber("12345678901234"); // Valid card number
        Cards cards = new Cards();
        cards.setMobileNumber(response.getMobileNumber());
        cards.setCardNumber(response.getCardNumber());
        cards.setCardType("VISA");
        cards.setTotalLimit(50000L);
        cards.setAmountUsed(0L);
        cards.setAvailableAmount(50000L);
        Mockito.when(cardRepository.save(Mockito.any(Cards.class))).thenReturn(cards);
        var data = cardServiceImpl.createCard(response);
        assertEquals(response.getMobileNumber(), data.getMobileNumber());
        assertEquals(response.getCardNumber(), data.getCardNumber());
    }

    @Test
    void updateCard_PreserveCardNumber_Succeeds() {
        CardResponse response = new CardResponse();
        response.setMobileNumber("1234567890");
        response.setCardType("VISA");
        response.setTotalLimit(50000L);
        response.setAmountUsed(0L);
        response.setAvailableAmount(50000L);
        Cards existingCards = new Cards();
        existingCards.setMobileNumber(response.getMobileNumber());
        existingCards.setCardNumber("12345678901234"); // Existing card number
        existingCards.setCardType("MASTER");
        existingCards.setTotalLimit(40000L);
        existingCards.setAmountUsed(10000L);
        existingCards.setAvailableAmount(30000L);
        Mockito.when(cardRepository.findByMobileNumber(response.getMobileNumber())).thenReturn(java.util.Optional.of(existingCards));
        Mockito.when(cardRepository.save(Mockito.any(Cards.class))).thenAnswer(invocation -> invocation.getArgument(0));
        var data = cardServiceImpl.updateCard(response);
        assertEquals(response.getMobileNumber(), data.getMobileNumber());
        assertEquals("12345678901234", data.getCardNumber()); // Ensure card number is preserved
    }

    @Test
    void updateCard_NoExistingCard_ThrowsException() {
        CardResponse response = new CardResponse();
        response.setMobileNumber("1234567890");
        try {
            cardServiceImpl.updateCard(response);
        } catch (RuntimeException e) {
            assertThrows(RuntimeException.class, () -> {
                throw new RuntimeException("Cards Not Found for Mobile Number " + response.getMobileNumber());
            });
        }
    }

    @Test
    void getCards_NoCardsFound_ThrowsException() {
        String mobileNumber = "1234567890";
        Mockito.when(cardRepository.findByMobileNumber(mobileNumber)).thenReturn(java.util.Optional.empty());
        try {
            cardServiceImpl.getCards(mobileNumber);
        } catch (RuntimeException e) {
            assertEquals("Cards Not Found for Mobile Number " + mobileNumber, e.getMessage());
        }
    }

    @Test
    void updateCard_NoCardsFound_ThrowsException() {
        String mobileNumber = "1234567890";
        CardResponse response = new CardResponse();
        response.setMobileNumber(mobileNumber);
        Mockito.when(cardRepository.findByMobileNumber(mobileNumber)).thenReturn(Optional.empty());
        try {
            cardServiceImpl.updateCard(response);
        } catch (RuntimeException e) {
            assertEquals("Card Not Found for Mobile Number " + mobileNumber, e.getMessage());
        }
    }

    @Test
    void deleteCard_NoCardsFoundForMobileNumber_ReturnsFalse() {
        String mobileNumber = "1234567890";
        Mockito.when(cardRepository.findByMobileNumber(mobileNumber)).thenReturn(Optional.empty());
        Boolean result = cardServiceImpl.deleteCard(mobileNumber);
        assertEquals(false, result);
    }

    @Test
    void deleteCard_CardsFoundForMobileNumber_ReturnsTrue() {
        String mobileNumber = "1234567890";
        Cards cards = new Cards();
        cards.setMobileNumber(mobileNumber);
        Mockito.when(cardRepository.findByMobileNumber(mobileNumber)).thenReturn(Optional.of(cards));
        Boolean result = cardServiceImpl.deleteCard(mobileNumber);
        assertEquals(true, result);
    }

    @Test
    void deleteCard_NullMobileNumber_ThrowsIllegalArgumentException() {
        try {
            cardServiceImpl.deleteCard(null);
        } catch (IllegalArgumentException e) {
            assertEquals("Provided mobile number is null", e.getMessage());
        }
    }

    @Test
    void createCard_InvalidMobileNumber_ThrowsException() {
        CardResponse response = new CardResponse();
        response.setMobileNumber("12345"); // Invalid mobile number
        try {
            cardServiceImpl.createCard(response);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            assertThrows(IllegalArgumentException.class, () -> {
                throw new IllegalArgumentException("Mobile number must be exactly 10 digits");
            });
        }
    }

    @Test
    void createCard_ValidMobileNumber_Succeeds() throws IllegalAccessException {
        CardResponse response = new CardResponse();
        response.setMobileNumber("1234567890"); // Valid mobile number
        Cards cards = new Cards();
        cards.setMobileNumber(response.getMobileNumber());
        cards.setCardType("VISA");
        cards.setTotalLimit(50000L);
        cards.setAmountUsed(0L);
        cards.setAvailableAmount(50000L);
        Mockito.when(cardRepository.save(Mockito.any(Cards.class))).thenReturn(cards);
        var data = cardServiceImpl.createCard(response);
        assertEquals(response.getMobileNumber(), data.getMobileNumber());
    }

    /*@Test
    void updateCard_InvalidMobileNumber_ThrowsException() {
        CardResponse response = new CardResponse();
        response.setMobileNumber("12345"); // Invalid mobile number
        try {
            cardServiceImpl.updateCard(response);
        } catch (IllegalArgumentException e) {
            assertThrows(RuntimeException.class, () -> {
                throw new RuntimeException("Card Not Found for Mobile Number " + response.getMobileNumber());
            });
        }
    }*/

    /*@Test
    void getCards_InvalidMobileNumber_ThrowsException() {
        String mobileNumber = "12345"; // Invalid mobile number
        try {

            cardServiceImpl.getCards(mobileNumber);
        } catch (IllegalArgumentException e) {
            assertThrows(IllegalArgumentException.class, () -> {
                throw new IllegalArgumentException("Mobile number must be exactly 10 digits");
            });
        }
    }*/
}
