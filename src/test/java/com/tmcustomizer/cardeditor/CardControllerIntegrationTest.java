package com.tmcustomizer.cardeditor;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.sql.Connection;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.tmcustomizer.cardeditor.model.Card;
import com.tmcustomizer.cardeditor.repository.CardRepository;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class CardControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private DataSource dataSource;

    @Test
    void testDataSourceUrl() throws Exception {
        try (Connection connection = dataSource.getConnection()) {
            String url = connection.getMetaData().getURL();
            System.out.println("Database URL: " + url);
            assert url.equals("jdbc:postgresql://localhost:5432/custom_card_editor_test");
        }
    }


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

    // @Test 
    // void testPutCardIntoDB() throws Exception{
    //     cardRepository
    // }

}
