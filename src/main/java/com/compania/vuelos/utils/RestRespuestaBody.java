package com.compania.vuelos.utils;

public class RestRespuestaBody {

	private Integer codigoRespuesta;
	private String mensajeRespuesta;

	public RestRespuestaBody(Integer codigoRespuesta) {
		this.codigoRespuesta = codigoRespuesta;
	}

	public RestRespuestaBody(Integer codigoRespuesta, String mensajeRespuesta) {
		this.codigoRespuesta = codigoRespuesta;
		this.mensajeRespuesta = mensajeRespuesta;
	}
	
	public Integer getCodigoRespuesta() {
		return codigoRespuesta;
	}

	public void setCodigoRespuesta(Integer codigoRespuesta) {
		this.codigoRespuesta = codigoRespuesta;
	}

	public String getMensajeRespuesta() {
		return mensajeRespuesta;
	}

	public void setMensajeRespuesta(String mensajeRespuesta) {
		this.mensajeRespuesta = mensajeRespuesta;
	}

}
