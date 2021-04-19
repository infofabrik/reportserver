package net.datenwerke.rs.utils.zip;

import java.util.zip.ZipEntry;

public abstract class ZipExtractionConfig {

	private String[] allowedExtensions;

	public ZipExtractionConfig(String... allowedExtensions){
		this.allowedExtensions = allowedExtensions;
	}
	
	public boolean isAllowedFile(ZipEntry entry){
		if(allowedExtensions.length == 0)
			return true;
		for(String ext : allowedExtensions)
			if(entry.getName().endsWith(ext))
				return true;
		return false;
	}
	
	public void processContent(ZipEntry entry, byte[] content){
		
	}
}
