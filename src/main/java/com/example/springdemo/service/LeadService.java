package com.example.springdemo.service;

import java.util.List;

import com.example.springdemo.domain.LeadDTO;

public interface LeadService {
 
    LeadDTO create(LeadDTO lead);
 
    LeadDTO delete(String id);
 
    List<LeadDTO> findAll();
    
    List<LeadDTO> findByUsername(String username);
 
    LeadDTO findById(String id);
 
    LeadDTO update(LeadDTO lead);
}
