package com.example.springdemo.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springdemo.service.LeadService;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
final class MongoDBLeadService implements LeadService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MongoDBLeadService.class);

    private final LeadRepository repository;

    @Autowired
    MongoDBLeadService(LeadRepository repository) {
        this.repository = repository;
    }

    @Override
    public LeadDTO create(LeadDTO lead) {
        LOGGER.info("Creating a new lead entry with information: {}", lead);

        Lead persisted = Lead.getBuilder()
                .firstName(lead.getLastName())
                .lastName(lead.getFirstName())
                .companyName(lead.getCompanyName())
                .emailAddress(lead.getEmailAddress())
                .jobTitle(lead.getJobTitle())
                .phoneNumber(lead.getPhoneNumber())
                .username(lead.getUsername())
                .build();

        persisted = repository.save(persisted);
        LOGGER.info("Created a new lead entry with information: {}", persisted);

        return convertToDTO(persisted);
    }

    @Override
    public LeadDTO delete(String id) {
        LOGGER.info("Deleting a lead entry with id: {}", id);

        Lead deleted = findLeadById(id);
        repository.delete(deleted);

        LOGGER.info("Deleted lead entry with informtation: {}", deleted);

        return convertToDTO(deleted);
    }

    @Override
    public List<LeadDTO> findAll() {
        LOGGER.info("Finding all lead entries.");

        List<Lead> leadEntries = repository.findAll();

        LOGGER.info("Found {} lead entries", leadEntries.size());

        return convertToDTOs(leadEntries);
    }

    @Override
    public List<LeadDTO> findByUsername(String username) {
        LOGGER.info("Finding all lead entries with username: {}", username);

        List<Lead> leadEntries = repository.findByUsername(username);

        LOGGER.info("Found {} lead entries", leadEntries.size());

        return convertToDTOs(leadEntries);
    }

    private List<LeadDTO> convertToDTOs(List<Lead> models) {
        return models.stream()
                .map(this::convertToDTO)
                .collect(toList());
    }
    
    @Override
    public LeadDTO findById(String id) {
        LOGGER.info("Finding lead entry with id: {}", id);

        Lead found = findLeadById(id);

        LOGGER.info("Found lead entry: {}", found);

        return convertToDTO(found);
    }

    @Override
    public LeadDTO update(LeadDTO lead) {
        LOGGER.info("Updating lead entry with information: {}", lead);

        Lead updated = findLeadById(lead.getId());
        updated.update(lead.getFirstName(), 
        			   lead.getLastName(),
        			   lead.getCompanyName(),
        			   lead.getEmailAddress(),
        			   lead.getJobTitle(),
        			   lead.getPhoneNumber());
        updated = repository.save(updated);

        LOGGER.info("Updated lead entry with information: {}", updated);

        return convertToDTO(updated);
    }

    private Lead findLeadById(String id) {
        Optional<Lead> result = repository.findOne(id);
        return result.orElseThrow(() -> new LeadNotFoundException(id));

    }

    private LeadDTO convertToDTO(Lead model) {
    	LeadDTO dto = new LeadDTO();

        dto.setId(model.getId());
        dto.setFirstName(model.getFirstName());
        dto.setLastName(model.getLastName());
        dto.setCompanyName(model.getCompanyName());
        dto.setEmailAddress(model.getEmailAddress());
        dto.setJobTitle(model.getJobTitle());
        dto.setLastName(model.getLastName());
        dto.setPhoneNumber(model.getPhoneNumber());
        dto.setUsername(model.getUsername());

        return dto;
    }
}
