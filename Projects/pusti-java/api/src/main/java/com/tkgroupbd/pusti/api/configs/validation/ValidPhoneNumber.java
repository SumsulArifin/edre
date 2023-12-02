package com.tkgroupbd.pusti.api.configs.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PhoneNumberValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPhoneNumber {
    String message() default "Invalid phone number";
    int minSize() default 5; // Default minimum size
    int maxSize() default 20; // Default maximum size

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
