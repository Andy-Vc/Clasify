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
public class EstudianteDTO {
	private Integer idUsuario;
    private String nombreUsuario;
    private String apellidoUsuario;
    private String email;
    private String nombreCompleto;
    private String rol;
    private String rolDescripcion;
    private Boolean flgEliminado;
    private List<EstudianteCursoDTO> cursosInscritos; 
}
