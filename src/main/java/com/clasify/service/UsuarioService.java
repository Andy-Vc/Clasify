package com.clasify.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.clasify.dto.AutenticacionUsuario;
import com.clasify.dto.ResultadoResponse;
import com.clasify.model.Usuario;
import com.clasify.repository.IUsuarioRepository;

@Service
public class UsuarioService {

	private IUsuarioRepository repository;
	
	private UsuarioService(IUsuarioRepository repository) {
		this.repository = repository;
	}
	
	private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	public Usuario getOne(Integer id) {
		return repository.findById(id).orElseThrow();
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
	
	public ResultadoResponse deleteStudent(Integer id) {
		Usuario usuario = this.getOne(id);
		String accion = usuario.getFlgEliminado() ? "Eliminado" : "Activo";
		usuario.setFlgEliminado(!usuario.getFlgEliminado());
		
		try {
			Usuario deleted = repository.save(usuario);
			String mensaje = String.format("Usuario %s ha sido %s correctamente", deleted.getNombreCompleto(), accion.toLowerCase());
			return new ResultadoResponse(true, mensaje);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultadoResponse(false, e.getMessage());
		}	
	}
}
