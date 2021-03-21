package br.com.tinnova.avaliacao.exercicio5.veiculos.exceptionhandler;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Marcelo Alves Prado
 * 
 */
@Slf4j
@Controller
public class DefaultErrorController implements ErrorController {
	
	@RequestMapping("/error")
	@ResponseBody
	public ResponseEntity<?> handleError(HttpServletRequest request) {
		HttpStatus status = getStatus(request);
		Exception ex = (Exception) request.getAttribute("javax.servlet.error.exception");
		String message = getMessage(status, ex);
		
		Map<String, String> response = new HashMap<>();
		response.put("message", "Erro interno no servidor");
		response.put("detailedMessage", message);
		
		log.error("Erro interno: {}", message, ex);

		return ResponseEntity.status(status).body(response);
	}

	@Override
	public String getErrorPath() {
		return "/error";
	}

	private HttpStatus getStatus(HttpServletRequest request) {
		Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
		if (statusCode == null) {
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return HttpStatus.valueOf(statusCode);
	}
	
	private String getMessage(HttpStatus status, Exception ex) {
		String message = "";
		if(ex != null) {
			Throwable rootCause = ExceptionUtils.getRootCause(ex);
			if(rootCause != null)
				message = rootCause.getMessage();
		}
		
		if(message.isEmpty() && status != null) {
			switch(status) {
				case NOT_FOUND:
					message = "Recurso não encontrado";
				break;
				case UNAUTHORIZED:
					message = "Acesso não autorizado";
				break;
				case FORBIDDEN:
					message = "Acesso não permitido para este recurso";
				break;
				default:
					message = "Erro interno no servidor";
					break;
			}
		}
		
		return message;
	}
}
