package com.clasify.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.clasify.dto.CursoDTO;
import com.clasify.dto.CursoEstudiantesDTO;
import com.clasify.dto.EstudiantesProfesorDTO;
import com.clasify.dto.UsuarioDTO;
import com.clasify.model.Curso;
import com.clasify.model.Grado;
import com.clasify.model.Notas;
import com.clasify.model.Usuario;
import com.clasify.repository.INotaRepository;

@Component
public class CursoMapper {
	private final INotaRepository notaRepository;

    public CursoMapper(INotaRepository notaRepository) {
        this.notaRepository = notaRepository;
    }
    
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
	
	public CursoEstudiantesDTO getDetalleDTO(Curso curso) {
	    CursoEstudiantesDTO dto = new CursoEstudiantesDTO();
	    dto.setIdCurso(curso.getIdCurso());
	    dto.setNombreCurso(curso.getNombreCurso());
	    dto.setFlgEliminado(curso.getFlgEliminado());
	    dto.setIdProfesor(UsuarioMapper.toDTO(curso.getIdProfesor()));
	    dto.setIdGrado(curso.getIdGrado());

	    if (curso.getEstudiantes() != null) {
	        List<EstudiantesProfesorDTO> estudiantesDTO = curso.getEstudiantes().stream()
	            .map(ec -> {
	                EstudiantesProfesorDTO estudianteDTO = UsuarioMapper.toEstudiantesProfesorDTO(ec.getEstudiante(), curso.getIdCurso());

	                // Traer las notas del estudiante en este curso
	                List<Notas> notas = notaRepository.findByCurso_IdCursoAndUsuario_Id(curso.getIdCurso(), ec.getEstudiante().getIdUsuario());

	                // Calcular promedio si hay notas
	                if (!notas.isEmpty()) {
	                    double promedio = notas.stream()
	                                           .mapToDouble(Notas::getCalificacion)
	                                           .average()
	                                           .orElse(0.0);
	                    estudianteDTO.setPromedio(promedio);
	                    estudianteDTO.setNotas(notas.stream().map(NotaMapper::toDTO).toList());
	                }

	                return estudianteDTO;
	            })
	            .collect(Collectors.toList());

	        dto.setEstudiantes(estudiantesDTO);
	    }

	    return dto;
	}

}
