package ar.com.ia.mastermind.dominio;

public class Color {
	private int valor;
	
	public Color(int valor){
		this.setValor(valor);
	}
	@Override
	public boolean equals(Object obj) {
		
		return this.getValor() == ((Color)obj).getValor();
	}
	public boolean esIgualA(int color)
	{
		return (this.valor == color);
	}
	public int getValor() {
		return valor;
	}
	public void setValor(int valor) {
		this.valor = valor;
	}
	public String toString(){
		return String.valueOf(this.valor) ;
		
	}
}
