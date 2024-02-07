package it.betacom.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Animale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int matricola;

    private String nomeAnimale;
    public Animale() {
		super();
	}

	public Animale(int  matricola, String nome_animale, Date data_acquisto, double prezzo, String tipo_animale,
			Cliente cliente) {
		super();
		this.matricola = matricola;
		this.nomeAnimale = nome_animale;
		this.dataAcquisto = data_acquisto;
		this.prezzo = prezzo;
		this.tipoAnimale = tipo_animale;
		this.cliente = cliente;
	}

	@Temporal(TemporalType.DATE)
    private Date dataAcquisto;
    private double prezzo;
    private String tipoAnimale;

    @ManyToOne
    @JoinColumn(name = "idCliente")
    private Cliente cliente;

    // Costruttori, getter e setter, e altri metodi

    public int getMatricola() {
        return matricola;
    }

    public void setMatricola(int matricola) {
        this.matricola = matricola;
    }

    public String getNomeAnimale() {
        return nomeAnimale;
    }

    public void setNomeAnimale(String nome_animale) {
        this.nomeAnimale = nome_animale;
    }

    public Date getDataAcquisto() {
        return dataAcquisto;
    }

    public void setDataAcquisto(Date data_acquisto) {
        this.dataAcquisto = data_acquisto;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public String getTipoAnimale() {
        return tipoAnimale;
    }

    public void setTipoAnimale(String tipo_animale) {
        this.tipoAnimale = tipo_animale;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
}
