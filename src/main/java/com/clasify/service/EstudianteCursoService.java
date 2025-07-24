package com.clasify.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.clasify.dto.CursoEstudianteGrafic;
import com.clasify.repository.IEstudianteCursoRepository;

@Service
public class EstudianteCursoService {
	private IEstudianteCursoRepository repository;

	public EstudianteCursoService(IEstudianteCursoRepository repository) {
		this.repository = repository;
	}
	
	public List<CursoEstudianteGrafic> getStudentsCurse() {
	    List<Object[]> resultado = repository.countEstudiantesPorCurso();
	    return resultado.stream()
	        .map(obj -> new CursoEstudianteGrafic((String) obj[0], ((Number) obj[1]).intValue()))
	        .collect(Collectors.toList());
	}

}
