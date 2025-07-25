package com.clasify.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.clasify.dto.CursoDTO;
import com.clasify.dto.CursoEstudiantesDTO;
import com.clasify.dto.ResultadoResponse;
import com.clasify.mapper.CursoMapper;
import com.clasify.model.Curso;
import com.clasify.repository.ICursoRepository;

@Service
public class CursoService {

    private final ICursoRepository repository;
    private CursoMapper cursoMapper;

    public CursoService(ICursoRepository repository, CursoMapper cursoMapper) {
		this.repository = repository;
		this.cursoMapper = cursoMapper;
	}

	public long countCursos() {
        return repository.countCursos();
    }

    public List<CursoDTO> getAll() {
        List<Curso> cursos = repository.findAll();
        return cursos.stream()
                     .map(CursoMapper::getDTO)
                     .collect(Collectors.toList());
    }

    public List<CursoEstudiantesDTO> getAllByTeacher(Integer idProfesor) {
        List<Curso> cursos = repository.findByIdProfesor_IdUsuario(idProfesor);
        return cursos.stream()
                     .map(cursoMapper::getDetalleDTO)
                     .collect(Collectors.toList());
    }
    
    public CursoEstudiantesDTO getCurseStudents(String idCurso) {
        Curso curso = repository.findById(idCurso).orElseThrow();
        return cursoMapper.getDetalleDTO(curso);
    }

    
    public CursoDTO getOne(String id) {
        Curso curso = repository.findById(id).orElseThrow();
        return CursoMapper.getDTO(curso);
    }

    public ResultadoResponse create(CursoDTO cursoDTO) {
        try {
        	String idGenerado = generarIdUnicoParaCurso(cursoDTO);
            cursoDTO.setIdCurso(idGenerado);
            
            Curso curso = CursoMapper.convertDTOToEntity(cursoDTO);
            
            if (repository.findByIdCurso(curso.getIdCurso()).isPresent()) {
                return new ResultadoResponse(false,
                        "El ID generado para el curso ya existe, intente con otro nombre o profesor");
            }

            String mensaje = "Curso " + curso.getNombreCurso() + " registrado correctamente";
            repository.save(curso);
            return new ResultadoResponse(true, mensaje);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResultadoResponse(false, e.getMessage());
        }
    }

    public ResultadoResponse update(CursoDTO cursoDTO) {
        try {
            Curso curso = CursoMapper.convertDTOToEntity(cursoDTO);

            String mensaje = "Curso " + curso.getNombreCurso() + " actualizado correctamente";
            repository.save(curso);
            return new ResultadoResponse(true, mensaje);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResultadoResponse(false, e.getMessage());
        }
    }

    public ResultadoResponse delete(String id) {
        Curso curso = repository.findById(id).orElseThrow();
        String accion = curso.getFlgEliminado() ? "Activado" : "Eliminado";
        curso.setFlgEliminado(!curso.getFlgEliminado());

        try {
            Curso deleted = repository.save(curso);
            String mensaje = String.format("%s ha sido %s correctamente", deleted.getNombreCurso(), accion.toLowerCase());
            return new ResultadoResponse(true, mensaje);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResultadoResponse(false, e.getMessage());
        }
    }

    private String generarIdUnicoParaCurso(CursoDTO cursoDTO) throws Exception {
        String prefijo = cursoDTO.generarPrefijoId();

        List<Curso> cursosSimilares = repository.findByIdCursoStartingWith(prefijo);

        int maxSecuencia = 0;
        for (Curso c : cursosSimilares) {
            String idCurso = c.getIdCurso();
            if(idCurso.length() >= prefijo.length() + 2) {
                String secStr = idCurso.substring(prefijo.length(), prefijo.length() + 2);
                try {
                    int sec = Integer.parseInt(secStr);
                    if(sec > maxSecuencia) maxSecuencia = sec;
                } catch(NumberFormatException e) {
                    // Ignorar si no es número
                }
            }
        }

        int siguienteSecuencia = maxSecuencia + 1;
        if (siguienteSecuencia > 99) {
            throw new Exception("Se alcanzó el máximo de cursos con este prefijo.");
        }

        return prefijo + String.format("%02d", siguienteSecuencia);
    }

}
