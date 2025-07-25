package com.clasify.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EstudiantesProfesorDTO {
	private Integer idUsuario;
    private String nombre;
    private String apellido;
    private String email;
    private List<NotaDTO> notas;
    private Double promedio;

}
