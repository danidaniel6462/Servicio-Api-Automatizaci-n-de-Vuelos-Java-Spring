package com.compania.vuelos.servicio.implementacion;

import static java.util.Collections.emptyList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.compania.vuelos.POJO.Usuario;
import com.compania.vuelos.repositorio.IUsuarioRepositorio;

@Service
public class UserDetailsServicio implements UserDetailsService {

	   private IUsuarioRepositorio repositorioUsuario;

	    public UserDetailsServicio(IUsuarioRepositorio repositorioUsuario) {
	        this.repositorioUsuario = repositorioUsuario;
	    }

	    @Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	        Usuario user = repositorioUsuario.findByUsername(username);
	        if (user == null) {
	            throw new UsernameNotFoundException(username);
	        }
	        return new User(user.getUsername(), user.getPassword(), emptyList());
	    }
	
}
