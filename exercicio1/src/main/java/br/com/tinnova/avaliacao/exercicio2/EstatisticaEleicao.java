package br.com.tinnova.avaliacao.exercicio2;

/**
 * Classe responsavel por calcular as estatisticas de uma eleicao baseados no
 * seu consolidado de votos.
 * 
 * Por favor, veja a classe {@link br.com.tinnova.avaliacao.exercicio2.ConsolidadoEleicao} que compoe o consolidado
 * 
 * @author Marcelo Prado
 * 
 */
public class EstatisticaEleicao {
	
	/**
	 * O consolidado de votos da elei��o
	 */
	private ConsolidadoEleicao consolidadoEleicao;

	public EstatisticaEleicao(ConsolidadoEleicao consolidadoEleicao){
		this.consolidadoEleicao = consolidadoEleicao;
	}
	
	public ConsolidadoEleicao getConsolidadoEleicao() {
		return this.consolidadoEleicao;
	}
	
	/**
	 * <p>Calcula o percentual de votos v�lidos da elei��o</p>
	 *  
	 * @return o percentual de votos v�lidos
	 */
	public float calcularPercentualVotosValidos() {		
		return (float) consolidadoEleicao.getVotosValidos() / (float) consolidadoEleicao.getTotalEleitores();
	}

	/**
	 * <p>Calcula o percentual de votos em branco da elei��o</p>
	 *  
	 * @return o percentual de votos em branco
	 */
	public float calcularPercentualVotosBrancos() {
		return (float) consolidadoEleicao.getVotosBrancos() / (float) consolidadoEleicao.getTotalEleitores();
	}

	/**
	 * <p>Calcula o percentual de votos nulos da elei��o</p>
	 *  
	 * @return o percentual de votos nulos
	 */
	public float calcularPercentualVotosNulos() {
		return (float) consolidadoEleicao.getVotosNulos() / (float) consolidadoEleicao.getTotalEleitores();
	}
}
