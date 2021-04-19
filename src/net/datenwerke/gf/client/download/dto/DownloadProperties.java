package net.datenwerke.gf.client.download.dto;

import java.io.Serializable;
import java.util.HashMap;

import net.datenwerke.gxtdto.client.model.DwModel;

public class DownloadProperties implements Serializable, DwModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3230462892424038936L;
	
	
	private String id;
	private String handler;
	private HashMap<String, String> metadata = new HashMap<String, String>();
	
	public DownloadProperties(){
	}
	
	public DownloadProperties(String id, String handler) {
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
}
