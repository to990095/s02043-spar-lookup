package com.telenor.spar.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.telenor.spar.model.LookUpResponse;
import com.telenor.spar.service.LookUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.security.NoSuchAlgorithmException;

@RestController
public class LookUpController {

    @Autowired
    LookUpService lookUpService;

    @GetMapping(value = "/lookUp/{identifier}")
    public LookUpResponse participantIdSearch(@PathVariable String identifier) throws JsonProcessingException, NoSuchAlgorithmException {
       return lookUpService.participantLookUp(identifier);
    }
}