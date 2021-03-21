package br.com.tinnova.avaliacao.exercicio5.veiculos;

import java.time.Duration;
import java.util.Arrays;
import java.util.Locale;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 
 * @author Marcelo Alves Prado
 * 
 */
@ComponentScan(basePackages = {"br.com.tinnova.avaliacao.exercicio5"})
@SpringBootApplication
public class Application extends SpringBootServletInitializer {

	public static void main(String[] args) {
		Locale.setDefault(new Locale("pt", "BR"));
        TimeZone.setDefault(TimeZone.getTimeZone("America/Sao_Paulo"));
		SpringApplication.run(Application.class, args);		
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(Application.class);
	}

    /**
     * Configuração das propriedades de desserialização JSON do Jackson Provider.
     *
     * @return Jackson2ObjectMapperBuilderCustomizer
     */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jacksonObjectMapperCustomization() {
        return jacksonObjectMapperBuilder -> jacksonObjectMapperBuilder.timeZone(TimeZone.getDefault());
    }

    /**
     * Parâmetro que define o timeout da conexão com host
     */
    @Value("${rest.connect-timeout:1}")
    private int restConnectTimeout;

    /**
     * Parâmetro que define o timeout da requisição
     */
    @Value("${rest.read-timeout:35}")
    private int restReadTimeout;

    /**
     * Configuração das propriedades das requisições REST
     *
     * @param restTemplateBuilder
     * @return RestTemplate
     */
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder //
                .setConnectTimeout(Duration.ofSeconds(restConnectTimeout))
                .setReadTimeout(Duration.ofSeconds(restReadTimeout))
                .build();
    }   
    
    /**
     * Configuração das propriedades do Cors
     *
     * @return CorsFilter
     */
    @Bean
	public CorsFilter corsFilter() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		final CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "PATCH"));
		config.addAllowedHeader("*");
		config.addAllowedOriginPattern("http://localhost*");		
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}
}