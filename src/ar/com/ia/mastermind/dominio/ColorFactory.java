package ar.com.ia.mastermind.dominio;

import ar.com.ia.mastermind.exceptions.BussinessException;

public class ColorFactory {

	public Color construirColor(String unColor) {
		switch (unColor.toLowerCase()) {
			case "rojo":
				return Color.ROJO;
			case "amarillo":
				return Color.AMARILLO;
			case "azul":
				return Color.AZUL;
			case "verde":
				return Color.VERDE;
			case "violeta":
				return Color.VIOLETA;
			case "naranja":
				return Color.NARANJA;
			case "purpura":
				return Color.MAGENTA;
			case "celeste":
				return Color.CELESTE;
			default:
				throw new BussinessException("El color " + unColor + " no existe");
		}
	}
}
