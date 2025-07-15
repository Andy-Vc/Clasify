package com.clasify.dto;

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
}
