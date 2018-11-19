package com.yinww.demo.springboot2.demo002.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinww.demo.springboot2.demo002.domain.ResultEntity;

@ControllerAdvice
public class TestExceptionHandler {

	private static final Logger log = LoggerFactory.getLogger(TestExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultEntity handleException(Exception e){
        log.error(e.getMessage(), e);

        BindingResult result = null;
        if (e instanceof MethodArgumentNotValidException){
            result = ((MethodArgumentNotValidException) e).getBindingResult();
        } else if (e instanceof BindException){
            result = ((BindException) e).getBindingResult();
        } else if (e instanceof ConstraintViolationException){
            Set<ConstraintViolation<?>> constraintViolations = ((ConstraintViolationException) e).getConstraintViolations();
            List<String> errors = new ArrayList<>();
            for (ConstraintViolation<?> violation : constraintViolations) {
            	errors.add(violation.getMessage());
            }
            return new ResultEntity(HttpStatus.OK.value(), errors);
        }
        
        if (result != null) {
        	List<String> errors = new ArrayList<>();
            for (ObjectError error : result.getAllErrors()) {
            	errors.add(error.getDefaultMessage());
            }
            return new ResultEntity(HttpStatus.BAD_REQUEST.value(), errors);
        }

        return new ResultEntity(HttpStatus.BAD_REQUEST.value());
    }
}
