package net.datenwerke.gf.client.fileselection;

import java.io.Serializable;
import java.util.HashMap;

import net.datenwerke.gxtdto.client.model.DwModel;

public class FileSelectionConfig implements Serializable, DwModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4418656779746516676L;
	
	private String handler;
	private HashMap<String, String> metadata = new HashMap<String, String>();
	
	public FileSelectionConfig(){
	}

	public FileSelectionConfig(String handler) {
		super();
		this.handler = handler;
	}

	public String getHandler() {
		return handler;
	}

	public void setHandler(String handler) {
		this.handler = handler;
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
