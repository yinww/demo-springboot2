package com.yinww.demo.springboot2.demo002.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER, ElementType.FIELD, ElementType.METHOD})
@Constraint(validatedBy=IDValidator.class)
public @interface IDConstraint {
	boolean required();
	
	String message();
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};
}
