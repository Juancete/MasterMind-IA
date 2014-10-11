package ar.com.ia.mastermind.dominio;

import ar.com.ia.mastermind.exceptions.BussinessException;

public enum Color {
    ROJO (0, "Rojo"),
    AMARILLO   (1, "Amarillo"),
    AZUL   (2, "Azul"),
    VERDE    (3, "Verde"),
    VIOLETA (4,   "Violeta"),
    NARANJA  (5, "Naranja"),
    PURPURA  (6, "Púrpura"),
    CELESTE (7, "Celeste");

    private int valor;
    private String nombre;

    Color(int valor, String nombre) {
        this.valor = valor;
        this.nombre = nombre;
    }
   
    public String toString(){
		return nombre;
    	
    }
    public static Color fromInteger(int x){
    	for (Color unColor : Color.values()){
    		if (unColor.getValor() == x) return unColor;
    	}
    	throw new BussinessException("el número "+ x + " no existe");
    	
    }

	public int getValor() {
		return valor;
	}

}