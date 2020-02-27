package com.algaworks.brewer.controller.page;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

public class PageWrapper<T> {
	
	private Page<T> page;
	private UriComponentsBuilder uriComponentsBuilder;
	
	public PageWrapper(Page<T> page, HttpServletRequest request) {
		this.page = page;
		this.uriComponentsBuilder = ServletUriComponentsBuilder.fromRequest(request); 
	}
	
	public List<T> getContent() {
		return page.getContent();
	}
	
	public boolean isEmpty() {
		return page.getContent().isEmpty();
	}
	
	public boolean isNotEmpty() {
		return !isEmpty();
	}
	
	public int getCurrent() {
		return page.getNumber();
	}
	
	public boolean isFirst() {
		return page.isFirst();
	}
	
	public boolean isLast() {
		return page.isLast();
	}
	
	public int getTotal() {
		return page.getTotalPages();
	}
	
	public String urlForPage(int pagina) {
		return this.uriComponentsBuilder.replaceQueryParam("page", pagina).build(true).encode().toUriString();
	}
	
	public String order(String property) {
		UriComponentsBuilder uriBuilderOrder = UriComponentsBuilder.
					fromUriString(uriComponentsBuilder.build(true).encode().toUriString());
		
		String orderSelect = String.format("%s,%s", property, invertOrder(property));
		return uriBuilderOrder.replaceQueryParam("sort", orderSelect).build(true).encode().toUriString();
	}
	
	public String invertOrder(String property) {
		String orderSelection = "asc";
		
		Order order = page.getSort() != null ? page.getSort().getOrderFor(property) : null;
		if(order != null) {
			orderSelection = Sort.Direction.ASC.equals(order.getDirection()) ? "desc" : "asc";
		}
		
		return orderSelection;
	}
	
	public boolean desc(String property) {
		return invertOrder(property).equals("asc");
	}
	
	public boolean ordained(String property) {
		Order order = page.getSort() != null ? page.getSort().getOrderFor(property) : null;
		
		if(order == null) {
			return false;
		}
		
		return page.getSort().getOrderFor(property) != null ? true : false;
	}
}
