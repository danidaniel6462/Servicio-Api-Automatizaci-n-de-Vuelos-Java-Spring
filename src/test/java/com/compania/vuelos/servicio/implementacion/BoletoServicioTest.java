package com.compania.vuelos.servicio.implementacion;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class BoletoServicioTest {

	@Test
	public void contextLoads() 
	{
		System.out.println(LocalDate.now());
	}
	
	@Test
	public void comprobarLocalDateTest() 
	{
		Object fecha = LocalDate.now();
		LocalTime horaActual = LocalTime.now();
		System.out.println("\n\n\nSe imprime en consola de Spring Boot \n" + fecha + " hora Actual supuesta " + horaActual + "\n\n\n");
	}
}
