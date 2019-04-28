package com.compania.vuelos.POJO;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 
 * @author Daniel
 */
@Entity
public class Boleto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, columnDefinition = "serial")
	private Long id;
	@Column(name = "nombre", nullable = false)
	private String nombre;
	@Column(name = "fechaViaje", nullable = false)
	private LocalDate fechaViaje;
	@Column(name = "destino", nullable = false)
	private String destino;
	@Column(name = "fechaSolicitud", nullable = false)
	private LocalDate fechaSolicitud;
	@Column(name = "horaSolicitud", nullable = false)
	private LocalTime horaSolicitud;
	@Column(name = "fechaReserva", nullable = true)
	private LocalDate fechaReserva;
	@Column(name = "horaReservado", nullable = true)
	private LocalTime horaReservado;
	@Column(name = "estado")
	private String estado;

	public Boleto() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public LocalDate getFechaViaje() {
		return fechaViaje;
	}

	public void setFechaViaje(LocalDate fechaViaje) {
		this.fechaViaje = fechaViaje;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public LocalDate getFechaSolicitud() {
		return fechaSolicitud;
	}

	public void setFechaSolicitud(LocalDate fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	public LocalTime getHoraSolicitud() {
		return horaSolicitud;
	}

	public void setHoraSolicitud(LocalTime horaSolicitud) {
		this.horaSolicitud = horaSolicitud;
	}

	public LocalDate getFechaReserva() {
		return fechaReserva;
	}

	public void setFechaReserva(LocalDate fechaReserva) {
		this.fechaReserva = fechaReserva;
	}

	public LocalTime getHoraReservado() {
		return horaReservado;
	}

	public void setHoraReservado(LocalTime horaReservado) {
		this.horaReservado = horaReservado;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
}
