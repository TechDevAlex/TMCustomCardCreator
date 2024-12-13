package com.tmcustomizer.cardeditor;

import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.tmcustomizer.cardeditor.model.Card;
import com.tmcustomizer.cardeditor.repository.CardRepository;

@WebMvcTest
class CardControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private CardRepository cardRepository;

    @Test
    void 
    // just testing if the controller works
    testCreateCard() throws Exception {
        Card newCard = new Card();
        newCard.setName("NewCard");
        Mockito.when(cardRepository.save(Mockito.any(Card.class))).thenReturn(newCard);
        mockMvc.perform(post("/cards")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"name\": \"NewCard\"}"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("NewCard"))
        ;
    }

}
