package ar.com.ia.mastermind.commons;

import ar.com.ia.mastermind.dominio.Combinacion;

public class Log {

	Logueable estrategiaDeLogueo;

	public Log(String nombreDelArchivo) {
		if (nombreDelArchivo == null)
			this.estrategiaDeLogueo = new LogEmpty();
		else
			this.estrategiaDeLogueo = new LogCSV();
		this.estrategiaDeLogueo.setearArchivo(nombreDelArchivo);
	}

	public void close(){
		this.estrategiaDeLogueo.close();
	}
	public void escribir(Combinacion unaCombinacion, double valorFitness,
			int numeroDeCorrida){
		this.estrategiaDeLogueo.escribir(unaCombinacion, valorFitness, numeroDeCorrida);
	}

}
