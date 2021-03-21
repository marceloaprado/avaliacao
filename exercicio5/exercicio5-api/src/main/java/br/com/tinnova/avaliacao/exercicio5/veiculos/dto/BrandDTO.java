package br.com.tinnova.avaliacao.exercicio5.veiculos.dto;

import java.io.Serializable;

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
public class BrandDTO implements Serializable {
	
	private static final long serialVersionUID = -4147336419277465996L;

	@NotNull
	private Long id;
			
	@JsonProperty("marca")	
	private String brand;
}
