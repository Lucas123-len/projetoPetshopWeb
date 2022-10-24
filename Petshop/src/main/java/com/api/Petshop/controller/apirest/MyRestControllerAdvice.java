package com.api.Petshop.controller.apirest;
import com.api.Petshop.exception.Error;
import com.api.Petshop.exception.NotFoundException;
import com.api.Petshop.exception.PropertyError;
import com.api.Petshop.exception.ValidationError;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(annotations = RestController.class)
public class MyRestControllerAdvice {
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ConstraintViolationException> erroValidacao(ConstraintViolationException e, HttpServletRequest request) {
		ValidationError er = new ValidationError(Calendar.getInstance(),HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND.name(), "Erro de Validação",request.getRequestURI());
		for(ConstraintViolation<?> cv : e.getConstraintViolations()) {
			PropertyError p = new PropertyError(cv.getPropertyPath().toString(), cv.getMessage());
			er.getErrors().add(p);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MethodArgumentNotValidException> erroValidacao(MethodArgumentNotValidException e, HttpServletRequest request) {
		ValidationError er = new ValidationError(Calendar.getInstance(),HttpStatus.UNPROCESSABLE_ENTITY.value(),HttpStatus.UNPROCESSABLE_ENTITY.name(), "Erro de Validação",request.getRequestURI());
		for(FieldError fe : e.getBindingResult().getFieldErrors()) {
			PropertyError p = new PropertyError(fe.getField(), fe.getDefaultMessage());
			er.getErrors().add(p);
		}
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e);
	}
	
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<NotFoundException> erroPadrao(NotFoundException e, HttpServletRequest request) {
		Error er = new Error(Calendar.getInstance(),HttpStatus.BAD_REQUEST.value(),HttpStatus.BAD_REQUEST.name(), e.getMessage(),request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Exception> erroPadrao(Exception e, HttpServletRequest request) {
		Error er = new Error(Calendar.getInstance(),HttpStatus.BAD_REQUEST.value(),HttpStatus.BAD_REQUEST.name(), e.getMessage(),request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
	}
}
