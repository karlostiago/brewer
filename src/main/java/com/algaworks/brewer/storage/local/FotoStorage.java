package com.algaworks.brewer.storage.local;

import org.springframework.web.multipart.MultipartFile;

public interface FotoStorage {
	
	public String salvarTemporariamente(MultipartFile[] files);
	public byte[] recuperarFotoTemporaria(String nome);
	public byte[] recuperarFotoLocal(String nome);
	public void salvar(String foto);
}
