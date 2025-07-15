package com.clasify.mapper;

import com.clasify.dto.UsuarioDTO;
import com.clasify.model.Usuario;

public class UsuarioMapper {
	public static UsuarioDTO toDTO(Usuario usuario) {
        if (usuario == null) return null;

        return new UsuarioDTO(
            usuario.getIdUsuario(),
            usuario.getNombreUsuario(),
            usuario.getApellidoUsuario(),
            usuario.getEmail(),
            usuario.getRol()
        );
    }
}
