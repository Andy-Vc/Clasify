package com.clasify.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.clasify.dto.EstudianteCursoDTO;
import com.clasify.dto.EstudianteDTO;
import com.clasify.dto.UsuarioDTO;
import com.clasify.model.EstudiantesCurso;
import com.clasify.model.Usuario;

@Component
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
	
	public EstudianteDTO convertirAUsuarioDTO(Usuario usuario) {
		EstudianteDTO dto = new EstudianteDTO();
	    dto.setIdUsuario(usuario.getIdUsuario());
	    dto.setNombreUsuario(usuario.getNombreUsuario());
	    dto.setApellidoUsuario(usuario.getApellidoUsuario());
	    dto.setEmail(usuario.getEmail());
	    dto.setNombreCompleto(usuario.getNombreCompleto());
	    dto.setRol(usuario.getRol());
	    dto.setRolDescripcion(usuario.getRolDescripcion());
	    dto.setFlgEliminado(usuario.getFlgEliminado());

	    if ("E".equals(usuario.getRol())) {
	        List<EstudianteCursoDTO> cursosDTO = usuario.getCursosInscritos().stream()
	            .map(this::convertirEstudiantesCursoDTO)  
	            .toList();
	        dto.setCursosInscritos(cursosDTO);
	    }
	    return dto;
	}
	
	public EstudianteCursoDTO convertirEstudiantesCursoDTO(EstudiantesCurso ec) {
		EstudianteCursoDTO dto = new EstudianteCursoDTO();
	    dto.setCurso(CursoMapper.getDTO(ec.getCurso()));
	    return dto;
	}
	
	public static List<UsuarioDTO> convertirUserDTOs(List<Usuario> usuarios) {
		return usuarios.stream().map(UsuarioMapper::toDTO).toList();
		}
	
	public List<EstudianteDTO> convertirAEstudianteDTOs(List<Usuario> usuarios) {
        return usuarios.stream()
            .map(this::convertirAUsuarioDTO)
            .toList();
    }
}
