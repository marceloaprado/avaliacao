package br.com.tinnova.avaliacao.exercicio5.veiculos.config.datasource;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import br.com.tinnova.avaliacao.exercicio5.veiculos.config.ApplicationDatabaseConfig;

/**
 * Classe com as configurações de conexão com o banco de dados e propriedades de
 * persistência dos dados
 * 
 * @author Marcelo Alves Prado
 * 
 */
@Configuration
@EnableTransactionManagement
@EnableJpaAuditing
@EnableJpaRepositories(//
		basePackages = "br.com.tinnova.avaliacao.exercicio5.veiculos.db", //
		entityManagerFactoryRef = "applicationEntityManager", //
		transactionManagerRef = "applicationTransactionManager")
public class ApplicationDataSourceConfig extends HikariConfig {

	@Autowired
	private ApplicationDatabaseConfig databaseConfig;

	/**
	 * Factory para criação do Data Source
	 *
	 * @return
	 */
	@Primary
	@Bean(name = "applicationDataSource")
	public DataSource dataSource() {
		this.setJdbcUrl(databaseConfig.getJdbcUrl());
		this.setDriverClassName(databaseConfig.getDriverClassName());
		return new HikariDataSource(this);
	}

	/**
	 * Factory para criação do Entity Manager
	 *
	 * @param builder
	 * @return
	 */
	@Primary
	@PersistenceContext(unitName = "applicationPersistenceContext")
	@Bean(name = "applicationEntityManager")
	public LocalContainerEntityManagerFactoryBean applicationEntityManager(EntityManagerFactoryBuilder builder) {
		return builder.dataSource(dataSource()) //
				.persistenceUnit("applicationPersistenceContext") //
				.packages("br.com.tinnova.avaliacao.exercicio5.veiculos.db.entity") //
				.build();
	}

	/**
	 * Factory para criação do Transaction Manager
	 *
	 * @param entity manager
	 * @return
	 */
	@Primary
	@Bean(name = "applicationTransactionManager")
	public PlatformTransactionManager applicationTransactionManager(
			@Qualifier("applicationEntityManager") EntityManagerFactory em) {
		return new JpaTransactionManager(em);
	}
}