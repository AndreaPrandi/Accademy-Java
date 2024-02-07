package it.betacom;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import it.betacom.dao.AnimaleDAO;
import it.betacom.dao.ClienteDAO;
import it.betacom.model.Animale;
import it.betacom.model.Cliente;

public class main {

	public static void main(String[] args) throws ParseException {
Cliente cliente = new Cliente(2,"Franco","Baresi","via la loggia 4","vinovo","33333342");
ClienteDAO dao = new ClienteDAO();
//dao.aggiungiCliente(cliente);
String dateString = "2023-07-21";
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
Date date= sdf.parse(dateString);
Animale animale = new Animale(1,"fuffy",date,490,"Gatto",cliente);
AnimaleDAO animaleDAO = new AnimaleDAO();
animaleDAO.aggiungiAnimale(animale);
	}

}
