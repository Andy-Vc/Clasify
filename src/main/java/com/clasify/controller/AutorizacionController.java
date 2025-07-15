package com.clasify.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clasify.config.JwtUtil;
import com.clasify.dto.AutenticacionUsuario;
import com.clasify.dto.ResultadoResponse;
import com.clasify.dto.UsuarioDTO;
import com.clasify.mapper.UsuarioMapper;
import com.clasify.model.Usuario;
import com.clasify.service.UsuarioService;

@RequestMapping("/auth")
@RestController
public class AutorizacionController {
	private final UsuarioService usuarioService;
	private final JwtUtil jwtUtil;

	public AutorizacionController(UsuarioService usuarioService, JwtUtil jwtUtil) {
		this.usuarioService = usuarioService;
		this.jwtUtil = jwtUtil;
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

	        String token = jwtUtil.generateToken(
	            usuario.getEmail(),
	            usuario.getNombreCompleto(),
	            usuario.getRol()
	        );

	        UsuarioDTO usuarioDTO = UsuarioMapper.toDTO(usuario);
	        
	        Map<String, Object> response = new HashMap<>();
	        response.put("token", token);
	        response.put("usuario", usuarioDTO);

	        return ResponseEntity.ok(response);

	    } catch (RuntimeException e) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
	    }
	}
}
