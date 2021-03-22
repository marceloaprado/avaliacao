package br.com.tinnova.avaliacao.exercicio5.veiculos.payload.request;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Filtro de busca personalizado para consultar veículos
 * 
 * @author Marcelo Alves Prado
 * 
 */
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FindVehicleFilterRequest {

	@ApiParam("Nome do veículo")
	private String vehicle;

	@ApiParam("Marca do veículo")
	private String brand;

	@ApiParam("Ano de fabricação do veículo")
	private Integer year;

	@ApiParam("Descrição do veículo")
	private String description;

	@ApiParam("Flag de veículo vendido")
	private Boolean isSold;
		
	@ApiParam("Inicío do intervalo de data de cadastro do veículo no sistema")
	private LocalDate startCreatedInterval;	
	
	@ApiParam("Final do intervalo de data de cadastro do veículo no sistema")
	private LocalDate endCreatedInterval;
	
	public void setVeiculo(String vehicle) {
		this.vehicle = vehicle;
	}
	
	public void setMarca(String brand) {
		this.brand = brand;
	}
	
	public void setDescricao(String description) {
		this.description = description;
	}
	
	public void setVendido(Boolean isSold) {
		this.isSold = isSold;
	}
	
	public void setAno(Integer year) {
		this.year = year;
	}
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public void setInicioIntervaloCriacao(LocalDate startCreatedInterval) {
		this.startCreatedInterval = startCreatedInterval;
	}
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public void setFimIntervaloCriacao(LocalDate endCreatedInterval) {
		this.endCreatedInterval = endCreatedInterval;
	}
}
