package com.compania.vuelos.servicio.implementacion;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compania.vuelos.POJO.PerfilUsuario;
import com.compania.vuelos.repositorio.IPerfilUsuarioRepositorio;
import com.compania.vuelos.servicio.IServicioGenerico;

@Service
public class PerfilUsuarioServicio implements IServicioGenerico<PerfilUsuario> {

	@Autowired
	protected IPerfilUsuarioRepositorio perfilUsuarioRepositorio;

	@Override
	public void crear(PerfilUsuario perfilUsuario) {
		perfilUsuarioRepositorio.save(perfilUsuario);
	}
	@Override
	public List<PerfilUsuario> todos(){
		return perfilUsuarioRepositorio.findAll();
	}
	@Override
	public Optional<PerfilUsuario> obtenerRegistro(Long id) {
		return perfilUsuarioRepositorio.findById(id);
	}
	@Override
	public void actualizar(PerfilUsuario perfilUsuario) {
		perfilUsuarioRepositorio.save(perfilUsuario);
	}
	@Override
	public void borrar(Long id) {
		perfilUsuarioRepositorio.deleteById(id);
	}
}
