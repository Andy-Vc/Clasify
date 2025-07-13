package com.clasify.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clasify.model.Usuario;

public interface IUsuarioRepository extends JpaRepository<Usuario, Integer>{
	Usuario findByEmail(String email);
}
