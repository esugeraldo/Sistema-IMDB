package com.edutecno.pruebaFinalSistemaIMDb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.edutecno.pruebaFinalSistemaIMDb.model.Show;


@Repository
public interface ShowRepository extends JpaRepository<Show, Long> {
	public boolean existsByShowTitle(String showTitle);
	public List<Show> findAll();
}
