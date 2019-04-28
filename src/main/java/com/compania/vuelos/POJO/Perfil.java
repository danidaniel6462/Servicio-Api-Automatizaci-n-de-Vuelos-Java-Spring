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
import javax.validation.constraints.NotBlank;
@Entity
public class Perfil {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, columnDefinition = "serial")
	private Long id;
	@NotBlank(message = "El nombre de perfil no puede ser nulo")
	@Column(name = "nombre", nullable = false)
	private String nombre;
	@Column(name = "descripcion")
	private String descripcion;
//	@ManyToMany(mappedBy = "perfil", fetch = FetchType.LAZY)
//	@JsonIdentityInfo(
//			  generator = ObjectIdGenerators.PropertyGenerator.class, 
//			  property = "id")
//	private Set<Usuario> usuario = new HashSet<>();
//	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	@JoinTable(name = "perfilAcceso",
//    joinColumns = @JoinColumn(name = "perfilId", referencedColumnName = "id"),
//    inverseJoinColumns = @JoinColumn(name = "accesoId", referencedColumnName = "id"))
//	@JsonIdentityInfo(
//			  generator = ObjectIdGenerators.PropertyGenerator.class, 
//			  property = "id")
//	private Set<Acceso> acceso = new HashSet<>();
	
	@OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
	private Set<PerfilUsuario> perfilUsuario = new HashSet<>();
	
	public Perfil() {
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
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Set<PerfilUsuario> getPerfilUsuario() {
		return perfilUsuario;
	}
	public void setPerfilUsuario(Set<PerfilUsuario> perfilUsuario) {
		this.perfilUsuario = perfilUsuario;
	}
	
}
