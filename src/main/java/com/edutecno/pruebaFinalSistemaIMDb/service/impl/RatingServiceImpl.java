package com.edutecno.pruebaFinalSistemaIMDb.service.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutecno.pruebaFinalSistemaIMDb.model.Rating;
import com.edutecno.pruebaFinalSistemaIMDb.repository.RatingRepository;
import com.edutecno.pruebaFinalSistemaIMDb.service.GenericService;

@Service
public class RatingServiceImpl implements GenericService<Rating>{

    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public void update(Rating rating) {
        
    }

    @Override
    public void delete(Rating rating) {
        
    }

    @Override
    public Rating findById(Long id) {
        return ratingRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Rating> findALL() {
        return ratingRepository.findAll();
    }

    @Override
    public void create(Rating rating) {
        
        ratingRepository.save(rating);
        
        
    }
 

 
    
}
