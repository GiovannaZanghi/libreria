package com.generation.libreria.controllers;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.generation.libreria.dao.DAOLibri;
import com.generation.libreria.entities.Libro;

//@RequestMapping("/libri") applica in automatico a ogni mapping del controller il prefisso /libri
//quindi ad esempio il mapping "elencolibri" diventa "/libri/elencolibri"
@Controller
@RequestMapping("/libri")
public class LibriController
{
	@Autowired
	private DAOLibri dl;
	
	@Autowired
	private ApplicationContext context;
	
	@GetMapping("elencolibri")
	public String elencoLibri(Model model)
	{
		//Il Model è un oggetto di Spring. Immaginatelo come una scatola nella quale potete
		//inserire quello che volete a patto di definirlo attraverso un'etichetta.
		//model.addAttribute("ETICHETTA",CONTENUTO);
		model.addAttribute("elencolibri",dl.leggiTutti());
		model.addAttribute("salutosimpa","Ciao belli");
		//Il Model ovvero la scatola riempita con tutto quello che viene definito dal programmatore
		//ed etichettato a dovere VIENE POI SPEDITO NEL RETURN ALLA PAGINA JSP DI DESTINAZIONE
		return "/libri/elencolibri.jsp";
		//Le pagine JSP sono un mix tra HTML e JAVA perché attraverso una specifica sintassi
		//permettono di leggere JAVA direttamente sull'HTML.
		//Quali differenze ci sono rispetto a Javascript.
		//I processi gestiti dalle JSP non vengono mostrati nella console.
		//Le JSP lavorano in backend e i processi non sono visibili lato frontend.
	}
	
	@GetMapping("eliminalibro")
	public String elimina(@RequestParam("id") int idLibro)
	{
		//Da tutto questo URL "localhost:8080/eliminalibro?id=IDLIBRO"
		//@RequestParam recupera il parametro "id" e lo inserisce all'interno del parametro int idLibro
		//Successivamente invia il parametro al metodo delete di DAOLibri
		if(dl.delete(idLibro))
			return "redirect:elencolibri";
		else
			return "Errore nell'eliminazione del libro con id=" + idLibro;
		//REDIRECT permette di reindirizzare l'utente al mapping elencolibri in modo che una volta
		//eliminato il record sul DB si ritorni alla pagina con tutti i libri presenti.
		//NOTA BENE: scrivere redirect:elencolibri e scrivere redirect:elencolibri.jsp sono due cose diverse:
		//redirect:elencolibri ->	rimanda al mapping che carica il model che a sua volta contiene tutti i libri
		//							che vengono richiesti dal metodo dl.leggiTutti() direttamente al DB
		//redirect:elencolibri.jsp ->	rimanda alla pagina JSP che da sola non ha la possibilità di richiamare
		//								il model perché è il mapping a poterlo fare. Quindi arriverebbe
		//								semplicemente la pagina senza il contenuto del mapping.
		//								Se la pagina è già stata visitata il contenuto non verrebbe
		//								aggiornato perché recupererebbe quello in CACHE.
	}
	
	@GetMapping("dettaglilibro")
	public String dettagli(@RequestParam("id") int idLibro, Model model)
	{
		//Il metodo attraverso RequestMapping riceve "localhost:8080/dettaglilibro?id=IDLIBRO"
		//Come parametro abbiamo anche Model...vediamo perché!
		//Con l'id che riceviamo dalla request cerchiamo l'oggetto Libro corrispondente
		Libro l = dl.cercaPerId(idLibro);
		if(l == null)
		{
			//Se c'è un problema di ID...non dovrebbe...rimandiamo direttamente a elencolibri
			return "redirect:elencolibri";
		}
		else
		{
			//Dopo aver recuperato l'intero oggetto LIBRO dal DB lo aggiungo allo scatolone model
			//con l'etichetta "libro" e lo invio alla pagina dettaglilibro.jsp -> PRIMA QUESTA OPERAZIONE
			//L'AVREI DOVUTA FARE CON JSON.
			model.addAttribute("libro",l);
			return "/libri/dettaglilibro.jsp";
		}
	}
	
	@GetMapping("formmodifica")
	public String vaiAllaForm(@RequestParam("id") int idLibro, Model model)
	{
		Libro l = dl.cercaPerId(idLibro);
		if(l == null)
		{
			return "redirect:elencolibri";
		}
		else
		{
			model.addAttribute("libro",l);
			return "/libri/formmodifica.jsp";
		}
	}
	
	@GetMapping("modificalibro")
	public String aggiorna(@RequestParam Map<String,String> inputs)
	{
		Libro l = context.getBean(Libro.class,inputs);
		if(dl.update(l))
			return "redirect:elencolibri";
		else
			return "Errore nell'aggiornamento del libro con id = " + l.getId();
	}
	
	@GetMapping("formnuovolibro")
	public String nuovoLibro()
	{
		return "/libri/formnuovolibro.html";
	}
	
	@GetMapping("nuovolibro")
	public String aggiungiLibro(@RequestParam Map<String,String> inputsNuovoLibro)
	{
		Libro l = context.getBean(Libro.class,inputsNuovoLibro);
		if(dl.create(l))
			return "redirect:elencolibri";
		else
			return "Errore nell'inserimento del nuovo libro";
	}
}