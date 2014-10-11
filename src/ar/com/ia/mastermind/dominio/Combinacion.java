package ar.com.ia.mastermind.dominio;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.jgap.IChromosome;

public class Combinacion {
	private List<Color> colores = new ArrayList<Color>();

	public Combinacion(Color posicion1, Color posicion2, Color posicion3, Color posicion4) {
		this.getColores().add(posicion1);
		this.getColores().add(posicion2);
		this.getColores().add(posicion3);
		this.getColores().add(posicion4);
	}

	public double ColorPosicionCorrectos(IChromosome unaSolucion) {
		double cuenta = 0;

		for (int i = 0; i < 4; i++) {
			if (this.getColores().get(i).getValor() == ((Integer) unaSolucion.getGene(i).getAllele()))
				cuenta = cuenta + 25;
		}
		return cuenta;
	}

	public double coloresCorrectos(IChromosome unaSolucion) {
		double cuenta = 0;

		for (int i = 0; i < 4; i++) {
			if (this.getColores().contains(Color.fromInteger((Integer) unaSolucion.getGene(i).getAllele())))
				cuenta = cuenta + 10;
		}
		return cuenta;
	}

	@Override
	public boolean equals(Object unaCombinacion) {

		return this.colores.equals(((Combinacion) unaCombinacion).getColores());

	}

	public List<Color> getColores() {
		return colores;
	}

	public void setColores(List<Color> colores) {
		this.colores = colores;
	}
}
