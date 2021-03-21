package br.com.tinnova.avaliacao.exercicio5.veiculos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 
 * @author Marcelo Alves Prado
 * 
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerException extends RuntimeException {
	private static final long serialVersionUID = -3967211814799882461L;

	public InternalServerException(String message) {
        super(message);
    }
}
