package com.clasify.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.clasify.dto.EstudianteCursoId;
import com.clasify.model.EstudiantesCurso;

public interface IEstudianteCursoRepository extends JpaRepository<EstudiantesCurso, EstudianteCursoId> {
	@Query("SELECT e.curso.id, COUNT(e.estudiante) FROM EstudiantesCurso e GROUP BY e.curso.id")
    List<Object[]> countEstudiantesPorCurso();

}
