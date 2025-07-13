package com.clasify.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clasify.dto.ResultadoResponse;
import com.clasify.model.Usuario;
import com.clasify.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	private final UsuarioService usuarioService;

	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	@PostMapping("/crear")
	public ResponseEntity<ResultadoResponse> crearStudent(@RequestBody Usuario usuario) {
		try {
			ResultadoResponse resultado = usuarioService.createStudent(usuario);
			return ResponseEntity.ok(resultado);
		} catch (Exception e) {
			e.printStackTrace();
			ResultadoResponse errorResponse = new ResultadoResponse(false, e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
	}
}
