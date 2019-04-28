package com.compania.vuelos.seguridad;

public class ConstantesSeguridad {

	// SEGURIDAD SPRING 
	public static final String LOGIN_URL = "/login";
	public static final String SIGN_IN = "/api/usuario/signIn";
	public static final String HEADER_AUTHORIZATION_KEY = "Authorization";
	public static final String PREFIJO_TOKEN_BEARER = "Bearer ";
	
	// JWT
	public static final String LLAVE_SECRETA = "$Compania.0.VueloJWT";
	public static final String ISSUER = "www.companiavuelos.com";
	public static final long TOKEN_TIEMPO_EXPIRACION = 86_400_000;
}
