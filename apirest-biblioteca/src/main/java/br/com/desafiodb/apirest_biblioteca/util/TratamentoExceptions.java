package br.com.desafiodb.apirest_biblioteca.util;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class TratamentoExceptions {

    @ExceptionHandler(RegraNegocioException.class)
    public ResponseEntity<Object> handlerRegraNegocioException(RegraNegocioException exception, WebRequest request) {
        Map<String, Object> response = new HashMap<String, Object>();
        response.put("dataHora", LocalDateTime.now());
        response.put("mensagem", exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
