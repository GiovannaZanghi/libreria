package com.generation.libreria.entities;
import com.generation.utility.entities.Entity;

public class Libro extends Entity
{
	private String autore;
	private String titolo;
	
	public String getAutore()
	{
		return autore;
	}
	
	public void setAutore(String autore)
	{
		this.autore = autore;
	}
	
	public String getTitolo()
	{
		return titolo;
	}
	
	public void setTitolo(String titolo)
	{
		this.titolo = titolo;
	}
}