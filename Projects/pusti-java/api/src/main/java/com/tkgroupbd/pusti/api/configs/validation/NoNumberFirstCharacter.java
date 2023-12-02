package com.tkgroupbd.pusti.api.configs.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NoNumberFirstCharacterValidator.class)
public @interface NoNumberFirstCharacter {
    String message() default "{NoNumberFirstCharacter.default}";

    String messageKey() default ""; // Custom message key attribute

    String defaultMessage() default "Field cannot start with a number"; // Default message

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
