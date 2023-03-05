package com.generation.libreria;
import java.util.Map;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import com.generation.libreria.dao.DAOLibri;
import com.generation.libreria.entities.Libro;
import com.generation.utility.dao.Database;

@Configuration
public class Context
{
	@Bean
	public Database db()
	{
		return new Database("springlibreria","root","root");
	}
	
	@Bean
	public DAOLibri daolibri()
	{
		return new DAOLibri();
	}
	
	@Bean
	@Scope("prototype")
	public Libro libroMappa(Map<String,String> riga)
	{
		Libro l = new Libro();
		l.fromMap(riga);
		return l;
	}
}