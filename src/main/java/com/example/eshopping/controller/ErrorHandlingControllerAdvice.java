package com.example.eshopping.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.eshopping.model.validation.ValidationErrorResponse;
import com.example.eshopping.model.validation.Violation;

@ControllerAdvice
class ErrorHandlingControllerAdvice {
	


  @ExceptionHandler(ConstraintViolationException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  ValidationErrorResponse onConstraintValidationException(ConstraintViolationException e) {
	    ValidationErrorResponse error = new ValidationErrorResponse();
	    List<Violation> violationList = new ArrayList<>();
	    for (ConstraintViolation violation : e.getConstraintViolations()) {
	    	Violation violations = new Violation();
	    	violations.setFieldName(violation.getPropertyPath().toString());
	    	violations.setMessage(violation.getMessage());
	    	violationList.add(violations);
	    }
	    error.setViolations(violationList);
	    return error;
	}

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  ValidationErrorResponse onMethodArgumentNotValidException(
      MethodArgumentNotValidException e) {
	  List<Violation> violationList = new ArrayList<>();
    ValidationErrorResponse error = new ValidationErrorResponse();
    for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
    	Violation violation = new Violation();
    	violation.setFieldName(fieldError.getField());
    	violation.setMessage(fieldError.getDefaultMessage());
    	violationList.add(violation);
    }
    error.setViolations(violationList);
    return error;
  }

}
