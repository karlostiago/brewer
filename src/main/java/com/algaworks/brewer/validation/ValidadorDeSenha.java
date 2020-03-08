package com.algaworks.brewer.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.OverridesAttribute;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;

import com.algaworks.brewer.validation.validator.ConfirmarSenhaValidator;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { ConfirmarSenhaValidator.class })
public @interface ValidadorDeSenha {
	
	@OverridesAttribute(constraint = Pattern.class, name = "message")
	String message() default "A senha e confirmação de senha, estão diferentes.";
	
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
	
	String password();
	String confirmation();
}
