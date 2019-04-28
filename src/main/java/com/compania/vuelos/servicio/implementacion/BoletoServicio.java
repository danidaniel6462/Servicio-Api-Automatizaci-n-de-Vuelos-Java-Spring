package com.compania.vuelos.servicio.implementacion;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compania.vuelos.POJO.Boleto;
import com.compania.vuelos.repositorio.IBoletoRepositorio;
import com.compania.vuelos.servicio.IServicioGenerico;

@Service
public class BoletoServicio implements IServicioGenerico<Boleto> {

	@Autowired
	protected IBoletoRepositorio boletoRepositorio;

	@Override
	public void crear(Boleto boleto) {
		boletoRepositorio.save(boleto);
	}

	@Override
	public List<Boleto> todos() {
		return boletoRepositorio.findAll().stream()
				.sorted(Comparator.comparing(Boleto::getFechaSolicitud).thenComparing(Boleto::getHoraSolicitud))
				.collect(Collectors.toList());
	}

	@Override
	public Optional<Boleto> obtenerRegistro(Long id) {
		return boletoRepositorio.findById(id);
	}

	@Override
	public void actualizar(Boleto boleto) {
		boletoRepositorio.save(boleto);
	}

	@Override
	public void borrar(Long id) {
		boletoRepositorio.deleteById(id);
	}

	public List<Boleto> actualizarBoletoObtenerLista(Boleto boleto) {
		boletoRepositorio.save(boleto);
		return boletoRepositorio.findAll();
	}

}
