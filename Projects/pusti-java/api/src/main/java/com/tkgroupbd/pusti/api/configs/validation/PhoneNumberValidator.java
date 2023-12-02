package com.tkgroupbd.pusti.api.configs.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<ValidPhoneNumber, String> {
    private int minSize;
    private int maxSize;

    @Override
    public void initialize(ValidPhoneNumber constraintAnnotation) {
        this.minSize = constraintAnnotation.minSize();
        this.maxSize = constraintAnnotation.maxSize();
    }

    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext context) {
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            return false; // null values are considered valid
        }
        phoneNumber = phoneNumber.replaceAll("\\s+", "");
        return (phoneNumber.startsWith("+880") &&
                (phoneNumber.length() == (maxSize) && phoneNumber.length()>=(minSize)))
                || (phoneNumber.startsWith("0") &&
                (phoneNumber.length() == (maxSize-3) && phoneNumber.length()>=minSize));


        //phoneNumber = phoneNumber.replace("-", "");
//        // Check if the first character is '+' or '0'
//        if (phoneNumber.startsWith("+") || phoneNumber.startsWith("0")) {
//            phoneNumber = phoneNumber.substring(1);
//            // Check if the remaining characters are numeric and within dynamic size limits
//            return phoneNumber.matches("[0-9]+") && phoneNumber.length() >= minSize && phoneNumber.length() <= maxSize;
//        } else {
//            return false;
//        }
    }
}


