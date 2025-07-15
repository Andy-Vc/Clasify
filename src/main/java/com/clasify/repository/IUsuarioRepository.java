package com.clasify.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.clasify.model.Usuario;

public interface IUsuarioRepository extends JpaRepository<Usuario, Integer>{
	Usuario findByEmail(String email);
	
	@Query("SELECT u FROM Usuario u WHERE u.rol = 'P'")
    List<Usuario> findTeachers();

    @Query("SELECT u FROM Usuario u WHERE u.rol = 'E'")
    List<Usuario> findStudents();
	
	@Query("SELECT COUNT(u) FROM Usuario u WHERE u.rol = 'P'")
    Long countTeachers();

    @Query("SELECT COUNT(u) FROM Usuario u WHERE u.rol = 'E'")
    Long countStudents();
}
