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

import com.compania.vuelos.POJO.PerfilUsuario;
import com.compania.vuelos.servicio.implementacion.PerfilUsuarioServicio;
import com.compania.vuelos.utils.RestRespuestaBody;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin
@RestController
@RequestMapping("/api/perfilUsuario")
public class PerfilUsuarioController {

	@Autowired
	protected PerfilUsuarioServicio servicio;

	protected ObjectMapper mapeador = new ObjectMapper();
	
	@GetMapping
	public ResponseEntity<List<PerfilUsuario>> obtenerTodos() {
		List<PerfilUsuario> perfilUsuarios = this.servicio.todos();
		if (perfilUsuarios.isEmpty()) {
			return new ResponseEntity<List<PerfilUsuario>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<PerfilUsuario>>(perfilUsuarios, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> obtenerRegistro(@PathVariable("id") Long id) {
		PerfilUsuario perfilUsuario = this.servicio.obtenerRegistro(id).get();
		if (perfilUsuario == null ) {
			return ((BodyBuilder) ResponseEntity.notFound())
					.body(new RestRespuestaBody(HttpStatus.NOT_FOUND.value(), "Registro no encontrado"));
		}
		return ResponseEntity.ok(perfilUsuario);
	}
	
	@PostMapping
	public ResponseEntity<Object> crear(@RequestBody PerfilUsuario perfilUsuario){
		this.servicio.crear(perfilUsuario);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(perfilUsuario.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> actualizarRegistro(@RequestBody String entidadJSON, @PathVariable Long id)
			throws JsonParseException, JsonMappingException, IOException {
		PerfilUsuario perfilUsuario = this.mapeador.readValue(entidadJSON, PerfilUsuario.class);
		if (id != perfilUsuario.getId()) {
			return ResponseEntity.badRequest().body(new RestRespuestaBody(HttpStatus.BAD_REQUEST.value(),
					"Error, Verifique que el Id del objeto coincida con el id enviado en la ruta /{id}."));
		}
		Optional<PerfilUsuario> perfilUsuarioEncontrado = servicio.obtenerRegistro(id);
		if (!perfilUsuarioEncontrado.isPresent()) {
			return ((BodyBuilder) ResponseEntity.notFound())
					.body(new RestRespuestaBody(HttpStatus.NOT_FOUND.value(), "Registro no encontrado para actualizar"));
		}
		this.servicio.actualizar(perfilUsuario);
		return ResponseEntity.ok().body(new RestRespuestaBody(HttpStatus.OK.value(), "Registro actualizado exitósamente"));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> borrarRegistro(@PathVariable("id") Long id) {
		Optional<PerfilUsuario> perfilUsuario = servicio.obtenerRegistro(id);
		if (!perfilUsuario.isPresent()) {
			return ((BodyBuilder) ResponseEntity.notFound())
					.body(new RestRespuestaBody(HttpStatus.NOT_FOUND.value(), "Registro no encontrado para eliminar"));
		}
		this.servicio.borrar(id);

		return ResponseEntity.ok().body(new RestRespuestaBody(HttpStatus.OK.value(), "Registro eliminado exitósamente"));
	}
}
