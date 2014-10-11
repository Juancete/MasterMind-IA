package ar.com.ia.mastermind.ejecutable;

import org.jgap.Chromosome;
import org.jgap.Configuration;
import org.jgap.FitnessFunction;
import org.jgap.Gene;
import org.jgap.Genotype;
import org.jgap.IChromosome;
import org.jgap.impl.DefaultConfiguration;
import org.jgap.impl.IntegerGene;

import ar.com.ia.mastermind.algoritmogenetico.SolucionFitness;
import ar.com.ia.mastermind.dominio.Color;
import ar.com.ia.mastermind.dominio.Combinacion;

public class Main {
	
	private final static int POBLACION_A_DESARROLLAR = 15;
	private final static int MAXIMAS_EVOLUCIONES = 10;
	
	public static void main(String[] args) throws Exception {

		  Configuration conf = new DefaultConfiguration();


		  Combinacion codigoSecreto = new Combinacion (Color.AMARILLO, Color.AZUL,  Color.ROJO, Color.AMARILLO); 
		  FitnessFunction myFunc =
		    new SolucionFitness(codigoSecreto);

		  conf.setFitnessFunction( myFunc );

		  Gene[] sampleGenes = new Gene[ 4 ];

		  sampleGenes[0] = new IntegerGene(conf, 0, 7 );  // posición 1
		  sampleGenes[1] = new IntegerGene(conf, 0, 7 );  // posición 2
		  sampleGenes[2] = new IntegerGene(conf, 0, 7 );  // posición 3
		  sampleGenes[3] = new IntegerGene(conf, 0, 7 );  // posición 4

		  Chromosome sampleChromosome = new Chromosome(conf, sampleGenes );

		  conf.setSampleChromosome( sampleChromosome );
		  conf.setPopulationSize(POBLACION_A_DESARROLLAR);

		  Genotype population = Genotype.randomInitialGenotype( conf );
		  IChromosome bestSolutionSoFar = null;
		  int i;
		  for( i = 0; i < MAXIMAS_EVOLUCIONES; i++ )
		  {
		      population.evolve();
		      bestSolutionSoFar = population.getFittestChromosome();
		      Combinacion mejor = new Combinacion(Color.fromInteger((Integer) bestSolutionSoFar.getGene(0).getAllele()),
		    		  Color.fromInteger((Integer) bestSolutionSoFar.getGene(1).getAllele()),
		    		  Color.fromInteger((Integer) bestSolutionSoFar.getGene(2).getAllele()),
		    		  Color.fromInteger((Integer) bestSolutionSoFar.getGene(3).getAllele()));
		      
		      if (codigoSecreto.equals(mejor)){
				  System.out.println( "\nGané en la evolución "+ i +"!!!!!! con el resultado: " );
				  System.out.println("posicion 1: "+ Color.fromInteger((int) bestSolutionSoFar.getGene(0).getAllele()).toString());
				  System.out.println("posicion 2: "+ Color.fromInteger((int) bestSolutionSoFar.getGene(1).getAllele()).toString());
				  System.out.println("posicion 3: "+ Color.fromInteger((int) bestSolutionSoFar.getGene(2).getAllele()).toString());
				  System.out.println("posicion 4: "+ Color.fromInteger((int) bestSolutionSoFar.getGene(3).getAllele()).toString());
				  break;
		      }
			  System.out.println( "La mejor solucion para la evolución "+ i +": " );

			  System.out.println("posicion 1: "+ Color.fromInteger((int) bestSolutionSoFar.getGene(0).getAllele()).toString());
			  System.out.println("posicion 2: "+ Color.fromInteger((int) bestSolutionSoFar.getGene(1).getAllele()).toString());
			  System.out.println("posicion 3: "+ Color.fromInteger((int) bestSolutionSoFar.getGene(2).getAllele()).toString());
			  System.out.println("posicion 4: "+ Color.fromInteger((int) bestSolutionSoFar.getGene(3).getAllele()).toString());		      
		  }
		  if (i == MAXIMAS_EVOLUCIONES) System.out.println("perdí :(");
		}
		
}
