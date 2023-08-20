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
    }

    public Business getBusinessById(Long id) {
        Optional<Business> optionalBusiness = businessRepository.findById(id);
        return optionalBusiness.orElse(null);
    }

    public Business createBusiness(Business business) {
        return businessRepository.save(business);
    }

}

