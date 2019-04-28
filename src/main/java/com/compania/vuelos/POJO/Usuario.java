package com.compania.vuelos.POJO;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, columnDefinition = "serial")
	private Long id;
	@Column
	private String username;
	@Column
	private String password;
	@Column
	private String nombres;
	@Column
	private String apellidos;
	@Column
	private String correo;
	
	@OneToMany(mappedBy = "perfil", fetch = FetchType.LAZY)
	private Set<PerfilUsuario> perfilUsuario = new HashSet<>();
	
	@OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
	private Set<Boleto> boleto = new HashSet<>();
	
	public Usuario() { }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public Set<PerfilUsuario> getPerfilUsuario() {
		return perfilUsuario;
	}

	public void setPerfilUsuario(Set<PerfilUsuario> perfilUsuario) {
		this.perfilUsuario = perfilUsuario;
	}
	
	public Set<Boleto> getBoleto() {
		return boleto;
	}

	public void setBoleto(Set<Boleto> boleto) {
		this.boleto = boleto;
	}

	public String getNombreApellidoCompletos() {
		return nombres + " " + apellidos;
	}
	public String getApellidoNombreCompletos() {
		return apellidos + " " + nombres;
	}
}
