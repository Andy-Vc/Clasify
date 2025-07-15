package com.clasify.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clasify.dto.DashboardResponse;
import com.clasify.service.CursoService;
import com.clasify.service.UsuarioService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	private final CursoService cursoService;
	private final UsuarioService usuarioService;
	
	public AdminController(CursoService cursoService, UsuarioService usuarioService) {
		this.cursoService = cursoService;
		this.usuarioService = usuarioService;
	}
	
	@GetMapping("/dashboard")
	public ResponseEntity<DashboardResponse> dashboard(){
        Long totalProfesores = usuarioService.countTeachers();
        Long totalEstudiantes = usuarioService.countStudents();
        Long totalCursos = cursoService.countCursos();

        DashboardResponse response = new DashboardResponse(
            totalProfesores,
            totalEstudiantes,
            totalCursos
        );

        return ResponseEntity.ok(response);
	}
}
