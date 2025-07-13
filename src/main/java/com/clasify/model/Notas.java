package com.clasify.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name ="TB_NOTAS")
@Getter
@Setter
public class Notas {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_NOTA")
	private Integer idNota;
	
	@ManyToOne
	@JoinColumn(name="ID_CURSO", nullable = false)
	private Curso idCurso;
	
	@ManyToOne
	@JoinColumn(name="ID_USUARIO", nullable = false)
	private Usuario idEstudiante;
	
	@Column(name="CALIFICACION", nullable = false)
	private Double calificacion;
	
	@Column(name="COMENTARIO")
	private String comentario;
	
	@Column(name="FECHA_REGISTRO", columnDefinition = "DATETIME DEFAULT GETDATE()")
	private LocalDate fechaRegistro;
}
