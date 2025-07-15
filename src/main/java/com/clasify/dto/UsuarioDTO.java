package com.clasify.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UsuarioDTO {
	private Integer idUsuario;
	private String nombreUsuario;
	private String apellidoUsuario;
	private String email;
	private String rol;

}
