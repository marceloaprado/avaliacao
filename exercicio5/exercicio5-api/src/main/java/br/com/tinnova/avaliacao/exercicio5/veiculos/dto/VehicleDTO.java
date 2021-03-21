package br.com.tinnova.avaliacao.exercicio5.veiculos.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Marcelo Alves Prado
 * 
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDTO {
	
	private Long id;
	
	@JsonProperty("veiculo")
	@NotEmpty
	private String vehicle;
	
	@JsonProperty("marca")
	@NotNull
	private BrandDTO brand;
	
	@JsonProperty("ano")
	@NotNull
	private Integer year;
	
	@JsonProperty("descricao")
	@NotEmpty
	private String description;
	
	@JsonProperty("vendido")
	@NotNull
	private Boolean isSold;
	
	private LocalDateTime created;
	
	private LocalDateTime updated;
}
