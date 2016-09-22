package com.example.springdemo.service;

import com.auth0.spring.security.api.Auth0JWTToken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.springdemo.Auth0Client;

@Service
public class UsernameService {

    @Autowired
    private Auth0Client auth0Client;

    public String getUsername() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();        
        return auth0Client.getUsername((Auth0JWTToken) authentication);
    }

}
