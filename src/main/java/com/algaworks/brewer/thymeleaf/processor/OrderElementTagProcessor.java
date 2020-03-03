package com.algaworks.brewer.thymeleaf.processor;

import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.model.IAttribute;
import org.thymeleaf.model.IModel;
import org.thymeleaf.model.IModelFactory;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractElementTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.templatemode.TemplateMode;

public class OrderElementTagProcessor extends AbstractElementTagProcessor {
	
	private static final String TAG_NAME = "order";
	private static final int PRECEDENTE = 1000;
	
	public OrderElementTagProcessor(String dialectPreffix) {
		super(TemplateMode.HTML, dialectPreffix, TAG_NAME, true, null, false, PRECEDENTE);
	}

	@Override
	protected void doProcess(ITemplateContext context, IProcessableElementTag tag,
			IElementTagStructureHandler structureHandler) {
		
		IModelFactory modelFactory = context.getModelFactory();
		
		IAttribute page = tag.getAttribute("page");
		IAttribute field = tag.getAttribute("field");
		IAttribute text = tag.getAttribute("text");
		
		IModel model = modelFactory.createModel();		
		model.add(modelFactory.createStandaloneElementTag("th:block"
				, "th:replace"
				, String.format("layout/fragments/order :: order(${%s}, %s, '%s')", page.getValue(), field.getValue(), text.getValue())));
		
		structureHandler.replaceWith(model, true);
	}
}
