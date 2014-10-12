package ar.com.ia.mastermind.commons;

import ar.com.ia.mastermind.dominio.Combinacion;

public interface Logueable {
	
	public void setearArchivo(String nombreArchivo);
	public void escribir(Combinacion unaCombinacion, double valorFitness,int numeroDeCorrida);
	public void close();
}
