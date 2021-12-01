package net.datenwerke.rs.eximport.client.eximport.im.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ImportConfigDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2997629001090925249L;

	private List<ImportItemConfigDto> configs = new ArrayList<ImportItemConfigDto>();

	public void setConfigs(List<ImportItemConfigDto> configs) {
		this.configs = configs;
	}

	public List<ImportItemConfigDto> getConfigs() {
		return configs;
	} 
	
	public void addConfig(ImportItemConfigDto config){
		configs.add(config);
	}
	
}
