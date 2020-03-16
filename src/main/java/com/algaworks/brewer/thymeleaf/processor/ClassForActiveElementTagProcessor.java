package com.algaworks.brewer.thymeleaf.processor;

import javax.servlet.http.HttpServletRequest;

import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.context.IWebContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractAttributeTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.templatemode.TemplateMode;

public class ClassForActiveElementTagProcessor extends AbstractAttributeTagProcessor {

	private static final String NOME_ATRIBUTO = "active";
	private static final int PRECEDENCIA = 1000;
	
	public ClassForActiveElementTagProcessor(String dialectPreffix) {
		super(TemplateMode.HTML, dialectPreffix, null, false, NOME_ATRIBUTO, true, PRECEDENCIA , true);
	}

	@Override
	protected void doProcess(ITemplateContext context, IProcessableElementTag tag, AttributeName attributeName,
			String attributeValue, IElementTagStructureHandler structureHandler) {
		
		String menu = attributeValue;
		HttpServletRequest request = ((IWebContext) context).getRequest();
		String uri = request.getRequestURI();
		
		if(uri.contains(menu)) {
			String classes = tag.getAttributeValue("class");
			structureHandler.setAttribute("class", classes.concat(" ").concat("is-active"));
		}
	}

}
