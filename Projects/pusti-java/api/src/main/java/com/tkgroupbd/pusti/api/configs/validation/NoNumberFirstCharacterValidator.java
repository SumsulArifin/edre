package com.tkgroupbd.pusti.api.configs.validation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
public class NoNumberFirstCharacterValidator implements ConstraintValidator<NoNumberFirstCharacter, String>{

    private String messageKey;
    private String defaultMessage; // Store the default message

    @Override
    public void initialize(NoNumberFirstCharacter constraintAnnotation) {
        this.messageKey = constraintAnnotation.messageKey();
        this.defaultMessage = constraintAnnotation.defaultMessage(); // Get the default message from the annotation
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // Check if the first character is a digit or a space followed by a digit
        if (value != null && !value.isEmpty()) {
            char firstChar = value.charAt(0);
            if (Character.isDigit(firstChar) || (firstChar == ' ' && value.length() > 1 && Character.isDigit(value.charAt(1)))) {
                context.disableDefaultConstraintViolation();

                if (messageKey.isEmpty()) {
                    context.buildConstraintViolationWithTemplate(defaultMessage).addConstraintViolation();
                } else {
                    context.buildConstraintViolationWithTemplate(messageKey).addConstraintViolation();
                }
                return false; // Invalid if the first character is a digit or a space followed by a digit
            }
        }
        return true; // Valid if the first character is not a digit or a space followed by a digit, or if the value is null or empty
    }


}
