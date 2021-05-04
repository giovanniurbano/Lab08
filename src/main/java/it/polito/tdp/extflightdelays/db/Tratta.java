package it.polito.tdp.extflightdelays.db;

public class Tratta {
	private int idOrigine;
	private int idDestinazione;
	private double sumDistanza;
	private int nVoli;
	
	public Tratta(int idOrigine, int idDestinazione, double sumDistanza, int nVoli) {
		this.idOrigine = idOrigine;
		this.idDestinazione = idDestinazione;
		this.sumDistanza = sumDistanza;
		this.nVoli = nVoli;
	}

	public int getIdOrigine() {
		return idOrigine;
	}

	public void setIdOrigine(int idOrigine) {
		this.idOrigine = idOrigine;
	}

	public int getIdDestinazione() {
		return idDestinazione;
	}

	public void setIdDestinazione(int idDestinazione) {
		this.idDestinazione = idDestinazione;
	}

	public double getSumDistanza() {
		return sumDistanza;
	}

	public void setSumDistanza(double sumDistanza) {
		this.sumDistanza = sumDistanza;
	}

	public int getnVoli() {
		return nVoli;
	}

	public void setnVoli(int nVoli) {
		this.nVoli = nVoli;
	}
	
	
}
