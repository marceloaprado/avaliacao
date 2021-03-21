package br.com.tinnova.avaliacao.exercicio5.veiculos.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Marcelo Alves Prado
 * 
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class ValidationException extends RuntimeException {

	private static final long serialVersionUID = 3473094129626736298L;
	
	@Getter
	private int statusCode = HttpStatus.BAD_REQUEST.value();

	@Getter
	private String message;

	public ValidationException(String message) {
		super();
		this.message = message;
	}

	public ValidationException(Throwable t, String message) {
		super(t);
		this.message = message;
	}

	public ValidationException(Throwable t, int statusCode, String message) {
		super(t);
		this.statusCode = statusCode;
		this.message = message;
	}
}
