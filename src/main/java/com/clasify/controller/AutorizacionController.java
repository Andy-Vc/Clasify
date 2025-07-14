package com.clasify.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clasify.dto.AutenticacionUsuario;
import com.clasify.dto.ResultadoResponse;
import com.clasify.model.Usuario;
import com.clasify.service.UsuarioService;

@RequestMapping("/auth")
@RestController
public class AutorizacionController {
	private final UsuarioService usuarioService;

	public AutorizacionController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	@PostMapping("/register")
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
	
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AutenticacionUsuario filtro) {
        try {
            Usuario usuario = usuarioService.autenticar(filtro);
            return ResponseEntity.ok(usuario);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
