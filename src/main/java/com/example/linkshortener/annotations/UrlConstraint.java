package com.example.linkshortener.annotations;

import com.example.linkshortener.validators.UrlValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UrlValidator.class)
public @interface UrlConstraint {
    String message() default "Invalid URL";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
