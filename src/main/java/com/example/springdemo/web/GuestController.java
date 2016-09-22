package com.example.springdemo.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.springdemo.domain.LeadDTO;
import com.example.springdemo.domain.LeadNotFoundException;
import com.example.springdemo.service.LeadService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/leads/guest")
final class GuestController {

	private static final Logger LOGGER = LoggerFactory.getLogger(GuestController.class);

    private LeadService service;
    
    @Autowired
    GuestController(LeadService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    LeadDTO createGuest(@RequestBody @Valid LeadDTO leadEntry) {
    	
        String reqUser = "guest";

    	leadEntry.setUsername(reqUser);
    	
        LOGGER.info("Creating a new lead entry with information: {}", leadEntry);

        LeadDTO created = service.create(leadEntry);
        LOGGER.info("Created a new lead entry with information: {}", created);

        return created;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    LeadDTO delete(@PathVariable("id") String id) {
        LOGGER.info("Deleting lead entry with id: {}", id);

        LeadDTO deleted = service.delete(id);
        LOGGER.info("Deleted lead entry with information: {}", deleted);

        return deleted;
    }

    @RequestMapping(method = RequestMethod.GET)
    List<LeadDTO> findAll() {
        LOGGER.info("Finding all lead entries");

        List<LeadDTO> leadEntries = service.findAll();
        LOGGER.info("Found {} lead entries", leadEntries.size());

        return leadEntries;
    }
    
    @RequestMapping(value = "findGuest", method = RequestMethod.GET,
			produces = "application/json; charset=UTF-8")
    List<LeadDTO> findGuest() {

        String reqUser = "guest";

    	LOGGER.info("accessed resource: " + reqUser);


    	List<LeadDTO> leadEntries = service.findByUsername(reqUser);
    	LOGGER.info("Found lead entries with information: {}", leadEntries);

    	return leadEntries;
    }
    
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    LeadDTO findById(@PathVariable("id") String id) {
        LOGGER.info("Finding lead entry with id: {}", id);

        LeadDTO leadEntry = service.findById(id);
        LOGGER.info("Found lead entry with information: {}", leadEntry);

        return leadEntry;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    LeadDTO update(@RequestBody @Valid LeadDTO leadEntry) {
        LOGGER.info("Updating lead entry with information: {}", leadEntry);

        LeadDTO updated = service.update(leadEntry);
        LOGGER.info("Updated lead entry with information: {}", updated);

        return updated;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleLeadNotFound(LeadNotFoundException ex) {
        LOGGER.error("Handling error with message: {}", ex.getMessage());
    }
    
}