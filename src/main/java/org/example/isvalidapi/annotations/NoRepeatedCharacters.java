package org.example.isvalidapi.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.example.isvalidapi.validators.NoRepeatedCharactersValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NoRepeatedCharactersValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NoRepeatedCharacters {
    String message() default NoRepeatedCharactersValidator.MESSAGE;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
