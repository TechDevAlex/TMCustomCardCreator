package com.tmcustomizer.cardeditor;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.tmcustomizer.cardeditor.model.Card;
import com.tmcustomizer.cardeditor.repository.CardRepository;

@SpringBootTest
@AutoConfigureMockMvc
class CardControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CardRepository cardRepository;

    @Test
    void testGetAllCards() throws Exception {

        cardRepository.deleteAll();
        Card card1 = new Card();
        card1.setName("Card1");
        Card card2 = new Card();
        card2.setName("Card2");
        cardRepository.save(card1);
        cardRepository.save(card2);
        mockMvc.perform(get("/cards")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Card1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Card2"));
    }

}
