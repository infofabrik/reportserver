package net.datenwerke.gf.client.upload.dto;

import java.io.Serializable;
import java.util.HashMap;

import javax.persistence.Transient;

import net.datenwerke.gf.client.upload.filter.FileUploadFilter;
import net.datenwerke.gxtdto.client.model.DwModel;

public class UploadProperties implements Serializable, DwModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6014101932803686946L;

	
	
	private String handler;
	private String id;
	private HashMap<String, String> metadata = new HashMap<String, String>();
	
	@Transient
	private FileUploadFilter filter = FileUploadFilter.DUMMY_UPLOAD_FILTER;
	
	public UploadProperties(){
	}
	
	public UploadProperties(String id, String handler) {
		this.id = id;
		this.handler = handler;
	}

	public String getHandler() {
		return handler;
	}

	public void setHandler(String handler) {
		this.handler = handler;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public HashMap<String, String> getMetadata() {
		return metadata;
	}

	public void setMetadata(HashMap<String, String> metadata) {
		if(null==metadata)
			metadata = new HashMap<String, String>();
		this.metadata = metadata;
	}

	public void addMetadata(String key, String value){
		this.metadata.put(key,value);
	}

	public FileUploadFilter getFilter() {
		return filter;
	}

	public void setFilter(FileUploadFilter filter) {
		this.filter = filter;
	}
	
	
	
}
