package com.tmcustomizer.cardeditor;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.tmcustomizer.cardeditor.model.Card;
import com.tmcustomizer.cardeditor.repository.CardRepository;

@SpringBootTest(classes = CardEditorApplication.class)
@ActiveProfiles("test")

class CardEditorApplicationTests {
    private static final Logger logger = LoggerFactory.getLogger(CardEditorApplicationTests.class);

    @Autowired
    private CardRepository cardRepository;

    @Test
    void testInsertAndDelete() {
        Card newCard = new Card();
        newCard.setName("NewCard");
        Card savedCard = cardRepository.save(newCard);
        assertThat(cardRepository.findById(savedCard.getId())).isPresent();
        List<Card> cardList = cardRepository.findAll();
        logger.info("\nCardList: {}\n", cardList);
        cardRepository.delete(savedCard);
        cardList = cardRepository.findAll();
        assertThat(cardRepository.findById(savedCard.getId())).isNotPresent();
        logger.info("\nCardList: {}\n", cardList);

    }
}

