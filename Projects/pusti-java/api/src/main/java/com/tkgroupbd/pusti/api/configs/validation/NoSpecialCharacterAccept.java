package com.tkgroupbd.pusti.api.configs.validation;

import jakarta.validation.Payload;
import jakarta.validation.Constraint;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NoSpecialCharacterAcceptValidation.class)
public @interface NoSpecialCharacterAccept {
    String message() default "Special Character not acceptable";
    String defaultMessage() default "Special Character not acceptable";
    Class<?>[] groups() default{};
    Class<?extends Payload>[] payload() default {};
}
