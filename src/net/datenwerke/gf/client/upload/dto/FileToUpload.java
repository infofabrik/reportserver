package net.datenwerke.gf.client.upload.dto;

import java.io.Serializable;

public class FileToUpload implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4738338258610882160L;
	
	private String name;
	private long size;
	private String b64Data;
	
	public FileToUpload() {
		name = null;
		size = 0;
		b64Data = null;
	}
	
	public FileToUpload(String name, long size, String b64Data) {
		this.name = name;
		this.size = size;
		this.b64Data = b64Data;
	}
	public String getName() {
		return name;
	}
	public long getSize() {
		return size;
	}
	public String getB64Data() {
		return b64Data;
	}
}