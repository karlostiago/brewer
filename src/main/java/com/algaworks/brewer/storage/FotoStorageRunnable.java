package com.algaworks.brewer.storage;

import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

import com.algaworks.brewer.dto.FotoDTO;
import com.algaworks.brewer.storage.local.FotoStorage;

public class FotoStorageRunnable implements Runnable {
	
	private MultipartFile[] files; 
	private DeferredResult<FotoDTO> result;
	private FotoStorage fotoStorage;
	
	public FotoStorageRunnable(MultipartFile[] files, DeferredResult<FotoDTO> result, FotoStorage fotoStorage) {
		this.files = files;
		this.result = result;
		this.fotoStorage = fotoStorage;
	}
	
	@Override
	public void run() {
		String nomeFoto = files[0].getOriginalFilename();
		String contentType = files[0].getContentType();
		
		fotoStorage.salvarTemporariamente(files);
		
		result.setResult(new FotoDTO(nomeFoto, contentType));
	}

}
