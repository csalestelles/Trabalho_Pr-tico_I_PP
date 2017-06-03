package BibliotecaSIS;

public class Relatorio 
{	
	private static int valor;
	private static int tipo;
//	usuarios = 0, administradores = 1, exemplares = 5, empReal = 2, empAnd = 3, devAtra = 4;
	
	
	public static int getValor() {
		return valor;
	}
	public static void setValor(int valor) {
		Relatorio.valor = valor;
	}
	public static int getTipo() {
		return tipo;
	}
	public static void setTipo(int tipo) {
		Relatorio.tipo = tipo;
	}
	
	

}
