package com.example.linkshortener.validators;

import com.example.linkshortener.annotations.UrlConstraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UrlValidator implements ConstraintValidator<UrlConstraint, String> {

    @Override
    public void initialize(UrlConstraint constraintAnnotation) {
    }

    @Override
    public boolean isValid(String link, ConstraintValidatorContext ctx) {
        return link.matches("^http(s)?://.*$");
    }
}
