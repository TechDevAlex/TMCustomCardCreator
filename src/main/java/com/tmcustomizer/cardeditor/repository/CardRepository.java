package com.tmcustomizer.cardeditor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tmcustomizer.cardeditor.model.Card;

public interface CardRepository extends JpaRepository<Card, Long> {
    
}
