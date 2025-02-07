package org.example.isvalidapi.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.example.isvalidapi.validators.ContainsDigitValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ContainsDigitValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ContainsDigit {
    String message() default ContainsDigitValidator.MESSAGE;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
