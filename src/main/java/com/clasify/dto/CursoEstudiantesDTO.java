package com.clasify.dto;

import java.util.List;

import com.clasify.model.Grado;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CursoEstudiantesDTO {
	private String idCurso;
    private String nombreCurso;
    private UsuarioDTO idProfesor;
    private Grado idGrado;
    private Boolean flgEliminado;

    private List<UsuarioDTO> estudiantes; 
}
