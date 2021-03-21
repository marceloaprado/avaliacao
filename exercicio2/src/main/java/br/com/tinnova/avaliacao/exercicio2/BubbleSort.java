package br.com.tinnova.avaliacao.exercicio2;

/**
 * Classe responsavel por ordenar um vetor de numeros inteiros utilizando o
 * algoritmo Bubble Sort
 *
 * 
 * @author Marcelo Prado
 * 
 */
public class BubbleSort {

	/**
	 * <p>
	 * Ordenar de forma crescente o vetor passaram como parametro atraves do
	 * algoritmo Bubble Sort
	 * </p>
	 *
	 * @param vetor o vetor de inteiros a ser ordenado
	 */
	public static void ordernar(int[] vetor) {
		for (int iteracao = 0; iteracao < vetor.length - 1; iteracao++) {
			for (int iteracaoMinima = 0; iteracaoMinima < vetor.length - 1 - iteracao; iteracaoMinima++) {
				if (vetor[iteracaoMinima] > vetor[iteracaoMinima + 1])
					trocar(vetor, iteracaoMinima, iteracaoMinima + 1);
			}
		}
	}

	/**
	 * <p> Troca o valor de duas posicoes no vetor </p>
	 *
	 * @param vetor o vetor de inteiros
	 * @param pos1 a primeira posicao
	 * @param pos2 a segunda posicao
	 */
	private static void trocar(int[] vetor, int pos1, int pos2) {
		int aux = vetor[pos1];
		vetor[pos1] = vetor[pos2];
		vetor[pos2] = aux;
	}
}
