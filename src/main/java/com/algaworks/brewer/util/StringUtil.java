package com.algaworks.brewer.util;

import java.text.Normalizer;

public abstract class StringUtil {
	
	public static String removerAcentos(String str) {
	    return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
	}
	
	public static String caixaAltaPrimeiroCaracter(String str) {
		StringBuilder builder = new StringBuilder(new Character(Character.toUpperCase(str.charAt(0))).toString());
		builder.append(str.substring(1).toLowerCase());
		return builder.toString();
	}
}
