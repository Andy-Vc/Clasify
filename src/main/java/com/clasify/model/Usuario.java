package com.clasify.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name ="TB_USUARIO")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_USUARIO")
	private Integer idUsuario;
	
	@Column(name="NOMBRE_USUARIO", nullable = false)
	private String nombreUsuario;
	
	@Column(name="APELLIDO_USUARIO", nullable = false)
	private String apellidoUsuario;
	
	@Column(name = "EMAIL", nullable = false, unique = true)
    private String email;
	
	@Column(name="CONTRASENIA", nullable = false)
	private String contrasenia;
	
	@Column(name="ROL", nullable = false)
	private String rol;
	
	@Column(name="FLGELIMINADO", columnDefinition = "BIT DEFAULT 0")
	private Boolean flgEliminado = false; 
	
	@OneToMany(mappedBy = "estudiante")
	@JsonIgnore
	private List<EstudiantesCurso> cursosInscritos;

	public String getNombreCompleto() {
		return nombreUsuario + " " +apellidoUsuario;
	}
	
	public String getRolDescripcion() {
	    switch (this.rol) {
	        case "A": return "Administrador";
	        case "P": return "Profesor";
	        case "E": return "Estudiante";
	        default: return "Rol desconocido";
	    }
	}
}
