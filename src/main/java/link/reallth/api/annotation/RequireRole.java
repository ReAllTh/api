package link.reallth.api.annotation;

import link.reallth.api.constant.enums.ROLES;

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
public @interface RequireRole {
    ROLES role() default ROLES.DEFAULT;
}
