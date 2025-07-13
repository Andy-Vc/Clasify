package com.clasify.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clasify.model.Curso;

public interface ICursoRepository extends JpaRepository<Curso, String> {
	List<Curso> findAllByOrderByNombreCursoDesc();
}
