package com.compania.vuelos.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

@Component
public interface IServicioGenerico<T> {

	public List<T> todos();	
	
	public void crear(T entidad); 
	
	public Optional<T> obtenerRegistro(Long id);

	public void actualizar(T usuario);
	
	public void borrar(Long id);
}
