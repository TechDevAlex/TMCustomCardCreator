package com.tmcustomizer.cardeditor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tmcustomizer.cardeditor.model.Card;
import com.tmcustomizer.cardeditor.repository.CardRepository;



@RestController
@RequestMapping("/cards")
public class CardController{

    @Autowired
    private CardRepository cardRepository;

    @PostMapping
    public Card createCard(@RequestBody Card card) { 
       return cardRepository.save(card);
    }
    @GetMapping
    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }
    
    
}


