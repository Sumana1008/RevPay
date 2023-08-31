package com.example.demo.service;

import com.example.demo.model.Business;
import com.example.demo.repository.BusinessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BusinessService {
    @Autowired
    private BusinessRepository businessRepository;


    public List<Business> getAllBusinesses() {
        return businessRepository.findAll();
    } /*This method retrieves a list of all businesses
    from the database using the BusinessRepository's findAll() method. It returns a List<Business> containing all business entities.*/

    public Business getBusinessById(Long id) {
        Optional<Business> optionalBusiness = businessRepository.findById(id);
        return optionalBusiness.orElse(null);
    } /*This method retrieves a single business by its ID from the database using the BusinessRepository's findById(id) method.
    It uses an Optional<Business> to handle the  possibility that the business with the given ID might not exist.
    If the business exists, it's returned; otherwise, null is returned.*/

    public Business createBusiness(Business business) {
        return businessRepository.save(business);/*This method is used to create a new business.
    It takes a Business object as a parameter, which represents the business to be created. The BusinessRepository's save(business)
         method is used to persist the new business entity in the database. The method then returns the newly created business entity.*/
    }

}

