package com.algaworks.brewer.exceptions;

public class BrewerRuntimeException extends RuntimeException {
	
	private static final long serialVersionUID = -2043087211726227622L;
	
	private String component;
	
	public BrewerRuntimeException(String message) {
		super(message);
	}
	
	public BrewerRuntimeException(String component, String message) {
		super(message);
		this.component = component;
	}
	
	public String getComponent() {
		return component;
	}
}
