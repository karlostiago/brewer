package com.algaworks.brewer.storage.local;

import org.springframework.web.multipart.MultipartFile;

public interface FotoStorage {
	
	public void salvarTemporariamente(MultipartFile[] files);
}
