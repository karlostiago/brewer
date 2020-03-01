package com.algaworks.brewer.util;

import java.text.Normalizer;

public abstract class StringUtil {
	
	public static String removerAcentos(String str) {
	    return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
	}
}
