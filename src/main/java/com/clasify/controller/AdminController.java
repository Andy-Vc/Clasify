package com.clasify.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clasify.dto.CursoEstudianteGrafic;
import com.clasify.dto.CursoEstudiantesDTO;
import com.clasify.dto.DashboardResponse;
import com.clasify.dto.EstudianteDTO;
import com.clasify.dto.UsuarioDTO;
import com.clasify.service.CursoService;
import com.clasify.service.EstudianteCursoService;
import com.clasify.service.UsuarioService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	private final CursoService cursoService;
	private final UsuarioService usuarioService;
	private final EstudianteCursoService estudianteCursoService;

	public AdminController(CursoService cursoService, UsuarioService usuarioService,
			EstudianteCursoService estudianteCursoService) {
		this.cursoService = cursoService;
		this.usuarioService = usuarioService;
		this.estudianteCursoService = estudianteCursoService;
	}

	@GetMapping("/dashboard")
	public ResponseEntity<DashboardResponse> dashboard() {
		Long totalProfesores = usuarioService.countTeachers();
		Long totalEstudiantes = usuarioService.countStudents();
		Long totalCursos = cursoService.countCursos();
		List <UsuarioDTO> usuariosRecientes = usuarioService.getNewlyRegistered();
		List<CursoEstudianteGrafic> estudiantes = estudianteCursoService.getStudentsCurse();

		DashboardResponse response = new DashboardResponse(totalProfesores, totalEstudiantes, totalCursos, estudiantes,usuariosRecientes);

		return ResponseEntity.ok(response);
	}

	@GetMapping("/estudiante")
	public ResponseEntity<?> listStudents() {
		try {
			List<EstudianteDTO> students = usuarioService.getStudentsDTO();
			return ResponseEntity.ok(students);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
}
