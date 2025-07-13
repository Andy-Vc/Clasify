package com.clasify.dto;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EstudianteCursoId implements Serializable {
    @Column(name = "ID_CURSO", length = 7)
    private String idCurso;

    @Column(name = "ID_USUARIO")
    private Integer idUsuario;
}

