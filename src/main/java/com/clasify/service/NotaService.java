package com.clasify.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.clasify.dto.NotaDTO;
import com.clasify.dto.ResultadoResponse;
import com.clasify.mapper.NotaMapper;
import com.clasify.model.Notas;
import com.clasify.repository.INotaRepository;

@Service
public class NotaService {
	private INotaRepository repository;
	private NotaMapper mapper;
	
	public NotaService(INotaRepository repository, NotaMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}

	public NotaDTO getOne(Integer id) {
        Notas nota = repository.findById(id).orElseThrow();
        return mapper.toDTO(nota);
    }
	
	public Optional<NotaDTO> findNotaDTOByCursoUsuarioNroNota(String idCurso, Integer usuarioId, Integer nroNota) {
	    return repository.findByCursoUsuarioNroNota(idCurso, usuarioId, nroNota)
	                     .map(nota -> mapper.toDTO(nota));
	}
	
	public ResultadoResponse saveOrUpdateNota(NotaDTO dto) {
	    try {
	        // Buscar nota existente por curso, usuario y nroNota
	        Optional<Notas> notaExistenteOpt = repository.findByCursoUsuarioNroNota(
	            dto.getIdCurso(), dto.getIdUsuario(), dto.getNroNota());

	        Notas nota;
	        if (notaExistenteOpt.isPresent()) {
	            nota = notaExistenteOpt.get();
	            // Actualizar campos
	            nota.setCalificacion(dto.getCalificacion());
	            nota.setComentario(dto.getComentario());
	            // ... cualquier otro campo a actualizar
	        } else {
	            // Crear nueva entidad desde DTO
	            nota = mapper.toEntity(dto);
	        }

	        repository.save(nota);

	        String mensaje = "Nota número " + nota.getNroNota() + " ha sido registrada correctamente para el usuario "
	                + nota.getUsuario().getNombreCompleto();

	        return new ResultadoResponse(true, mensaje);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return new ResultadoResponse(false, e.getMessage());
	    }
	}
}
