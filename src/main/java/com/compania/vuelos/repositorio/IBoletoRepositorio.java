package com.compania.vuelos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.compania.vuelos.POJO.Boleto;

public interface IBoletoRepositorio extends JpaRepository<Boleto, Long>{
	
}
