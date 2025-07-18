package com.clasify.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsuarioDTO {
	private Integer idUsuario;
	private String nombreUsuario;
	private String apellidoUsuario;
	private String email;
	private String rol;

}
