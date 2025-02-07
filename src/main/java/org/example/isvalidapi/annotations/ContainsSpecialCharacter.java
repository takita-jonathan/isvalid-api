package org.example.isvalidapi.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.example.isvalidapi.validators.ContainsSpecialCharacterValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ContainsSpecialCharacterValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ContainsSpecialCharacter {
    String message() default ContainsSpecialCharacterValidator.MESSAGE;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
