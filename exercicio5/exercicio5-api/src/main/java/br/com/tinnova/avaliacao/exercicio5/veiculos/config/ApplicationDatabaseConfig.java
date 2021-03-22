package br.com.tinnova.avaliacao.exercicio5.veiculos.config;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Classe de propriedades de configuração do banco de dados.
 * 
 * @author Marcelo Alves Prado
 * 
 */
@Getter
@NoArgsConstructor
@Component
public class ApplicationDatabaseConfig implements Serializable {
	private static final long serialVersionUID = 1394672915875224006L;

	@Value("${app.datasource.jdbcUrl}")
	private String jdbcUrl;

	@Value("${app.datasource.driverClassName}")
	private String driverClassName;
}
