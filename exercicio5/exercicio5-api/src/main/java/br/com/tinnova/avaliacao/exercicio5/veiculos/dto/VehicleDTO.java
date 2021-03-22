package br.com.tinnova.avaliacao.exercicio5.veiculos.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * Classe DTO para a entidade Veículo
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
	
	@ApiParam("Nome do veículo") 
	@JsonProperty("veiculo")
	@NotEmpty
	private String vehicle;
	
	@ApiParam("Marca do veículo")
	@JsonProperty("marca")
	@NotNull
	private BrandDTO brand;
	
	@ApiParam("Ano de fabricação do veículo")
	@JsonProperty("ano")
	@NotNull
	private Integer year;
	
	@ApiParam("Descrição do veículo")
	@JsonProperty("descricao")
	@NotEmpty
	private String description;
	
	@ApiParam("Flag de veículo vendido")
	@JsonProperty("vendido")
	@NotNull
	private Boolean isSold;
	
	private LocalDateTime created;
	
	private LocalDateTime updated;
}
