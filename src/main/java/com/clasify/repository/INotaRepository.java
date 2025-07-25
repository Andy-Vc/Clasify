package com.clasify.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.clasify.model.Notas;

public interface INotaRepository extends JpaRepository<Notas, Integer> {
	@Query("SELECT n FROM Notas n WHERE n.curso.idCurso = :idCurso AND n.usuario.id = :usuarioId AND n.nroNota = :nroNota")
	Optional<Notas> findByCursoUsuarioNroNota(@Param("idCurso") String idCurso, @Param("usuarioId") Integer usuarioId,
			@Param("nroNota") Integer nroNota);
	
	@Query("SELECT n FROM Notas n WHERE n.curso.idCurso = :idCurso AND n.usuario.id = :usuarioId")
	List<Notas> findByCurso_IdCursoAndUsuario_Id(@Param("idCurso") String idCurso, @Param("usuarioId") Integer usuarioId);
}
