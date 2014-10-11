package ar.com.ia.mastermind.commons;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVFormat;

import ar.com.ia.mastermind.dominio.Combinacion;
import ar.com.ia.mastermind.exceptions.BussinessException;

public class Log {

	CSVPrinter impresora;

	public Log(String nombreDelArchivo) {
		CSVFormat formato = CSVFormat.RFC4180;
		try {
			this.impresora = new CSVPrinter(new PrintWriter(nombreDelArchivo
					+ ".csv"), formato);
			// impresión de header

			this.write(new String[] { "NroCorrida", "ColorPos1", "ColorPos2",
					"ColorPos3", "ColorPos4", "Aptitud" });
		} catch (Exception e) {
			throw new BussinessException("Imposible crear el archivo de Log");
		}
	}

	public void escribir(Combinacion unaCombinacion, double valorFitness,
			int numeroDeCorrida) {
		this.write(new String[] { Integer.toString(numeroDeCorrida),
				unaCombinacion.getColores().get(0).toString(),
				unaCombinacion.getColores().get(1).toString(),
				unaCombinacion.getColores().get(2).toString(),
				unaCombinacion.getColores().get(3).toString(),
				Double.toString(valorFitness) });

	}

	private void write(String[] valor) {
		try {
			this.impresora.printRecord(valor);
		} catch (IOException e) {
			throw new BussinessException("Imposible escribir el archivo de Log");
		}
	}

	public void close() {
		try {
			this.impresora.close();
		} catch (IOException e) {
			throw new BussinessException("Imposible cerrar el archivo de Log");
		}
	}

}
