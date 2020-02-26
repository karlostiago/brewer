package com.algaworks.brewer.thymeleaf.processor;

import org.thymeleaf.context.ITemplateContext;

import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractAttributeTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.spring5.util.FieldUtils;
import org.thymeleaf.templatemode.TemplateMode;

public class ClassForErrorAttributeTagProcessor extends AbstractAttributeTagProcessor {

	private static final String NOME_ATRIBUTO = "haserror";
	private static final int PRECEDENCIA = 1000;
	
	public ClassForErrorAttributeTagProcessor(String dialectPreffix) {
		super(TemplateMode.HTML, dialectPreffix, null, false, NOME_ATRIBUTO, true, PRECEDENCIA , true);
	}

	@Override
	protected void doProcess(ITemplateContext context, IProcessableElementTag tag, AttributeName attributeName,
			String attributeValue, IElementTagStructureHandler structureHandler) {
		
		String field = attributeValue;
		
		boolean temError = FieldUtils.hasErrors(context, field);
		
		if(temError) {
			String classes = tag.getAttributeValue("class");
			structureHandler.setAttribute("class", classes.concat(" ").concat("has-error"));
		}
	}
}
