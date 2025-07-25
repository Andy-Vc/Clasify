package com.clasify.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clasify.config.JwtUtil;
import com.clasify.dto.CursoEstudiantesDTO;
import com.clasify.dto.UsuarioDTO;
import com.clasify.service.CursoService;
import com.clasify.service.UsuarioService;

@RestController
@RequestMapping("/profesor/curso")
public class CursoProfesorController {
	private CursoService cursoService;
	private UsuarioService usuarioService;
	private final JwtUtil jwtUtil;
	
	public CursoProfesorController(CursoService cursoService, UsuarioService usuarioService, JwtUtil jwtUtil) {
		this.cursoService = cursoService;
		this.usuarioService = usuarioService;
		this.jwtUtil = jwtUtil;
	}

	@GetMapping("/list")
	public ResponseEntity<?> list(@RequestHeader("Authorization") String authHeader) {
	    try {
	        String token = authHeader.replace("Bearer ", "");

	        String email = jwtUtil.extractUsername(token);

	        UsuarioDTO profesor = usuarioService.getUsuarioDTOByEmail(email);

	        List<CursoEstudiantesDTO> cursos = cursoService.getAllByTeacher(profesor.getIdUsuario());

	        return ResponseEntity.ok(cursos);
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token inválido o expirado." + e.getMessage());
	    }
	}

}
