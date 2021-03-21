package br.com.tinnova.avaliacao.exercicio5.veiculos.payload.request;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Marcelo Alves Prado
 * 
 */
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FindVehicleFilterRequest {

	private String vehicle;

	private String brand;

	private Integer year;

	private String description;

	private Boolean isSold;
		
	private LocalDate startCreatedInterval;	
	
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
