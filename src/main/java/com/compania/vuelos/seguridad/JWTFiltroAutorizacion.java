package com.compania.vuelos.seguridad;


import java.io.IOException; 
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;


import io.jsonwebtoken.Jwts;

public class JWTFiltroAutorizacion extends BasicAuthenticationFilter {

	public JWTFiltroAutorizacion(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}
	
	@Override
	protected void doFilterInternal(
			HttpServletRequest requerimiento,
			HttpServletResponse respuesta,
			FilterChain chain) throws IOException, ServletException {
		
		String header = requerimiento.getHeader(ConstantesSeguridad.HEADER_AUTHORIZATION_KEY);
		if (header == null || !header.startsWith(ConstantesSeguridad.PREFIJO_TOKEN_BEARER)) {
			chain.doFilter(requerimiento, respuesta);
			return;
		}
		UsernamePasswordAuthenticationToken authentication = obtenerAutenticacion(requerimiento);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(requerimiento, respuesta);
	}

	private UsernamePasswordAuthenticationToken obtenerAutenticacion(HttpServletRequest request) {
		String token = request.getHeader(ConstantesSeguridad.HEADER_AUTHORIZATION_KEY);
		if (token != null) {
			// Se procesa el token y se recupera el usuario.
			String user = Jwts.parser()
						.setSigningKey(ConstantesSeguridad.LLAVE_SECRETA.getBytes())
						.parseClaimsJws(token.replace(ConstantesSeguridad.PREFIJO_TOKEN_BEARER, ""))
						.getBody()
						.getSubject();

			if (user != null) {
				return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
			}
			return null;
		}
		return null;
	}
}