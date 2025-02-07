package org.example.isvalidapi.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.example.isvalidapi.validators.ContainsUppercaseValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ContainsUppercaseValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ContainsUppercase {
    String message() default ContainsUppercaseValidator.MESSAGE;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
