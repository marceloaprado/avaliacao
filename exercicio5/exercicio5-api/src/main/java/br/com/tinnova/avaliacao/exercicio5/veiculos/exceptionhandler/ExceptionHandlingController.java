package br.com.tinnova.avaliacao.exercicio5.veiculos.exceptionhandler;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.tinnova.avaliacao.exercicio5.veiculos.exception.ResourceNotFoundException;
import br.com.tinnova.avaliacao.exercicio5.veiculos.exception.ValidationException;
import lombok.extern.slf4j.Slf4j;

/**
 * Controller para o tratamento de erros esperados pela aplicação
 * 
 * @author Marcelo Alves Prado
 * 
 */
@Slf4j
@ControllerAdvice
public class ExceptionHandlingController extends ResponseEntityExceptionHandler {
	
	
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler(ResourceNotFoundException.class)	
	@ResponseBody
	Map<String, String> handleNotFound(HttpServletRequest req, Exception ex) {
		Throwable rootCause = ExceptionUtils.getRootCause(ex);
		
		Map<String, String> response = new HashMap<>();
		response.put("message", "Recurso não encontrado");
		response.put("detailedMessage", rootCause.getMessage());
		
		log.error("Recurso não encontrado", ex);
		
		return response;
	}

	@ExceptionHandler(ValidationException.class)
	ResponseEntity<?> handleValidationException(HttpServletRequest req, Exception ex) {
		ValidationException e = (ValidationException) ex;
		
		Map<String, String> response = new HashMap<>();
		response.put("message", "Ocorreu um erro de validação.");
		response.put("detailedMessage", e.getMessage());
		
		log.error("Erro de validação", ex);
				
		return ResponseEntity.status(e.getStatusCode()).body(response);
	}
	
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseBody
	Map<String, String> handleIllegalArgumentException(HttpServletRequest req, Exception ex) {
		Throwable rootCause = ExceptionUtils.getRootCause(ex);
		
		Map<String, String> response = new HashMap<>();
		response.put("message", "Parâmetros inválidos");
		response.put("detailedMessage", rootCause.getMessage());
		
		log.error("Erro de parâmetros inválidos", ex);
		
		return response;
	}

	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(RuntimeException.class)
	@ResponseBody
	Map<String, String> handleInternalServerError(HttpServletRequest req, Exception ex) {
		Throwable rootCause = ExceptionUtils.getRootCause(ex);
		
		Map<String, String> response = new HashMap<>();
		response.put("message", "Ocorreu um erro interno no servidor");
		response.put("detailedMessage", rootCause.getMessage());
		
		log.error("Erro interno no servidor", ex);
		
		return response;
	}
	
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		Throwable rootCause = ExceptionUtils.getRootCause(ex);
		
		Map<String, String> response = new HashMap<>();
		response.put("message", "Ocorreu um erro interno no servidor");
		response.put("detailedMessage", rootCause.getMessage());
		
		log.error("Erro interno no servidor", ex);
		
		return ResponseEntity.status(status).body(response);
	}
}
