package com.clasify.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.clasify.dto.CursoDTO;
import com.clasify.dto.NotaDTO;
import com.clasify.dto.ResultadoResponse;
import com.clasify.service.NotaService;

@RestController
@RequestMapping("/profesor/nota")
public class NotaProfesorController {
	private NotaService notaService;

	public NotaProfesorController(NotaService notaService) {
		this.notaService = notaService;
	}

	@PostMapping("/saveOrUpdate")
	public ResponseEntity<ResultadoResponse> saveOrUpdateNota(@RequestBody NotaDTO dto) {
		try {
			ResultadoResponse resultado = notaService.saveOrUpdateNota(dto);
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

	@GetMapping("/nota")
	public ResponseEntity<?> getNota(@RequestParam String idCurso, @RequestParam Integer idUsuario,
			@RequestParam Integer nroNota) {
		try {
			Optional<NotaDTO> notaDTOOpt = notaService.findNotaDTOByCursoUsuarioNroNota(idCurso, idUsuario, nroNota);

			if (notaDTOOpt.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró la nota para el curso "
						+ idCurso + ", usuario " + idUsuario + ", nroNota " + nroNota);
			}

			return ResponseEntity.ok(notaDTOOpt.get());
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener la nota");
		}
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<?> getNota(@PathVariable("id") Integer id) {
		try {
			NotaDTO getCurso = notaService.getOne(id);
			if (getCurso == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró ningúna nota con ID: " + id);
			}
			return ResponseEntity.ok(getCurso);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener curso con ID: " + id);
		}
	}

}
