package com.tkgroupbd.pusti.api.configs.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NoSpecialCharacterAcceptValidation implements ConstraintValidator<NoSpecialCharacterAccept, String> {
    private String defaultMessage;

    @Override
    public void initialize(NoSpecialCharacterAccept constraintAnnotation) {
        this.defaultMessage = constraintAnnotation.defaultMessage();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (value == null || value.isEmpty()){
            return true;
        }
        for (char c : value.toCharArray()){
            if(!Character.isLetterOrDigit(c) && c != '-' && c!='(' && c!=')' && c!='.' && c!=' ')  {
                addConstraintViolation(context, defaultMessage);
                return false;
            }
        }
        return true;
    }

    private  void addConstraintViolation(ConstraintValidatorContext context, String message){
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
    }
}
