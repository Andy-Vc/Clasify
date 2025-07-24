package com.clasify.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DashboardResponse {
    private Long totalProfesores;
    private Long totalEstudiantes;
    private Long totalCursos;
    private List<CursoEstudianteGrafic> estudiantesPorCurso;
    private List<UsuarioDTO> estudiantesRecientes;
}
