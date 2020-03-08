package com.algaworks.brewer.validation.validator;

import java.util.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintValidatorContext.ConstraintViolationBuilder;

import org.apache.commons.beanutils.BeanUtils;

import com.algaworks.brewer.validation.ValidadorDeSenha;

public class ConfirmarSenhaValidator implements ConstraintValidator<ValidadorDeSenha, Object> {
	
	private String password;
	private String confirmation;
	
	@Override
	public void initialize(ValidadorDeSenha constraintAnnotation) {
		this.password = constraintAnnotation.password();
		this.confirmation = constraintAnnotation.confirmation();
	}

	@Override
	public boolean isValid(Object object, ConstraintValidatorContext context) {
		
		boolean valid = false;
		
		try {
			Object password = BeanUtils.getProperty(object, this.password);
			Object confirmation = BeanUtils.getProperty(object, this.confirmation);
			
			valid = isNull(password, confirmation) || isEguals(password, confirmation);
		} catch (Exception e) {
			throw new RuntimeException("Erro ao recuperar os valores de senha e confirmação de senha. " + e.getMessage());
		}
		
		if(!valid) {
			context.disableDefaultConstraintViolation();
			String message = context.getDefaultConstraintMessageTemplate();
			ConstraintViolationBuilder violationBuilder = context.buildConstraintViolationWithTemplate(message);
			violationBuilder.addPropertyNode(this.confirmation).addConstraintViolation();
		}
		
		return valid;
	}

	private boolean isEguals(Object password, Object confirmation) {
		return Objects.nonNull(password) && password.equals(confirmation);
	}

	private boolean isNull(Object password, Object confirmation) {
		return Objects.isNull(password) && Objects.isNull(confirmation);
	}
	
}
