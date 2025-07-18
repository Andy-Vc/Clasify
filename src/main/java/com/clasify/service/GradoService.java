package com.clasify.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.clasify.dto.ResultadoResponse;
import com.clasify.model.Grado;
import com.clasify.repository.IGradoRepository;

@Service
public class GradoService {
	private IGradoRepository repository;

	public GradoService(IGradoRepository repository) {
		this.repository = repository;
	}	
	
	public List<Grado> getAll(){
		return repository.findAll();
	}
	
	public Grado getOne(Integer id) {
		return repository.findById(id).orElseThrow();
	}
	
	public ResultadoResponse create(Grado grado) {
		try {
			if(repository.findByNombreGrado(grado.getNombreGrado()) != null) {
				return new ResultadoResponse(false, "El nombre de grado "+grado.getNombreGrado() +" ya existe en el sistema");
			}
			
			String mensaje = "Grado " + grado.getNombreGrado() + " registrado correctamente";
			repository.save(grado);
			return new ResultadoResponse(true, mensaje);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultadoResponse(false, e.getMessage());
		}
	}
	
	public ResultadoResponse update(Grado grado) {
		try {
			if(repository.findByNombreGrado(grado.getNombreGrado()) != null) {
				return new ResultadoResponse(false, "El nombre de grado "+grado.getNombreGrado() +" ya existe en el sistema");
			}
			
			String mensaje = "Grado " + grado.getNombreGrado() + " actualizado correctamente";
			repository.save(grado);
			return new ResultadoResponse(true, mensaje);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultadoResponse(false, e.getMessage());
		}
	}
	
	public ResultadoResponse delete(Integer id) {
		Grado grado = this.getOne(id);
		String accion = grado.getFlgEliminado() ? "Activado" : "Eliminado";
		grado.setFlgEliminado(!grado.getFlgEliminado());
		
		try {
			Grado deleted = repository.save(grado);
			String mensaje = String.format("%s ha sido %s correctamente", deleted.getNombreGrado(), accion.toLowerCase());
			return new ResultadoResponse(true, mensaje);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultadoResponse(false, e.getMessage());
		}	
	}
}
