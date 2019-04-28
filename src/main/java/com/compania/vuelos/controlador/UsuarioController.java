package com.compania.vuelos.controlador;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

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

import com.compania.vuelos.POJO.Usuario;
import com.compania.vuelos.servicio.implementacion.UsuarioServicio;
import com.compania.vuelos.utils.RestRespuestaBody;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin
@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

	@Autowired
	protected UsuarioServicio servicio;

	protected ObjectMapper mapeador = new ObjectMapper();
	
	@GetMapping
	public ResponseEntity<?> obtenerTodos() {
		List<Usuario> usuarios = this.servicio.todos();
		if (usuarios.isEmpty()) {
			return new ResponseEntity<>("No hay elementos", HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(usuarios, HttpStatus.OK);
	}
	
	@GetMapping("/obtenerPorUsername")
	public ResponseEntity<?> obtenerPorUsername(@PathParam ("username") String username) {
		Usuario usuario = this.servicio.obtenerPorUsername(username);
		if(usuario == null) {
			return ((BodyBuilder) ResponseEntity.notFound())
					.body(new RestRespuestaBody(HttpStatus.NOT_FOUND.value(), "Registro no encontrado"));
		}
		return new ResponseEntity<>(usuario, HttpStatus.OK);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> obtenerRegistro(@PathVariable("id") Long id) {
		Usuario usuario = this.servicio.obtenerRegistro(id).get();
		if (usuario == null ) {
			return ((BodyBuilder) ResponseEntity.notFound())
					.body(new RestRespuestaBody(HttpStatus.NOT_FOUND.value(), "Registro no encontrado"));
		}
		return ResponseEntity.ok(usuario);
	}
	
	@PostMapping("/signIn")
	public ResponseEntity<Object> crear(@RequestBody Usuario usuario){
		this.servicio.crear(usuario);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuario.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> actualizarRegistro(@RequestBody String entidadJSON, @PathVariable Long id)
			throws JsonParseException, JsonMappingException, IOException {
		Usuario usuario = this.mapeador.readValue(entidadJSON, Usuario.class);
		if (id != usuario.getId()) {
			return ResponseEntity.badRequest().body(new RestRespuestaBody(HttpStatus.BAD_REQUEST.value(),
					"Error, Verifique que el Id del objeto coincida con el id enviado en la ruta /{id}."));
		}
		Optional<Usuario> usuarioEncontrado = servicio.obtenerRegistro(id);
		if (!usuarioEncontrado.isPresent()) {
			return ((BodyBuilder) ResponseEntity.notFound())
					.body(new RestRespuestaBody(HttpStatus.NOT_FOUND.value(), "Registro no encontrado para actualizar"));
		}
		this.servicio.actualizar(usuario);
		return ResponseEntity.ok().body(new RestRespuestaBody(HttpStatus.OK.value(), "Registro actualizado exitósamente"));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> borrarRegistro(@PathVariable("id") Long id) {
		Optional<Usuario> usuario = servicio.obtenerRegistro(id);
		if (!usuario.isPresent()) {
			return ((BodyBuilder) ResponseEntity.notFound())
					.body(new RestRespuestaBody(HttpStatus.NOT_FOUND.value(), "Registro no encontrado para eliminar"));
		}
		this.servicio.borrar(id);

		return ResponseEntity.ok().body(new RestRespuestaBody(HttpStatus.OK.value(), "Registro eliminado exitósamente"));
	}
}
