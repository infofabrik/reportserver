package net.datenwerke.rs.scheduler.client.scheduler.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ReportScheduleDefinitionSendToConfig implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	private Map<String,String> values;
	
	public ReportScheduleDefinitionSendToConfig(){}

	public ReportScheduleDefinitionSendToConfig(String id,
			Map<String, String> values) {
		this.id = id;
		this.values = values;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Map<String, String> getValues() {
		return values;
	}

	public void setValues(HashMap<String, String> values) {
		this.values = values;
	}
	
	
}
