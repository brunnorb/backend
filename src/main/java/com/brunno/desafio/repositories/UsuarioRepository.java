package com.brunno.desafio.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brunno.desafio.domain.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	Optional<Usuario> findByEmail(String email);
	
}
