package br.com.tinnova.avaliacao.exercicio3;

import java.util.Scanner;

public class Main {
	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Informe o numero a ser calculado seu fatorial:");
		long numero = scanner.nextLong();
		
		scanner.close();
		
		System.out.println(String.format("O fatorial do numero %d e %d.", numero, Fatorial.calcular(numero)));		
		
		// Fatorial de um numero invalido
		try {
			System.out.println("Calulando fatorial de -1");
			Fatorial.calcular(-1);
		} catch(IllegalArgumentException ex) {
			System.out.println(ex.getMessage());
		}
	}
}
