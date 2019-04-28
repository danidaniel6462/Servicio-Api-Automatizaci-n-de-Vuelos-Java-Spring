package com.compania.vuelos.controlador;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.compania.vuelos.POJO.Perfil;
import com.compania.vuelos.servicio.implementacion.PerfilServicio;
import com.compania.vuelos.utils.RestRespuestaBody;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class PerfilController {

	@Autowired
	protected PerfilServicio servicio;

	protected ObjectMapper mapeador = new ObjectMapper();

	@GetMapping("/perfil")
	public ResponseEntity<List<Perfil>> obtenerTodos() {
		List<Perfil> perfils = this.servicio.todos();
		if (perfils.isEmpty()) {
			return new ResponseEntity<List<Perfil>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Perfil>>(perfils, HttpStatus.OK);
	}

	@GetMapping("/perfil/{id}")
	public ResponseEntity<Object> obtenerRegistro(@PathVariable("id") Long id) {
		Optional<Perfil> perfil = this.servicio.obtenerRegistro(id);
		if (!perfil.isPresent()) {
			return ((BodyBuilder) ResponseEntity.notFound())
					.body(new RestRespuestaBody(HttpStatus.NOT_FOUND.value(), "Registro no encontrado"));
		}
		return ResponseEntity.ok(perfil);
	}

	@PostMapping("/perfil")
	public ResponseEntity<Object> crear(@RequestBody Perfil perfil){
		if (perfil.getId() != null) {
			return ResponseEntity.badRequest().body(new RestRespuestaBody(HttpStatus.BAD_REQUEST.value(),
					"No se debe enviar el ID en la creación de registros, ya que se generán automáticamente."));
		}
		this.servicio.crear(perfil);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(perfil.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@PutMapping("/perfil/{id}")
	public ResponseEntity<Object> actualizarRegistro(@RequestBody String entidadJSON, @PathVariable Long id)
			throws JsonParseException, JsonMappingException, IOException {
		Perfil perfil = this.mapeador.readValue(entidadJSON, Perfil.class);
		if (id != perfil.getId()) {
			return ResponseEntity.badRequest().body(new RestRespuestaBody(HttpStatus.BAD_REQUEST.value(),
					"Error, Verifique que el Id del objeto coincida con el id enviado en la ruta /{id}."));
		}
		Optional<Perfil> perfilEncontrado = servicio.obtenerRegistro(id);
		if (!perfilEncontrado.isPresent()) {
			return ((BodyBuilder) ResponseEntity.notFound()).body(
					new RestRespuestaBody(HttpStatus.NOT_FOUND.value(), "Registro no encontrado para actualizar"));
		}
		this.servicio.actualizar(perfil);
		return ResponseEntity.ok()
				.body(new RestRespuestaBody(HttpStatus.OK.value(), "Registro actualizado exitósamente"));
	}

	@DeleteMapping("/perfil/{id}")
	public ResponseEntity<Object> borrarRegistro(@PathVariable("id") Long id) {
		Optional<Perfil> perfil = servicio.obtenerRegistro(id);
		if (!perfil.isPresent()) {
			return ((BodyBuilder) ResponseEntity.notFound())
					.body(new RestRespuestaBody(HttpStatus.NOT_FOUND.value(), "Registro no encontrado para eliminar"));
		}
		this.servicio.borrar(id);

		return ResponseEntity.ok()
				.body(new RestRespuestaBody(HttpStatus.OK.value(), "Registro eliminado exitósamente"));
	}
}
