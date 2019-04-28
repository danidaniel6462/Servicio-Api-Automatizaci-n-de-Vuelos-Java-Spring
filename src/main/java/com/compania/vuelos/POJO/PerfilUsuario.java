package com.compania.vuelos.POJO;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class PerfilUsuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, columnDefinition = "serial")
	private Long id;
	@Column(name = "estado")
	private String estado;
	@Column(name = "ultimoAcceso")
	private LocalDateTime ultimoAcceso;
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "perfilId", nullable = false)
	private Perfil perfil;
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "usuarioId", nullable = false)
	private Usuario usuario;
	public PerfilUsuario() {
	}
	public PerfilUsuario(Long id, String estado, LocalDateTime ultimoAcceso, Perfil perfil, Usuario usuario) {
		super();
		this.id = id;
		this.estado = estado;
		this.ultimoAcceso = ultimoAcceso;
		this.perfil = perfil;
		this.usuario = usuario;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public LocalDateTime getUltimoAcceso() {
		return ultimoAcceso;
	}
	public void setUltimoAcceso(LocalDateTime ultimoAcceso) {
		this.ultimoAcceso = ultimoAcceso;
	}
	@JsonIgnore
	public Perfil getPerfil() {
		return perfil;
	}
	public Long getPerfilId() {
		return perfil.getId();
	}
	@JsonIgnore
	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
	@JsonIgnore
	public Usuario getUsuario() {
		return usuario;
	}
	public Long getUsuarioId() {
		return usuario.getId();
	}
	@JsonIgnore
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	
}
