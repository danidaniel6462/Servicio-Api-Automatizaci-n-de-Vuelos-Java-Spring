//package com.compania.vuelos.configuracion;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
//import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
//
//import com.compania.vuelos.POJO.Boleto;
//import com.compania.vuelos.POJO.Perfil;
//import com.compania.vuelos.POJO.PerfilUsuario;
//import com.compania.vuelos.POJO.Usuario;
//
//@Configuration
//public class RepositorioConfiguracion extends RepositoryRestConfigurerAdapter{
//		
//	    @Override
//	    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
//	        config.exposeIdsFor(Usuario.class);
//	        config.exposeIdsFor(Perfil.class);
//	        config.exposeIdsFor(Boleto.class);
//	        config.exposeIdsFor(PerfilUsuario.class);
//	}
//}
