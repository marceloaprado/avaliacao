package br.com.tinnova.avaliacao.exercicio2;

/**
 * Classe que representa o consolidado de votos da eleicao
 *
 * 
 * @author Marcelo Prado
 * 
 */
public class ConsolidadoEleicao {
	
	/**
	 * O total de eleitores que participaram da votacao
	 */
	private int totalEleitores;	
	
	/**
	 * O total de votos validos da eleicao
	 */
	private int votosValidos;
	
	/**
	 * O total de votos brancos da eleicao
	 */
	private int votosBrancos;
	
	/**
	 * O total de votos nulos da eleicao
	 */
	private int votosNulos;
	
	public ConsolidadoEleicao(int totalEleitores, int votosValidos, int votosBrancos, int votosNulos) throws IllegalArgumentException {
		if(votosValidos < 0 || votosBrancos < 0 || votosNulos < 0)
			throw new IllegalArgumentException("Nao e possivel consolidar uma eleicao com quantidade negativa de votos.");
		
		if(totalEleitores <= 0 || totalEleitores != (votosValidos + votosBrancos + votosNulos))
			throw new IllegalArgumentException("As quantidades de votos informados estao inconsistentes com o total de eleitores.");
		
		this.totalEleitores = totalEleitores;
		this.votosValidos = votosValidos;
		this.votosBrancos = votosBrancos;
		this.votosNulos = votosNulos;
	}
	
	public int getTotalEleitores() {
		return totalEleitores;
	}

	public int getVotosValidos() {
		return votosValidos;
	}

	public int getVotosBrancos() {
		return votosBrancos;
	}

	public int getVotosNulos() {
		return votosNulos;
	}
}
