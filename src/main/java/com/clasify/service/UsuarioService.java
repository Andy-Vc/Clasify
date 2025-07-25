package com.clasify.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.clasify.dto.AutenticacionUsuario;
import com.clasify.dto.EstudianteDTO;
import com.clasify.dto.ResultadoResponse;
import com.clasify.dto.UsuarioDTO;
import com.clasify.mapper.UsuarioMapper;
import com.clasify.model.Usuario;
import com.clasify.repository.IUsuarioRepository;

@Service
public class UsuarioService {
	private IUsuarioRepository repository;
	private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	private UsuarioMapper usuarioMapper;
	
	public UsuarioService(IUsuarioRepository repository, UsuarioMapper usuarioMapper) {
	    this.repository = repository;
	    this.usuarioMapper = usuarioMapper;
	}
	
	public long countStudents() {
		return repository.countStudents();
	}
	
	public long countTeachers() {
		return repository.countTeachers();
	}
	
	public Usuario getOne(Integer id) {
		return repository.findById(id).orElseThrow();
	}
	
	public UsuarioDTO getUsuarioDTOByEmail(String email) {
	    Usuario usuario = repository.findByEmail(email);
	    return UsuarioMapper.toDTO(usuario);
	}
	
	public List<UsuarioDTO> getNewlyRegistered(){
		List<Usuario> estudiantes = repository.findTop5UsuariosRolE(PageRequest.of(0,5));
		return UsuarioMapper.convertirUserDTOs(estudiantes);
	}
	
	public List<Usuario> getTeachers(){
		return repository.findTeachers();
	}
	
	public List<EstudianteDTO> getStudentsDTO() {
	    List<Usuario> estudiantes = repository.findStudents();
	    return usuarioMapper.convertirAEstudianteDTOs(estudiantes);
	}
	
	public Usuario autenticar(AutenticacionUsuario filtro) {
		Usuario usuario = repository.findByEmail(filtro.getEmail());
        if (usuario == null) {
            throw new RuntimeException("Credenciales inválidas");
        }
        
        if (!passwordEncoder.matches(filtro.getContrasenia(), usuario.getContrasenia())) {
            throw new RuntimeException("Credenciales inválidas");
        }      
        return usuario;
	}
	
	public ResultadoResponse createStudent(Usuario usuario) {
		try {
			String mensaje = "Estudiante " + usuario.getNombreUsuario() + " registrado correctamente";
			String passwordHashed = passwordEncoder.encode(usuario.getContrasenia());
			usuario.setRol("E");
	        usuario.setContrasenia(passwordHashed);
			repository.save(usuario);
			return new ResultadoResponse(true, mensaje);
			
		}
		catch (Exception e) {
			e.printStackTrace();
			return new ResultadoResponse(false, e.getMessage());
		}
	}
	
	public ResultadoResponse createTeacher(Usuario usuario) {
		try {
			if (repository.findByEmail(usuario.getEmail()) != null) {
	            return new ResultadoResponse(false, "El correo electrónico "+ usuario.getEmail()+ " ya está registrado.");
	        }
			String mensaje = "Profesor " + usuario.getNombreUsuario() + " registrado correctamente";
			String passwordHashed = passwordEncoder.encode(usuario.getContrasenia());
			usuario.setRol("P");
	        usuario.setContrasenia(passwordHashed);
			repository.save(usuario);
			return new ResultadoResponse(true, mensaje);
			
		}
		catch (Exception e) {
			e.printStackTrace();
			return new ResultadoResponse(false, e.getMessage());
		}
	}
	
	public ResultadoResponse update(Usuario usuario) {
		String userType;

	    if ("E".equals(usuario.getRol())) {
	        userType = "Estudiante ";
	    } else {
	        userType = "Profesor ";
	    }

		try {
			String mensaje = userType + usuario.getNombreUsuario() + " actualizado correctamente";
			String passwordHashed = passwordEncoder.encode(usuario.getContrasenia());
	        usuario.setContrasenia(passwordHashed);
			repository.save(usuario);
			return new ResultadoResponse(true, mensaje);
			
		}
		catch (Exception e) {
			e.printStackTrace();
			return new ResultadoResponse(false, e.getMessage());
		}
	}
	
	public ResultadoResponse delete(Integer id) {
		Usuario usuario = this.getOne(id);
		String userType;
		String accion = usuario.getFlgEliminado() ? "Activado" : "Eliminado";
		usuario.setFlgEliminado(!usuario.getFlgEliminado());
		
	    if ("E".equals(usuario.getRol())) {
	        userType = "Estudiante";
	    } else {
	        userType = "Profesor";
	    }
		
		try {
			Usuario deleted = repository.save(usuario);
			String mensaje = String.format("%s %s ha sido %s correctamente", userType, deleted.getNombreCompleto(), accion.toLowerCase());
			return new ResultadoResponse(true, mensaje);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultadoResponse(false, e.getMessage());
		}	
	}
}
