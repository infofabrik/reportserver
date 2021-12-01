package net.datenwerke.rs.scripting.service.scripting.extensions;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;

@GenerateDto(
	dtoPackage="net.datenwerke.rs.scripting.client.scripting.dto"
)
public class DisplayCondition {

	@ExposeToClient
	private String propertyName;
	
	@ExposeToClient
	private String value;
	
	@ExposeToClient
	private DisplayConditionType type;

	public DisplayCondition() {
	}
	
	public DisplayCondition(String propertyName, String value) {
		this.propertyName = propertyName;
		this.value = value;
		this.type = DisplayConditionType.EQUALS;
	}
	
	public DisplayCondition(String propertyName, String value, DisplayConditionType type) {
		this.propertyName = propertyName;
		this.value = value;
		this.type = type;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public DisplayConditionType getType() {
		return type;
	}

	public void setType(DisplayConditionType type) {
		this.type = type;
	}
	
	
}
