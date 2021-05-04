package it.polito.tdp.extflightdelays.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.extflightdelays.db.ExtFlightDelaysDAO;
import it.polito.tdp.extflightdelays.db.Tratta;

public class Model {
	private ExtFlightDelaysDAO dao;
	private Graph<Airport, DefaultWeightedEdge> grafo;
	private Map<Integer, Airport> idMap;
	private String voliScelti;
	
	public Model() {
		dao = new ExtFlightDelaysDAO();
		idMap = new HashMap<Integer, Airport>();
		voliScelti = "";
	}
	
	public void creaGrafo(int media) {
		this.grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		this.dao.loadAllAirports(idMap);
		Graphs.addAllVertices(grafo, idMap.values()); 
		
		List<Tratta> tratte = dao.getTratte();
		double m = 0.0;
		int c = 0;
		for(Tratta t1 : tratte) {
			for(Tratta t2 : tratte) {
				if(t1.getIdOrigine() == t2.getIdDestinazione() && t1.getIdDestinazione() == t2.getIdOrigine()) {
					m = (t1.getSumDistanza()+t2.getSumDistanza())/(t1.getnVoli()+t2.getnVoli());
					if(m > media && !this.grafo.containsEdge(idMap.get(t1.getIdOrigine()), idMap.get(t1.getIdDestinazione()))) {
						Graphs.addEdge(this.grafo, idMap.get(t1.getIdOrigine()), idMap.get(t1.getIdDestinazione()), m);
						voliScelti += idMap.get(t1.getIdOrigine()).getAirportName() + " - " + idMap.get(t1.getIdDestinazione()).getAirportName() + ": " + m + "\n";
						c++;
					}
				}
			}
			if(c == 0) {
				m = t1.getSumDistanza()/t1.getnVoli();
				if(m > media && !this.grafo.containsEdge(idMap.get(t1.getIdOrigine()), idMap.get(t1.getIdDestinazione()))) {
					Graphs.addEdge(this.grafo, idMap.get(t1.getIdOrigine()), idMap.get(t1.getIdDestinazione()), m);
					voliScelti += idMap.get(t1.getIdOrigine()).getAirportName() + " - " + idMap.get(t1.getIdDestinazione()).getAirportName() + ": " + m + "\n";
				}
			}
			c = 0;
		}
	}
	
	public int getnNodi() {
		return  grafo.vertexSet().size();
	}
	public int getnArchi() {
		return grafo.edgeSet().size();
	}
	public String getVoliScelti() {
		return voliScelti;
	}
}
