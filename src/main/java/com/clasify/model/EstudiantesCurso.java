package com.clasify.model;

import com.clasify.dto.EstudianteCursoId;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TB_ESTUDIANTES_CURSOS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EstudiantesCurso {

    @EmbeddedId
    private EstudianteCursoId id;

    @ManyToOne
    @MapsId("idUsuario")
    @JoinColumn(name = "ID_USUARIO", nullable = false)
    private Usuario estudiante;

    @ManyToOne
    @MapsId("idCurso")
    @JoinColumn(name = "ID_CURSO", nullable = false)
    private Curso curso;
}

