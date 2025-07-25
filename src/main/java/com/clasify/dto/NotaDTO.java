package com.clasify.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotaDTO {
	private Integer idNota;
	private String idCurso;
    private int idUsuario;
    private int nroNota;
    private Double calificacion;
    private String comentario;
    private LocalDate fechaRegistro;
}
