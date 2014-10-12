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
		if (args.length == 4) { //si hay 4 par\u00e1metros 
			//Modo Manual
			ColorFactory factory = new ColorFactory();
			codigoSecreto = new Combinacion(factory.construirColor(args[0]), factory.construirColor(args[1]), factory.construirColor(args[2]), factory.construirColor(args[3]));
		} else if (args.length == 0) { //si no hay par\u00e1metros
			//Modo autom\u00e1tico de una corrida
			System.out.println("La corrida tiene por código secreto Amarillo Azul, Rojo, Amarillo");
			codigoSecreto = new Combinacion(Color.AMARILLO, Color.AZUL, Color.ROJO, Color.AMARILLO);
		} else if (args.length == 2) { //si hay 2 par\u00e1metros
			//Modo autom\u00e1tico ?
			
		} else {
			System.out.println("Error en los parámetros. Debe escribir por ejemplo: masterMind rojo amarillo azul verde");
			System.out.println("Los colores posibles son " + java.util.Arrays.asList(Color.values()));
			//System.out.println("O bien si quiere ejecutar en autom\u00e1tico: masterMind -a numeroDeCorridas");
			return;			
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
				System.out.println("\nGané en la evoluci\u00f3n " + i + "!!!!!! con el resultado: ");
				mejor.print();
				break;
			}
			System.out.println("\nLa mejor solucion para la evoluci\u00f3n " + i + ": ");
			mejor.print();

		}
		if (i == maximasEvoluciones) System.out.println("perdí :(");
		logueador.close();
	}

}
