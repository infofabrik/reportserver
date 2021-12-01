package net.datenwerke.rs.core.client.contexthelp.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ContextHelpInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5137130641578808198L;

	private String id;
	
	private String title;
	
	private Map<String, ContextHelpData<? extends Object>> dataMap = new HashMap<String, ContextHelpData<? extends Object>>();
	
	public ContextHelpInfo(){}
	
	public ContextHelpInfo(String id){
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void setDataMap(Map<String, ContextHelpData<? extends Object>> dataMap) {
		this.dataMap = dataMap;
	}
	
	public Map<String, ContextHelpData<? extends Object>> getDataMap() {
		return dataMap;
	}
	
	public void addData(String key, String data){
		this.dataMap.put(key, new ContextHelpStringData(data));
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
}
