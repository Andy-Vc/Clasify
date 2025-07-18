package com.clasify.dto;

import com.clasify.model.Grado;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CursoDTO {
	private String idCurso;
    private String nombreCurso;
    private UsuarioDTO idProfesor;
    private Grado idGrado;
    private Boolean flgEliminado = false; 
    
    public String generarPrefijoId() {
        String nombreCurso = this.getNombreCurso().replaceAll("\\s+", "").toUpperCase();
        String prefijoCurso = nombreCurso.length() >= 4 ? nombreCurso.substring(0,4) : nombreCurso;

        String grado = this.getIdGrado() != null ? this.getIdGrado().getIdGrado().toString() : "0";

        UsuarioDTO profesor = this.getIdProfesor();
        String inicialesProfesor = "";
        if(profesor != null && profesor.getNombreUsuario() != null && profesor.getApellidoUsuario() != null) {
            inicialesProfesor = profesor.getNombreUsuario().substring(0,1).toUpperCase()
                                  + profesor.getApellidoUsuario().substring(0,1).toUpperCase();
        }

        return prefijoCurso + grado + inicialesProfesor;
    }
}


