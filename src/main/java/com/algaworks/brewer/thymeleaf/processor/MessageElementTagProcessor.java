package com.algaworks.brewer.thymeleaf.processor;

import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.model.IModel;
import org.thymeleaf.model.IModelFactory;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractElementTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.templatemode.TemplateMode;

public class MessageElementTagProcessor extends AbstractElementTagProcessor {
	
	private static final String TAG_NAME = "message";
	private static final int PRECEDENTE = 1000;
	
	public MessageElementTagProcessor(String dialectPreffix) {
		super(TemplateMode.HTML, dialectPreffix, TAG_NAME, true, null, false, PRECEDENTE);
	}

	@Override
	protected void doProcess(ITemplateContext context, IProcessableElementTag tag,
			IElementTagStructureHandler structureHandler) {
		
		IModelFactory modelFactory = context.getModelFactory();
		
		IModel model = modelFactory.createModel();		
		model.add(modelFactory.createStandaloneElementTag("th:block", "th:replace", "layout/fragments/mensagem-sucesso :: alert"));
		model.add(modelFactory.createStandaloneElementTag("th:block", "th:replace", "layout/fragments/mensagem-erro-validacao :: alert"));
		
		structureHandler.replaceWith(model, true);
	}
	
	
}
