package com.compania.vuelos.repositorio;

import com.compania.vuelos.POJO.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsuarioRepositorio extends JpaRepository<Usuario, Long> {

	public Usuario findByUsername(String username);

}
