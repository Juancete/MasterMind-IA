package ar.com.ia.mastermind.ejecutable;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import ar.com.ia.mastermind.algoritmogenetico.ConfigurationBuilder;
import ar.com.ia.mastermind.algoritmogenetico.ConfigurationFactory;
import org.jgap.Configuration;
import org.jgap.Genotype;
import org.jgap.IChromosome;

import ar.com.ia.mastermind.algoritmogenetico.SolucionFitness;
import ar.com.ia.mastermind.commons.Log;
import ar.com.ia.mastermind.dominio.Color;
import ar.com.ia.mastermind.dominio.ColorFactory;
import ar.com.ia.mastermind.dominio.Combinacion;

public class Main {

	public static void main(String[] args) throws Exception {
		int poblacionSize;
		int maximasEvoluciones;
		String archivoLog = "";
		Combinacion codigoSecreto = null;
		
		System.out.println("Mastermind V1.0");
		if (args.length > 4) { //si hay más parámetros o ninguno
			System.out.println("Error en los parámetros. Debe escribir por ejemplo: masterMind rojo amarillo azul verde");
			System.out.println("O bien si quiere ejecutar en automático: masterMind -a numeroDeCorridas");
			return;
		} else if (args.length == 0) { //si no hay parámetros
			//Modo automático de una corrida
			System.out.println("Se hará una corrida con el código secreto Amarillo Azul, Rojo, Amarillo");
			codigoSecreto = new Combinacion(Color.AMARILLO, Color.AZUL, Color.ROJO, Color.AMARILLO);
		} else if (args.length == 2) { //si hay 2 parámetros
			//Modo automático ?
		} else {
			//Modo Manual
			ColorFactory factory = new ColorFactory();
			codigoSecreto = new Combinacion(factory.construirColor(args[0]), factory.construirColor(args[1]), factory.construirColor(args[2]), factory.construirColor(args[3]));
		}

		Properties prop = new Properties();
		InputStream input = null;
		input = new FileInputStream("config.properties");
		prop.load(input);
		maximasEvoluciones = Integer.parseInt(prop.getProperty("maximasEvoluciones"));
		archivoLog = prop.getProperty("archivoDeLog");

		ConfigurationFactory configurationFactory = new ConfigurationFactory(prop, codigoSecreto);
		Configuration conf = configurationFactory.getConfiguration(0);

		Genotype genotipe = Genotype.randomInitialGenotype(conf);
		IChromosome bestSolutionSoFar = null;
		
		int i;
		Log logueador = new Log(archivoLog);
		
		for (i = 0; i < maximasEvoluciones; i++) {

			Configuration.reset();

			genotipe = new Genotype(
				configurationFactory.getConfiguration(i / (double)maximasEvoluciones)
				,
				genotipe.getPopulation()
			);

			genotipe.evolve();
			bestSolutionSoFar = genotipe.getFittestChromosome();
			Combinacion mejor = new Combinacion(Color.fromInteger((Integer) bestSolutionSoFar.getGene(0).getAllele()),
				Color.fromInteger((Integer) bestSolutionSoFar.getGene(1).getAllele()),
				Color.fromInteger((Integer) bestSolutionSoFar.getGene(2).getAllele()),
				Color.fromInteger((Integer) bestSolutionSoFar.getGene(3).getAllele()));

			logueador.escribir(mejor, bestSolutionSoFar.getFitnessValue(), i);
			
			if (codigoSecreto.equals(mejor)) {
				System.out.println("\nGané en la evolución " + i + "!!!!!! con el resultado: ");
				System.out.println("posicion 1: " + Color.fromInteger((int) bestSolutionSoFar.getGene(0).getAllele()).toString());
				System.out.println("posicion 2: " + Color.fromInteger((int) bestSolutionSoFar.getGene(1).getAllele()).toString());
				System.out.println("posicion 3: " + Color.fromInteger((int) bestSolutionSoFar.getGene(2).getAllele()).toString());
				System.out.println("posicion 4: " + Color.fromInteger((int) bestSolutionSoFar.getGene(3).getAllele()).toString());
				break;
			}
			System.out.println("La mejor solucion para la evolución " + i + ": ");

			System.out.println("posicion 1: " + Color.fromInteger((int) bestSolutionSoFar.getGene(0).getAllele()).toString());
			System.out.println("posicion 2: " + Color.fromInteger((int) bestSolutionSoFar.getGene(1).getAllele()).toString());
			System.out.println("posicion 3: " + Color.fromInteger((int) bestSolutionSoFar.getGene(2).getAllele()).toString());
			System.out.println("posicion 4: " + Color.fromInteger((int) bestSolutionSoFar.getGene(3).getAllele()).toString());
		}
		if (i == maximasEvoluciones) System.out.println("perdí :(");
		logueador.close();
	}

}
