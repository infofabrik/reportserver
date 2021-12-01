package net.datenwerke.rs.search.service.search.results;

import java.io.Serializable;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.rs.utils.entitycloner.annotation.EnclosedEntity;

@GenerateDto(
	dtoPackage="net.datenwerke.rs.search.client.search.dto"
)
public class SearchResultTag implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7679069026469184052L;
	
	@ExposeToClient
	@EnclosedEntity
	private SearchResultTagType type;
	
	@ExposeToClient
	private String value;
	
	@ExposeToClient
	private String display;
	
	public SearchResultTag() {
	};
	
	public SearchResultTag(SearchResultTagType type, String value, String display){
		this.type = type;
		this.value = value;
		this.display = display;
	}
	
	public SearchResultTagType getType() {
		return type;
	}
	public void setType(SearchResultTagType type) {
		this.type = type;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getDisplay() {
		return display;
	}
	public void setDisplay(String display) {
		this.display = display;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof SearchResultTag)) {
			return false;
		}
		SearchResultTag other = (SearchResultTag) obj;
		if (type == null) {
			if (other.type != null) {
				return false;
			}
		} else if (!type.equals(other.type)) {
			return false;
		}
		if (value == null) {
			if (other.value != null) {
				return false;
			}
		} else if (!value.equals(other.value)) {
			return false;
		}
		return true;
	}
	
	
	
}
