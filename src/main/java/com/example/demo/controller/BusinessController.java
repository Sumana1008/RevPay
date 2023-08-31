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
//BusinessController that handles HTTP requests related to business registration.
@RestController
public class BusinessController {

    @Autowired
    private BusinessRepository businessRepository;

    @Autowired
    private BusinessService businessService;
   /* registerBusiness(): Handles a POST request to register a new business.
            Checks if the provided username already exists and creates a new business if not.
    Calls the createBusiness(Business business) method in BusinessService.*/

    @PostMapping(value = "/businesses")
    public ResponseEntity<?> registerBusiness(@ModelAttribute Business request) {
/*This method takes a parameter named request of type BusinessRegistrationRequest.
It uses the @ModelAttribute annotation to bind form data from the request body to the BusinessRegistrationRequest object.
 */
        businessService.createBusiness(request);

        return ResponseEntity.ok().body("Business registered successfully");
    }
}


