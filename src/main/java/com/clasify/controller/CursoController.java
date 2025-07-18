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

import com.clasify.dto.CursoDTO;
import com.clasify.dto.CursoEstudiantesDTO;
import com.clasify.dto.ResultadoResponse;
import com.clasify.service.CursoService;

@RestController
@RequestMapping("/curso")
public class CursoController {
	private CursoService cursoService;

	public CursoController(CursoService cursoService) {
		this.cursoService = cursoService;
	}
	
	@GetMapping("/list")
	public ResponseEntity<?> listCurses() {
		try {
			List<CursoDTO> listado = cursoService.getAll();
			return ResponseEntity.ok(listado);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@GetMapping("/detalle/{id}")
	public ResponseEntity<CursoEstudiantesDTO> getCursoDetalle(@PathVariable String id) {
		CursoEstudiantesDTO curso = cursoService.getCurseStudents(id);
	    return ResponseEntity.ok(curso);
	}
	
	@PostMapping("/register")
	public ResponseEntity<ResultadoResponse> createCurse(@RequestBody CursoDTO curso) {
		try {
			ResultadoResponse resultado = cursoService.create(curso);
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
	public ResponseEntity<?> getCurso(@PathVariable("id") String id) {
		try {
			CursoDTO getCurso = cursoService.getOne(id);
			if (getCurso == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró ningún curso con ID: " + id);
			}
			return ResponseEntity.ok(getCurso);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al obtener curso con ID: " + id);
		}
	}

	@PatchMapping("/update")
	public ResponseEntity<ResultadoResponse> updateCurse(@RequestBody CursoDTO curso) {
		try {
			ResultadoResponse resultado = cursoService.update(curso);
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
	public ResponseEntity<ResultadoResponse> deleteCurse(@PathVariable("id") String id) {
		try {
			ResultadoResponse resultado = cursoService.delete(id);
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
