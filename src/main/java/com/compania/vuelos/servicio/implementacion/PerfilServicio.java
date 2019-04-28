package com.compania.vuelos.servicio.implementacion;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compania.vuelos.POJO.Perfil;
import com.compania.vuelos.repositorio.IPerfilRepositorio;
import com.compania.vuelos.repositorio.IUsuarioRepositorio;
import com.compania.vuelos.servicio.IServicioGenerico;

@Service
public class PerfilServicio implements IServicioGenerico<Perfil> {

	@Autowired
	protected IPerfilRepositorio perfilRepositorio;
	@Autowired
	protected IUsuarioRepositorio usuarioRepositorio;

	@Override
	public void crear(Perfil perfil) {
		perfilRepositorio.save(perfil);
	}
	@Override
	public List<Perfil> todos(){
		return perfilRepositorio.findAll();
	}
	@Override
	public Optional<Perfil> obtenerRegistro(Long id) {
		return perfilRepositorio.findById(id);
	}
	@Override
	public void actualizar(Perfil perfil) {
		perfilRepositorio.save(perfil);
	}
	@Override
	public void borrar(Long id) {
		perfilRepositorio.deleteById(id);
	}
}
