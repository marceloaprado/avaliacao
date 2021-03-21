package br.com.tinnova.avaliacao.exercicio3;

/**
 * Classe responsavel por calcular o fatorial de determinado numero
 *
 * 
 * @author Marcelo Prado
 * 
 */
public class Fatorial {
	
	/**
	 * <p> Calcula o fatorial de um n�mero </p>
	 *
	 * @param numero o n�mero a ser calculado o fatorial
	 * @return o fatorial calculado
	 */
	public static long calcular(long numero) {		
		if(numero < 0)
			throw new IllegalArgumentException("Nao e possivel calcular o fatorial de um numero negativo.");
		
		long fatorial = 1;
		for(long i = numero; i > 0; i--) 
			fatorial *= i;
		
		return fatorial;
	}
}
