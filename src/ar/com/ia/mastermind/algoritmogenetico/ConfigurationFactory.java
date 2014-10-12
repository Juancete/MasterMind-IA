package ar.com.ia.mastermind.algoritmogenetico;

import ar.com.ia.mastermind.dominio.Combinacion;
import ar.com.ia.mastermind.exceptions.BussinessException;
import org.jgap.Configuration;

import java.util.Properties;

public class ConfigurationFactory {
	private ConfigurationBuilder builder;
	private final Properties properties;
	private final Combinacion target;

	public ConfigurationFactory(Properties properties, Combinacion target) {
		this.properties = properties;
		this.target = target;
	}

	public Configuration getConfiguration(double runRate){
		this.builder = new ConfigurationBuilder();
		int poblacionSize = Integer.parseInt(properties.getProperty("poblacionSize"));

		this.setSelector();
		this.builder.SetCrossoverOperator();
		this.setMutation(properties, runRate);
		return this.builder.SetFitnessFunction(new SolucionFitness(target))
			.SetSampleChromosome()
			.SetPopulationSize(poblacionSize)
			.Build();
	}

	private void setMutation(Properties properties, double runRate) {
		String mutation = properties.getProperty("mutation");
		String mutationRate = properties.getProperty("mutationRate");

		if(mutation.equals("simple")){
			if(!mutationRate.isEmpty()) this.builder.SetMutationOperator(Integer.parseInt(mutationRate));
			else this.builder.SetMutationOperator();
		}
		if(mutation.equals("asc")) this.builder.SetCrescentMutationOperator(Integer.parseInt(mutationRate), runRate);
		if(mutation.equals("desc")) this.builder.SetDecrescentMutationOperator(Integer.parseInt(mutationRate), runRate);
	}

	private void setSelector() {
		String selector = properties.getProperty("selector");
		if(selector.equals("tournament")) builder.SetTournamentSelector();
		if(selector.equals("ranking")) builder.SetRankingSelector();

		if(!(selector.equals("ranking") || selector.equals("tournament")))
			throw new BussinessException("unknown selector: " + selector);
	}

}

