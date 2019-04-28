package com.compania.vuelos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.compania.vuelos.POJO.PerfilUsuario;

public interface IPerfilUsuarioRepositorio extends JpaRepository<PerfilUsuario, Long>{
	
}
