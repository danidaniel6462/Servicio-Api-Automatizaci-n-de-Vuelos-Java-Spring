package com.compania.vuelos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.compania.vuelos.POJO.Perfil;

public interface IPerfilRepositorio extends JpaRepository<Perfil, Long>{
	
}
