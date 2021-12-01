package net.datenwerke.rs.base.service.parameters.datetime;

import java.util.Date;

import net.datenwerke.rs.core.service.parameters.entities.ParameterDefinitionForJuel;

public class DateTimeParameterDefinitionForJuel extends
		ParameterDefinitionForJuel {

	private Mode mode = Mode.Date;
	private Boolean useNowAsDefault = true;
	private Date defaultValue;
	
	public Mode getMode() {
		return mode;
	}
	public void setMode(Mode mode) {
		this.mode = mode;
	}
	public Boolean getUseNowAsDefault() {
		return useNowAsDefault;
	}
	public void setUseNowAsDefault(Boolean useNowAsDefault) {
		this.useNowAsDefault = useNowAsDefault;
	}
	public Date getDefaultValue() {
		return defaultValue;
	}
	public void setDefaultValue(Date defaultValue) {
		this.defaultValue = defaultValue;
	}
	
	
}
