package ar.com.ia.mastermind.algoritmogenetico;

import org.jgap.*;
import org.jgap.event.EventManager;
import org.jgap.impl.*;

public class ConfigurationBuilder {
	private Configuration configuration;

	public ConfigurationBuilder() {
		this.configuration = new Configuration();
		try {
			this.configuration.setBreeder(new GABreeder());
			this.configuration.setRandomGenerator(new StockRandomGenerator());
			this.configuration.setEventManager(new EventManager());
			this.configuration.setMinimumPopSizePercent(0);
			//
			this.configuration.setSelectFromPrevGen(1.0d);
			this.configuration.setKeepPopulationSizeConstant(true);
			this.configuration.setFitnessEvaluator(new DefaultFitnessEvaluator());
			this.configuration.setChromosomePool(new ChromosomePool());
		}
		catch (InvalidConfigurationException e) {
			throw new ConfigurationException(e.getMessage());
		}
	}

	public ConfigurationBuilder SetTournamentSelector(){
		//this.configuration.removeNaturalSelectors(false); //now there are not NaturalSelectors
		try {
			this.configuration.addNaturalSelector(new TournamentSelector(this.configuration, 3, 0.7), true);
		} catch (InvalidConfigurationException e) {
			throw new ConfigurationException(e.getMessage());
		}
		return this;
	}

	public ConfigurationBuilder SetRankingSelector(){
		try {
			BestChromosomesSelector bestChromsSelector = new BestChromosomesSelector(
				this.configuration, 0.90d);
			bestChromsSelector.setDoubletteChromosomesAllowed(true);
			this.configuration.addNaturalSelector(bestChromsSelector, false);
		} catch (InvalidConfigurationException e) {
			throw new ConfigurationException(e.getMessage());
		}
		return this;
	}




	public ConfigurationBuilder SetCrossoverOperator(){
		try {
			this.configuration.addGeneticOperator(new CrossoverOperator(this.configuration, 0.35d));
		} catch (InvalidConfigurationException e) {
			throw new ConfigurationException(e.getMessage());
		}
		return this;
	}




	public ConfigurationBuilder SetMutationOperator(){
		int a_desiredMutationRate = 12;
		return SetMutationOperator(a_desiredMutationRate);
	}

	public ConfigurationBuilder SetMutationOperator(int a_desiredMutationRate) {
		try {
			this.configuration.addGeneticOperator(new MutationOperator(this.configuration, a_desiredMutationRate));
		} catch (InvalidConfigurationException e) {
			throw new ConfigurationException(e.getMessage());
		}
		return this;
	}

	public ConfigurationBuilder SetDecrescentMutationOperator(int maxRate, double runRate){
		int a_desiredMutationRate = (int)(maxRate*(1- runRate));
		return SetMutationOperator(a_desiredMutationRate);
	}

	public ConfigurationBuilder SetCrescentMutationOperator(int maxRate, double runRate){
		int a_desiredMutationRate = (int)(maxRate*runRate);
		return SetMutationOperator(a_desiredMutationRate);
	}

	public ConfigurationBuilder SetFitnessFunction(FitnessFunction fitnessFunction){
		try {
			this.configuration.setFitnessFunction(fitnessFunction);
		} catch (InvalidConfigurationException e) {
			throw new ConfigurationException(e.getMessage());
		}
		return this;
	}

	public ConfigurationBuilder SetSampleChromosome(){
		try {
			Gene[] sampleGenes = new Gene[4];

			sampleGenes[0] = new IntegerGene(this.configuration, 0, 7);  // posición 1
			sampleGenes[1] = new IntegerGene(this.configuration, 0, 7);  // posición 2
			sampleGenes[2] = new IntegerGene(this.configuration, 0, 7);  // posición 3
			sampleGenes[3] = new IntegerGene(this.configuration, 0, 7);  // posición 4

			Chromosome sampleChromosome = new Chromosome(this.configuration, sampleGenes);
			this.configuration.setSampleChromosome(sampleChromosome);
		} catch (InvalidConfigurationException e) {
			throw new ConfigurationException(e.getMessage());
		}
		return this;
	}

	public ConfigurationBuilder SetPopulationSize(int poblacionSize){
		try {
			this.configuration.setPopulationSize(poblacionSize);
		} catch (InvalidConfigurationException e) {
			throw new ConfigurationException(e.getMessage());
		}
		return this;
	}

	public Configuration Build(){
		return this.configuration;
	}

	public class ConfigurationException extends RuntimeException{

		public ConfigurationException(String message) {
			super(message);
		}
	}
}
