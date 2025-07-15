package com.clasify.service;

import org.springframework.stereotype.Service;

import com.clasify.repository.ICursoRepository;

@Service
public class CursoService {
	
	private final ICursoRepository repository;
	
	public CursoService(ICursoRepository repository) {
		this.repository = repository;
	}
	
	public long countCursos() {
		return repository.countCursos();
	}
}
