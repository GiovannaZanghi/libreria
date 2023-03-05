package com.generation.libreria.dao;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import com.generation.libreria.entities.Libro;
import com.generation.utility.dao.Database;

public class DAOLibri
{
	@Autowired
	private Database db;
	
	@Autowired
	private ApplicationContext context;
	
	public List<Libro> read(String query, String... params)
	{
		List<Libro> ris = new ArrayList<Libro>();
		List<Map<String, String>> righe = db.rows(query, params);
		
		for(Map<String, String> riga : righe)
		{
			Libro l = context.getBean(Libro.class,riga);
			ris.add(l);
		}
		return ris;
	}//Fine di read()
	
	public List<Libro> leggiTutti()
	{
		return read("select * from libri");
	}
	
	public boolean create(Libro l)
	{
		String query = "insert into libri (autore, titolo) values (?,?)";
		return db.update(query, l.getAutore(), l.getTitolo());
	}
	
	public boolean delete(int id)
	{
		String query = "delete from libri where id = ?";
		return db.update(query,id + "");
	}
	
	public boolean update(Libro l)
	{
		String query = "update libri set autore = ?, titolo = ? where id = ?";
		return db.update(query, l.getAutore(), l.getTitolo(), l.getId() + "");
	}
	
	public Libro cercaPerId(int id)
	{
		return read("select * from libri where id = ?", id + "").get(0);
	}
}