package com.edutecno.pruebaFinalSistemaIMDb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.edutecno.pruebaFinalSistemaIMDb.model.Rating;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long>{
	
	public List<Rating> findAll();
}
