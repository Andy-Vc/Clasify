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
import com.clasify.model.Usuario;
import com.clasify.service.UsuarioService;

@RestController
@RequestMapping("/admin/profesor")
public class ProfesorController {
	private final UsuarioService usuarioService;

	public ProfesorController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	@GetMapping("/list")
	public ResponseEntity<?> listTeachers() {
		try {
			List<Usuario> teachers = usuarioService.getTeachers();
			return ResponseEntity.ok(teachers);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@PostMapping("/register")
	public ResponseEntity<ResultadoResponse> crearTeacher(@RequestBody Usuario usuario) {
		try {
			ResultadoResponse resultado = usuarioService.createTeacher(usuario);
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
	public ResponseEntity<?> obtenerTeacher(@PathVariable("id") Integer id) {
		try {
			Usuario getUsuario = usuarioService.getOne(id);
			 if (getUsuario == null) {
		            return ResponseEntity.status(HttpStatus.NOT_FOUND)
		                .body("No se encontró ningún profesor con ID: " + id);
		        }
			return ResponseEntity.ok(getUsuario);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al obtener profesor con ID: " + id);
		}
	}

	@PatchMapping("/update")
	public ResponseEntity<ResultadoResponse> updateTeacher(@RequestBody Usuario usuario) {
		try {
			ResultadoResponse resultado = usuarioService.update(usuario);
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
	public ResponseEntity<ResultadoResponse> deleteTeacher(@PathVariable("id") Integer id) {
		try {
			ResultadoResponse resultado = usuarioService.delete(id);
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
