package br.com.gvendas.gestaovendas.exception;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/*
 * TODO: Tratamento de erros:  
 * 1 - MethodArgumentNotValid (X)
 * 2 - EmptyResultDataAccessException 
 */

@ControllerAdvice
public class GestaoVendasExceptionHandler extends ResponseEntityExceptionHandler{
	private static final String CONSTANT_VALIDATION_SIZE = "Size";
	private static final String CONSTANT_VALIDATION_NOT_BLANK = "NotBlank";
	private static final String CONSTANT_VALIDATION_NOT_NULL = "NotNull";
	private static final String CONSTANT_VALIDATION_PATTERN = "Pattern";
	private static final String CONSTANT_VALIDATION_LENGTH = "Length";
	private static final String CONSTANT_VALIDATION_MIN = "Min";
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<Error> erros = gerarListaErros(ex.getBindingResult());
		return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	private List<Error> gerarListaErros(BindingResult bindingResult) {
		List<Error> erros =new ArrayList<>();
		bindingResult.getFieldErrors().forEach(fe->{
			String msgUsuario = gerarErrosUsuario(fe);
			String msgDesenvolvedor = fe.toString();
			erros.add(new Error(msgUsuario,msgDesenvolvedor));
		});		
		return erros;}
	
	@ExceptionHandler(RegraNegocioException.class)
	public ResponseEntity<Object> handleRegraNegocioException(RegraNegocioException ex,
			WebRequest request) {
		String msgUsuario = ex.getMessage();
		String msgDesenvolvedor = ex.getMessage();
		List<Error> erro = Arrays.asList(new Error(msgUsuario,msgDesenvolvedor));
		return handleExceptionInternal(ex, erro, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ResponseEntity<Object> handleEmptyResultDataAccessException(
			EmptyResultDataAccessException ex, WebRequest request) {
		String msgUsuario = "Recurso não encontrado!!";
		String msgDesenvolvedor = ex.toString();
		List<Error> erros = Arrays.asList(new Error(msgUsuario,msgDesenvolvedor));
		return handleExceptionInternal(ex, erros, new HttpHeaders(), 
				HttpStatus.NOT_FOUND, request);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<Object> handleDataIntegrityViolationException(
			DataIntegrityViolationException ex, WebRequest request) {
		String msgUsuario = "Recurso não encontrado!";
		String msgDesenvolvedor = ex.toString();
		List<Error> erros = Arrays.asList(new Error(msgUsuario,msgDesenvolvedor));
		return handleExceptionInternal(ex, erros, new HttpHeaders(), 
				HttpStatus.BAD_REQUEST, request);
	}
	
	
	private String gerarErrosUsuario(FieldError fe) {
		if(fe.getCode().equals(CONSTANT_VALIDATION_NOT_BLANK)) {
			return fe.getDefaultMessage().concat(" é obrigatório");
		}
		if(fe.getCode().equals(CONSTANT_VALIDATION_SIZE)) {
			return fe.getDefaultMessage().concat(String.format(" é obrigatório "
					+ "ter entre %s e %s caracteres",
					fe.getArguments()[2],fe.getArguments()[1]));
		}
		if(fe.getCode().equals(CONSTANT_VALIDATION_NOT_NULL)) {
			return fe.getDefaultMessage().concat(" é obrigatório");
		}
		if(fe.getCode().equals(CONSTANT_VALIDATION_PATTERN)) {
			return fe.getDefaultMessage().concat(" está errado!");
		}
		if(fe.getCode().equals(CONSTANT_VALIDATION_LENGTH)) {
			return fe.getDefaultMessage().concat(
					String.format(" é obrigatório ter entre %s e %s caracteres",
							fe.getArguments()[2],fe.getArguments()[1]));
		}
		if(fe.getCode().equals(CONSTANT_VALIDATION_MIN)) {
			return fe.getDefaultMessage().concat(String.format(" é obrigatório"
					+ " ser maior ou igual a %s", fe.getArguments()[1]));
		}
		return fe.toString();}}
