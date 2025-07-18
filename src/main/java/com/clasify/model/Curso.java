package com.clasify.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name ="TB_CURSOS")
@Getter
@Setter
public class Curso {
	@Id
	@Column(name="ID_CURSO")
	private String idCurso;
	
	@Column(name="NOMBRE_CURSO", nullable = false, unique = true)
	private String nombreCurso;
	
	@ManyToOne
	@JoinColumn(name="ID_PROFESOR", nullable = false)
	private Usuario idProfesor;
	
	@ManyToOne
	@JoinColumn(name="ID_GRADO", nullable = false)
	private Grado idGrado;
	
	@Column(name="FLGELIMINADO", columnDefinition = "BIT DEFAULT 0")
	private Boolean flgEliminado = false; 
	
	@OneToMany(mappedBy = "curso", fetch = FetchType.LAZY)
	private List<EstudiantesCurso> estudiantes;
}
