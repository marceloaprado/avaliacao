package br.com.tinnova.avaliacao.exercicio5.veiculos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;

/**
 * 
 * @author Marcelo Alves Prado
 * 
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 186860191815866877L;

	@Getter
	private String message;

	public ResourceNotFoundException(String message) {
		super(message);
		this.message = message;
	}
}
