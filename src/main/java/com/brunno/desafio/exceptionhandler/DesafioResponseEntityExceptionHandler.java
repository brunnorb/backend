package com.brunno.desafio.exceptionhandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.brunno.desafio.services.exception.ClienteJaCadastradoException;
import com.brunno.desafio.services.exception.ContratoJaCadastradoException;
import com.brunno.desafio.services.exception.CpfOuCnpjInvalidoException;
import com.brunno.desafio.services.exception.DataInicioMaiorException;
import com.brunno.desafio.services.exception.EmpresaInexistenteException;
import com.brunno.desafio.services.exception.ServicoJaCadastradoException;
import com.brunno.desafio.services.exception.TipoClienteInexistenteException;

@ControllerAdvice
public class DesafioResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;

	private List<Erro> criarErros(BindingResult bindingResult) {
		List<Erro> erros = new ArrayList<>();

		for (FieldError fieldError : bindingResult.getFieldErrors()) {
			String mensagem = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
			String mensagemDev = fieldError.toString();
			erros.add(new Erro(mensagem, mensagemDev));
		}
		return erros;
	}

	
	@ExceptionHandler({ ContratoJaCadastradoException.class })
	public ResponseEntity<Object> handleContratoJaCadastradoException(ContratoJaCadastradoException ex) {
		String mensagem = messageSource.getMessage("contrato.jacadastrado", null, LocaleContextHolder.getLocale());
		String mensagemDev = ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(mensagem, mensagemDev));
		return ResponseEntity.badRequest().body(erros);
	}
	
	
	@ExceptionHandler({ DataInicioMaiorException.class })
	public ResponseEntity<Object> handleDataInicioMaiorException(DataInicioMaiorException ex) {
		String mensagem = messageSource.getMessage("contrato.datainimaior", null, LocaleContextHolder.getLocale());
		String mensagemDev = ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(mensagem, mensagemDev));
		return ResponseEntity.badRequest().body(erros);
	}

	
	@ExceptionHandler({ CpfOuCnpjInvalidoException.class })
	public ResponseEntity<Object> handleCpfOuCnpjInvalidoException(CpfOuCnpjInvalidoException ex) {
		String mensagem = messageSource.getMessage("cliente.cpfcnpjincorreto", null, LocaleContextHolder.getLocale());
		String mensagemDev = ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(mensagem, mensagemDev));
		return ResponseEntity.badRequest().body(erros);
	}

	
	@ExceptionHandler({ ClienteJaCadastradoException.class })
	public ResponseEntity<Object> handleClienteJaCadastradoException(ClienteJaCadastradoException ex) {
		String mensagem = messageSource.getMessage("cliente.jacadastrado", null, LocaleContextHolder.getLocale());
		String mensagemDev = ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(mensagem, mensagemDev));
		return ResponseEntity.badRequest().body(erros);
	}
	
	@ExceptionHandler({ ServicoJaCadastradoException.class })
	public ResponseEntity<Object> handleServicoJaCadastradoException(ServicoJaCadastradoException ex) {
		String mensagem = messageSource.getMessage("servico.jacadastrado", null, LocaleContextHolder.getLocale());
		String mensagemDev = ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(mensagem, mensagemDev));
		return ResponseEntity.badRequest().body(erros);
	}

	
	@ExceptionHandler({ TipoClienteInexistenteException.class })
	public ResponseEntity<Object> handleTipoClienteInexistenteException(TipoClienteInexistenteException ex) {
		String mensagem = messageSource.getMessage("tipocliente.inexistente", null, LocaleContextHolder.getLocale());
		String mensagemDev = ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(mensagem, mensagemDev));
		return ResponseEntity.badRequest().body(erros);
	}
	
	@ExceptionHandler({ EmpresaInexistenteException.class })
	public ResponseEntity<Object> handleEmpresaInexistenteException(EmpresaInexistenteException ex) {
		String mensagem = messageSource.getMessage("empresa.inexistente", null, LocaleContextHolder.getLocale());
		String mensagemDev = ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(mensagem, mensagemDev));
		return ResponseEntity.badRequest().body(erros);
	}
	
	

	@ExceptionHandler({ EmptyResultDataAccessException.class })
	public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex,
			WebRequest request) {
		String mensagem = messageSource.getMessage("recurso.nao-encontrado", null, LocaleContextHolder.getLocale());
		String mensagemDev = ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(mensagem, mensagemDev));
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		String mensagem = messageSource.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale());
		String mensagemDev = ex.getCause() != null ? ex.getCause().toString() : ex.toString();

		List<Erro> erros = Arrays.asList(new Erro(mensagem, mensagemDev));

		return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<Erro> erros = criarErros(ex.getBindingResult());
		return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
	}
	
}
