package com.clasify.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.clasify.dto.CursoDTO;
import com.clasify.dto.CursoEstudiantesDTO;
import com.clasify.dto.UsuarioDTO;
import com.clasify.model.Curso;
import com.clasify.model.Grado;
import com.clasify.model.Usuario;

public class CursoMapper {
	public static CursoDTO getDTO(Curso curso) {
	    if (curso == null) {
	        return null;
	    }

	    Usuario profesor = curso.getIdProfesor();
	    UsuarioDTO profesorDTO = null;
	    if (profesor != null) {
	        profesorDTO = new UsuarioDTO(
	            profesor.getIdUsuario(),
	            profesor.getNombreUsuario(),
	            profesor.getApellidoUsuario(),
	            profesor.getEmail(),
	            profesor.getRol()
	        );
	    }

	    Grado grado = curso.getIdGrado();
	    
	    return new CursoDTO(
	        curso.getIdCurso(),
	        curso.getNombreCurso(),
	        profesorDTO,
	        grado,
	        curso.getFlgEliminado()
	    );
	}
	
	public static Curso convertDTOToEntity(CursoDTO dto) {
	    if (dto == null) {
	        return null;
	    }

	    Curso curso = new Curso();
	    curso.setIdCurso(dto.getIdCurso());
	    curso.setNombreCurso(dto.getNombreCurso());

	    if (dto.getIdProfesor() != null) {
	        Usuario profesor = new Usuario();
	        profesor.setIdUsuario(dto.getIdProfesor().getIdUsuario());
	        curso.setIdProfesor(profesor);
	    }

	    if (dto.getIdGrado() != null) {
	        Grado grado = new Grado();
	        grado.setIdGrado(dto.getIdGrado().getIdGrado());
	        curso.setIdGrado(grado);
	    }

	    return curso;
	}
	
	public static CursoEstudiantesDTO getDetalleDTO(Curso curso) {
		CursoEstudiantesDTO dto = new CursoEstudiantesDTO();
        dto.setIdCurso(curso.getIdCurso());
        dto.setNombreCurso(curso.getNombreCurso());
        dto.setFlgEliminado(curso.getFlgEliminado());

        dto.setIdProfesor(UsuarioMapper.toDTO(curso.getIdProfesor()));
        dto.setIdGrado(curso.getIdGrado());

        if (curso.getEstudiantes() != null) {
            List<UsuarioDTO> estudiantesDTO = curso.getEstudiantes()
                .stream()
                .map(ec -> UsuarioMapper.toDTO(ec.getEstudiante()))
                .collect(Collectors.toList());

            dto.setEstudiantes(estudiantesDTO);
        }

        return dto;
    }

}
