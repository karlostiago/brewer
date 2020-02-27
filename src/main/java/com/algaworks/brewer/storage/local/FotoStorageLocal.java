package com.algaworks.brewer.storage.local;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;

public class FotoStorageLocal implements FotoStorage {

	private static final Logger logger = LoggerFactory.getLogger(FotoStorageLocal.class);
	
	private Path local;
	private Path localTemporario;
		
	public FotoStorageLocal(ServletContext context) {
		this.local = FileSystems.getDefault().getPath(context.getContextPath(), ".brewerfotos");
		criarPastas();
	}

	@Override
	public String salvarTemporariamente(MultipartFile[] files) {
		
		String nome = null;
		
		if(files != null && files.length > 0) {
			MultipartFile arquivo = files[0];
			try {
				nome = renomear(arquivo.getOriginalFilename());
				arquivo.transferTo(new File(this.localTemporario.toAbsolutePath().toString(), nome));
			} catch (IOException e) {
				throw new RuntimeException("Erro ao salvar a foto na pasta temporária");
			}
		}
		
		return nome;
	}
	
	@Override
	public byte[] recuperarFotoTemporaria(String nome) {
		try {
			return Files.readAllBytes(this.localTemporario.resolve(nome));
		} catch (IOException e) {
			throw new RuntimeException("Erro ao recuperar foto no diretório temporário.", e);
		}
	}
	
	@Override
	public byte[] recuperarFotoLocal(String nome) {
		try {
			return Files.readAllBytes(this.local.resolve(nome));
		} catch (IOException e) {
			throw new RuntimeException("Erro ao recuperar foto no diretório local.", e);
		}
	}
	
	@Override
	public void salvar(String foto) {
		try {
			Files.move(this.localTemporario.resolve(foto), this.local.resolve(foto));
		} catch (IOException e) {
			throw new RuntimeException("Erro ao tentar mover a foto para o diretorio local. " + e.getMessage());
		}
		
		try {
			Thumbnails.of(this.local.resolve(foto).toString()).size(40, 68).toFiles(Rename.PREFIX_DOT_THUMBNAIL);
		} catch (IOException e) {
			throw new RuntimeException("Erro ao redimencionar foto. " + e.getMessage());
		}
	}
	
	private void criarPastas() {
		try {
			Files.createDirectories(this.local);
			this.localTemporario = FileSystems.getDefault().getPath(this.local.toString(), "temp");
			Files.createDirectories(this.localTemporario);
			
			if(logger.isDebugEnabled()) {
				logger.debug("Pastas criadas com sucesso.");
				logger.debug("Pasta default: " + this.local.toAbsolutePath());
				logger.debug("Pasta temporaria: " + this.localTemporario.toAbsolutePath());
			}
		} catch (IOException e) {
			throw new RuntimeException("Erro ao criar pastas para salvar fotos. " + e.getMessage());
		}
	}
	
	private String renomear(String nome) {
		
		StringBuilder builder = new StringBuilder(UUID.randomUUID().toString());
		builder.append("_");
		builder.append(nome);
		
		return builder.toString();
	}
}
