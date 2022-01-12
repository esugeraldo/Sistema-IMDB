package com.edutecno.pruebaFinalSistemaIMDb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutecno.pruebaFinalSistemaIMDb.model.Show;
import com.edutecno.pruebaFinalSistemaIMDb.repository.ShowRepository;
import com.edutecno.pruebaFinalSistemaIMDb.service.GenericService;

@Service
public class ShowServiceImpl implements GenericService<Show> {

    @Autowired
    ShowRepository showRepository;

    @Override
    public void update(Show show) {
        if (showRepository.existsByShowTitle(show.getShowTitle())) {
            showRepository.save(show);

        }
    }

    @Override
    public void delete(Show show) {
        showRepository.delete(show);

    }

    @Override
    public void create(Show show) {
        if (!showRepository.existsByShowTitle(show.getShowTitle())) {
            showRepository.save(show);

        }
        
    }

    @Override
    public Show findById(Long id) {
        return showRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Show> findALL() {
        return showRepository.findAll();
    }

}
