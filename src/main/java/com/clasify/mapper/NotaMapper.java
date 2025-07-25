package com.clasify.mapper;

import org.springframework.stereotype.Component;

import com.clasify.dto.NotaDTO;
import com.clasify.model.Curso;
import com.clasify.model.Notas;
import com.clasify.model.Usuario;
import com.clasify.repository.ICursoRepository;
import com.clasify.repository.IUsuarioRepository;

@Component
public class NotaMapper {
    private ICursoRepository cursoRepository;

    private IUsuarioRepository usuarioRepository;
    
    public NotaMapper(ICursoRepository cursoRepository, IUsuarioRepository usuarioRepository) {
		this.cursoRepository = cursoRepository;
		this.usuarioRepository = usuarioRepository;
	}

    public static NotaDTO toDTO(Notas nota) {
        NotaDTO dto = new NotaDTO();
        dto.setIdNota(nota.getIdNota());
        dto.setNroNota(nota.getNroNota());
        dto.setCalificacion(nota.getCalificacion());
        dto.setComentario(nota.getComentario());

        if (nota.getCurso() != null) {
            dto.setIdCurso(nota.getCurso().getIdCurso());
        }

        if (nota.getUsuario() != null) {
            dto.setIdUsuario(nota.getUsuario().getIdUsuario());
        }
        dto.setFechaRegistro(nota.getFechaRegistro());
        return dto;
    }

    
	public Notas toEntity(NotaDTO dto) {
        Notas nota = new Notas();
        nota.setIdNota(dto.getIdNota());
        nota.setNroNota(dto.getNroNota());
        nota.setCalificacion(dto.getCalificacion());
        nota.setComentario(dto.getComentario());

        Curso curso = cursoRepository.findById(dto.getIdCurso())
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        nota.setCurso(curso);
        nota.setUsuario(usuario);

        return nota;
    }
}

