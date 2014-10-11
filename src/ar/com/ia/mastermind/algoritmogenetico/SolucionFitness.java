package ar.com.ia.mastermind.algoritmogenetico;

import org.jgap.FitnessFunction;
import org.jgap.IChromosome;

import ar.com.ia.mastermind.dominio.Combinacion;

public class SolucionFitness extends FitnessFunction {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Combinacion solucion;

	public SolucionFitness(Combinacion solucion) {
		this.solucion = solucion;
	}


	@Override
	protected double evaluate(IChromosome unaSolucion) {
		double coloresConPosicionCorrectos = this.solucion.ColorPosicionCorrectos(unaSolucion);
		double coloresCorrectos = this.solucion.coloresCorrectos(unaSolucion);
		return coloresConPosicionCorrectos + coloresCorrectos;
	}


}
