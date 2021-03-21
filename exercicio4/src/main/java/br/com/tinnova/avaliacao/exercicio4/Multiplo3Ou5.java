package br.com.tinnova.avaliacao.exercicio4;

/**
 * Classe responsvel por calcular a soma dos multiplos de 3 ou 5 em um
 * intervalo abaixo de numero informado
 * 
 * @author Marcelo Prado
 * 
 */
public class Multiplo3Ou5 {
	/**
	 * <p>
	 * Calcula a soma dos multiplos de 3 e 5 no intervalo de 0 a um numero informado
	 * </p>
	 *
	 * @param numero o numero a ser calculado a soma dos multiplos de 3 ou 5
	 * @return a soma calculada
	 */
	public static long calcular(long numero) {
		long soma = 0;

		for (long i = numero - 1; i > 1; i--) {
			if (isMultiplo3Ou5(i))
				soma += i;
		}

		return soma;
	}

	/**
	 * <p> Verifica se um numero e multiplo de 3 ou 5 </p>
	 * 
	 * @param numero numero a ser verificado
	 * @return boolean true se for multiplo de 3 ou 5, e false caso contrario 
	 */
	private static boolean isMultiplo3Ou5(long numero) {
		return numero % 3 == 0 || numero % 5 == 0;
	}
}
