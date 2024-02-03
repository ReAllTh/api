package link.reallth.api.annotation;

import jakarta.validation.Constraint;
import link.reallth.api.constant.enums.ROLES;
import link.reallth.api.validator.AuthValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * for validate user role
 *
 * @author ReAllTh
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AuthValidator.class)
public @interface RequireRole {
    ROLES role() default ROLES.DEFAULT;
}
