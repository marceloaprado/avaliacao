package br.com.tinnova.avaliacao.exercicio4;

import java.util.Scanner;

public class Main {
	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Informe o numero a ser calculado a soma dos multiplos de 3 ou 5:");
		long numero = scanner.nextLong();

		scanner.close();

		System.out.println(String.format("A soma dos multiplos de 3 ou 5 abaixo do numero %d e %d.", numero, Multiplo3Ou5.calcular(numero)));
	}
}
