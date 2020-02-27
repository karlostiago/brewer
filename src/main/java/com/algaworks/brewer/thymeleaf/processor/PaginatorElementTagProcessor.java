package com.algaworks.brewer.thymeleaf.processor;

import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.model.IAttribute;
import org.thymeleaf.model.IModel;
import org.thymeleaf.model.IModelFactory;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractElementTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.templatemode.TemplateMode;

public class PaginatorElementTagProcessor extends AbstractElementTagProcessor {
	
	private static final String TAG_NAME = "paginator";
	private static final int PRECEDENTE = 1000;
	
	public PaginatorElementTagProcessor(String dialectPreffix) {
		super(TemplateMode.HTML, dialectPreffix, TAG_NAME, true, null, false, PRECEDENTE);
	}

	@Override
	protected void doProcess(ITemplateContext context, IProcessableElementTag tag,
			IElementTagStructureHandler structureHandler) {
		
		IModelFactory modelFactory = context.getModelFactory();
		
		IAttribute page = tag.getAttribute("page");
		
		IModel model = modelFactory.createModel();		
		model.add(modelFactory.createStandaloneElementTag("th:block"
				, "th:replace", String.format("layout/fragments/paginator :: paginator(${%s})", page.getValue())));
		
		structureHandler.replaceWith(model, true);
	}
}
