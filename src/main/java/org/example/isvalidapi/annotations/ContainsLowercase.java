package org.example.isvalidapi.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.example.isvalidapi.validators.ContainsLowercaseValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ContainsLowercaseValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ContainsLowercase {
    String message() default ContainsLowercaseValidator.MESSAGE;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
