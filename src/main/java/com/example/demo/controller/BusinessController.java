package com.example.demo.controller;
import com.example.demo.model.Business;
import com.example.demo.model.BusinessRegistrationRequest;
import com.example.demo.repository.BusinessRepository;
import com.example.demo.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BusinessController {

    @Autowired
    private BusinessRepository businessRepository;

    @PostMapping(value = "/businesses", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<?> registerBusiness(@ModelAttribute BusinessRegistrationRequest request) {
        // Check if the username already exists
        if (businessRepository.findByUsername(request.getUsername()) != null) {
            return ResponseEntity.badRequest().body("Username already exists");
        }

        // Create a new Business entity and save it to the database
        Business business = new Business();
        business.setUsername(request.getUsername());
        business.setPassword(request.getPassword());
        businessRepository.save(business);

        return ResponseEntity.ok().body("Business registered successfully");
    }
}


