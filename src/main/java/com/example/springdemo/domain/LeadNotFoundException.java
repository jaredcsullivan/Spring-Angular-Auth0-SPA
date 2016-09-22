package com.example.springdemo.domain;

public class LeadNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public LeadNotFoundException(String id) {
        super(String.format("No lead entry found with id: <%s>", id));
    }
}
