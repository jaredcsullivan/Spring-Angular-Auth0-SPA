package com.example.springdemo.domain;

import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

interface LeadRepository extends Repository<Lead, String> {

    void delete(Lead deleted);
    
    List<Lead> findAll();
    
    List<Lead> findByUsername(String Username);
    
    Optional<Lead> findOne(String id);

    Lead save(Lead saved);
    
}