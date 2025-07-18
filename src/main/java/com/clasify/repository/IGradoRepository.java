package com.clasify.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clasify.model.Grado;

public interface IGradoRepository extends JpaRepository<Grado, Integer>{
	Grado findByNombreGrado(String nombre);
}
