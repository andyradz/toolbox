package com.codigo.aplios.toolbox.core.identity;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PeselValidator.class)
public @interface PeselCheck {
	String message() default "Rating not in range";

	// Required by validation runtime
	Class<?>[] groups() default {};

	// Required by validation runtime
	Class<? extends Payload>[] payload() default {};
}
