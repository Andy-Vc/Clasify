package com.clasify.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.clasify.model.Curso;

public interface ICursoRepository extends JpaRepository<Curso, String> {
	List<Curso> findAllByOrderByNombreCursoDesc();
	
	List<Curso> findByIdCursoStartingWith(String prefijo);

	Optional<Curso> findByIdCurso(String idCurso);

	@Query("SELECT COUNT(C) FROM Curso C")
	long countCursos();
	
	@Modifying
	List<Curso> findByIdProfesor_IdUsuario(Integer idUsuario);	
}
