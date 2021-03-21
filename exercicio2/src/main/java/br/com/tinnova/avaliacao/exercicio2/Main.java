package br.com.tinnova.avaliacao.exercicio2;

public class Main {
	public static void main(String args[]) {
		int[] numeros = {5, 3, 2, 4, 7, 1, 0, 6};
		
		BubbleSort.ordernar(numeros);
		
		for(int numero : numeros)
			System.out.print(numero + " ");
	}
}
