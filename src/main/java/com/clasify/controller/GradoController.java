package com.clasify.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clasify.dto.ResultadoResponse;
import com.clasify.model.Grado;
import com.clasify.service.GradoService;

@RestController
@RequestMapping("/grado")
public class GradoController {
	private GradoService gradoService;

	public GradoController(GradoService gradoService) {
		this.gradoService = gradoService;
	}

	@GetMapping("/list")
	public ResponseEntity<?> listGrades() {
		try {
			List<Grado> listado = gradoService.getAll();
			return ResponseEntity.ok(listado);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@PostMapping("/register")
	public ResponseEntity<ResultadoResponse> createGrade(@RequestBody Grado grado) {
		try {
			ResultadoResponse resultado = gradoService.create(grado);
			if (!resultado.isValor()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resultado);
			}
			return ResponseEntity.ok(resultado);
		} catch (Exception e) {
			e.printStackTrace();
			ResultadoResponse errorResponse = new ResultadoResponse(false, e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<?> getGrado(@PathVariable("id") Integer id) {
		try {
			Grado getGrado = gradoService.getOne(id);
			if (getGrado == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró ningún grado con ID: " + id);
			}
			return ResponseEntity.ok(getGrado);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al obtener grado con ID: " + id);
		}
	}

	@PatchMapping("/update")
	public ResponseEntity<ResultadoResponse> updateGrade(@RequestBody Grado grado) {
		try {
			ResultadoResponse resultado = gradoService.update(grado);
			if (!resultado.isValor()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resultado);
			}
			return ResponseEntity.ok(resultado);
		} catch (Exception e) {
			e.printStackTrace();
			ResultadoResponse errorResponse = new ResultadoResponse(false, e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ResultadoResponse> deleteGrade(@PathVariable("id") Integer id) {
		try {
			ResultadoResponse resultado = gradoService.delete(id);
			if (!resultado.isValor()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resultado);
			}
			return ResponseEntity.ok(resultado);
		} catch (Exception e) {
			e.printStackTrace();
			ResultadoResponse errorResponse = new ResultadoResponse(false, e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
	}

}
