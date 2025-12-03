package com.suresh.cards.controller;

import com.suresh.cards.entities.Cards;
import com.suresh.cards.responsedto.CardResponse;
import com.suresh.cards.service.CardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.BeanUtils;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(SpringExtension.class)
class CardsRestControllerTest {

    @Mock
    private CardService cardService;

    @InjectMocks
    private CardsRestController cardsRestController;

    private static MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(cardsRestController).build();
    }

    @Test
    void testCreateCard() throws Exception {
        Cards cards = new Cards();
        CardResponse cardResponse = new CardResponse();
        cardResponse.setCardNumber("1234567890123456");
        cardResponse.setMobileNumber("9876543210");
        cardResponse.setCardType("VISA");
        cardResponse.setTotalLimit(500000L);
        cardResponse.setAmountUsed(100000L);
        cardResponse.setAvailableAmount(400000L);
        Mockito.when(cardService.createCard(Mockito.any(CardResponse.class))).thenReturn(cardResponse);
        BeanUtils.copyProperties(cardResponse, cards);
        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post("/api/v1/cards")
                .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                .content("{\"mobileNumber\":\"9876543210\",\"cardType\":\"VISA\",\"totalLimit\":500000,\"amountUsed\":100000,\"availableAmount\":400000}"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.status().isCreated())
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.cardNumber").value("1234567890123456"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.mobileNumber").value("9876543210"));
    }

    @Test
    void testGetCards() throws Exception {
        CardResponse cardResponse = new CardResponse();
        cardResponse.setCardNumber("1234567890123456");
        cardResponse.setMobileNumber("9876543210");
        cardResponse.setCardType("VISA");
        cardResponse.setTotalLimit(500000L);
        cardResponse.setAmountUsed(100000L);
        cardResponse.setAvailableAmount(400000L);
        Mockito.when(cardService.getCards("9876543210")).thenReturn(cardResponse);
        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get("/api/v1/cards")
                .param("mobileNumber", "9876543210"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.status().isOk())
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.cardNumber").value("1234567890123456"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.mobileNumber").value("9876543210"));
    }

    @Test
    void testUpdateCard() throws Exception {
        CardResponse cardResponse = new CardResponse();
        cardResponse.setCardNumber("1234567890123456");
        cardResponse.setMobileNumber("9876543210");
        cardResponse.setCardType("VISA");
        cardResponse.setTotalLimit(600000L);
        cardResponse.setAmountUsed(150000L);
        cardResponse.setAvailableAmount(450000L);
        Mockito.when(cardService.updateCard(Mockito.any(CardResponse.class))).thenReturn(cardResponse);
        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put("/api/v1/cards")
                .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                .content("{\"mobileNumber\":\"9876543210\",\"cardType\":\"VISA\",\"totalLimit\":600000,\"amountUsed\":150000,\"availableAmount\":450000}"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.status().isOk())
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.totalLimit").value(600000))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.amountUsed").value(150000));
    }

    @Test
    void testDeleteCard() throws Exception {
        Mockito.when(cardService.deleteCard("9876543210")).thenReturn(true);
        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete("/api/v1/cards")
                .param("mobileNumber", "9876543210"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.status().isOk())
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.content().string("true"));
    }

    @Test
    void testDeleteCard_NotFound() throws Exception {
        Mockito.when(cardService.deleteCard("1234567890")).thenReturn(false);
        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete("/api/v1/cards")
                .param("mobileNumber", "1234567890"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.status().isOk())
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.content().string("false"));
    }


    @Test
    void testCreateCard_NullInput() throws Exception {
        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post("/api/v1/cards")
                .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                .content(""))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void testUpdateCard_NullInput() throws Exception {
        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put("/api/v1/cards")
                .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                .content(""))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void testGetCards_NullInput() throws Exception {
        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get("/api/v1/cards")
                .param("mobileNumber", (String) null))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void testDeleteCard_NullInput() throws Exception {
        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete("/api/v1/cards")
                .param("mobileNumber", (String) null))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.status().isBadRequest());
    }

}
