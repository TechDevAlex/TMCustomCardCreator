package com.tmcustomizer.cardeditor.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tmcustomizer.cardeditor.model.Card;
import com.tmcustomizer.cardeditor.repository.CardRepository;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/cards")
public class CardController {

    private final CardRepository cardRepository;

    public CardController(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @PostMapping
    public Card createCard(@RequestBody Card card) {
        return cardRepository.save(card);
    }

    @GetMapping
    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    @PutMapping("/{id}")
    public Card putCard(@PathVariable Long id, @RequestBody Card card) {
        Card cardToUpdate = cardRepository.findById(id)
            .orElseThrow(EntityNotFoundException::new);
        cardToUpdate.setName(card.getName());
        return cardRepository.save(cardToUpdate);

    }



    
}
