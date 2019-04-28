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

import com.compania.vuelos.POJO.Boleto;
import com.compania.vuelos.servicio.implementacion.BoletoServicio;
import com.compania.vuelos.utils.RestRespuestaBody;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin
@RestController
@RequestMapping("/api/boleto")
public class BoletoController {

	@Autowired
	protected BoletoServicio servicio;

	protected ObjectMapper mapeador = new ObjectMapper();

	@GetMapping
	public ResponseEntity<?> obtenerTodos() {
		List<Boleto> boletos = this.servicio.todos();
		if (boletos.isEmpty()) {
			return new ResponseEntity<>("No hay boletos", HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Boleto>>(boletos, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> obtenerRegistro(@PathVariable("id") Long id) {
		Optional<Boleto> boleto = this.servicio.obtenerRegistro(id);
		if (!boleto.isPresent()) {
			return ((BodyBuilder) ResponseEntity.notFound())
					.body(new RestRespuestaBody(HttpStatus.NOT_FOUND.value(), "Registro no encontrado"));
		}
		return ResponseEntity.ok(boleto);
	}

	@PostMapping()
	public ResponseEntity<Object> crear(@RequestBody Boleto boleto){
		if (boleto.getId() != null) {
			return ResponseEntity.badRequest().body(new RestRespuestaBody(HttpStatus.BAD_REQUEST.value(),
					"No se debe enviar el ID en la creación de registros, ya que se generán automáticamente."));
		}
		this.servicio.crear(boleto);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(boleto.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> actualizarRegistro(@RequestBody String entidadJSON, @PathVariable Long id)
			throws JsonParseException, JsonMappingException, IOException {
		Boleto boleto = this.mapeador.readValue(entidadJSON, Boleto.class);
		if (id != boleto.getId()) {
			return ResponseEntity.badRequest().body(new RestRespuestaBody(HttpStatus.BAD_REQUEST.value(),
					"Error, Verifique que el Id del objeto coincida con el id enviado en la ruta /{id}."));
		}
		Optional<Boleto> boletoEncontrado = servicio.obtenerRegistro(id);
		if (!boletoEncontrado.isPresent()) {
			return ((BodyBuilder) ResponseEntity.notFound()).body(
					new RestRespuestaBody(HttpStatus.NOT_FOUND.value(), "Registro no encontrado para actualizar"));
		}
		this.servicio.actualizar(boleto);
		return ResponseEntity.ok()
				.body(new RestRespuestaBody(HttpStatus.OK.value(), "Registro actualizado exitósamente"));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> borrarRegistro(@PathVariable("id") Long id) {
		Optional<Boleto> boleto = servicio.obtenerRegistro(id);
		if (!boleto.isPresent()) {
			return ((BodyBuilder) ResponseEntity.notFound())
					.body(new RestRespuestaBody(HttpStatus.NOT_FOUND.value(), "Registro no encontrado para eliminar"));
		}
		this.servicio.borrar(id);

		return ResponseEntity.ok()
				.body(new RestRespuestaBody(HttpStatus.OK.value(), "Registro eliminado exitósamente"));
	}
	
	@PostMapping("/actualizarBoletoObtenerLista")
	public ResponseEntity<List<Boleto>> actualizarEstadoPorBoletoId(@RequestBody Boleto boleto) {
		List<Boleto> boletos = this.servicio.actualizarBoletoObtenerLista(boleto);
		return ResponseEntity.ok(boletos);
	}
	
}
