package org.example.isvalidapi.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.example.isvalidapi.validators.MinLengthValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MinLengthValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface MinLength {
    String message() default MinLengthValidator.MESSAGE;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    int value() default 9;
}

