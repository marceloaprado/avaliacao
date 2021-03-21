package br.com.tinnova.avaliacao.exercicio2;

public class Main {
	public static void main(String args[]) {
		// Resultado corretamente apurado
		ConsolidadoEleicao consolidadoEleicao = new ConsolidadoEleicao(1000, 800, 150, 50);
		EstatisticaEleicao estatisticaEleicao = new EstatisticaEleicao(consolidadoEleicao);

		System.out.println(
				String.format("Votos validos: %.2f%%", estatisticaEleicao.calcularPercentualVotosValidos() * 100));
		System.out.println(
				String.format("Votos em branco: %.2f%%", estatisticaEleicao.calcularPercentualVotosBrancos() * 100));
		System.out
				.println(String.format("Votos nulos: %.2f%%", estatisticaEleicao.calcularPercentualVotosNulos() * 100));

		// Resultado com votos inv�lidos
		try {
			new ConsolidadoEleicao(1000, -1, 950, 50);
		} catch (IllegalArgumentException ex) {
			System.out.println(ex.getMessage());
		}

		// Resultado com votos inconsistentes
		try {
			new ConsolidadoEleicao(1000, 800, 150, 100);
		} catch (IllegalArgumentException ex) {
			System.out.println(ex.getMessage());
		}
	}
}
