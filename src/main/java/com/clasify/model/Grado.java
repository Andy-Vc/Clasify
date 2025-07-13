package com.clasify.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name ="TB_GRADOS")
@Getter
@Setter
public class Grado {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_GRADO")
	private Integer idGrado;
	
	@Column(name="NOMBRE_GRADO", nullable = false, unique = true)
	private String nombreGrado;
}
