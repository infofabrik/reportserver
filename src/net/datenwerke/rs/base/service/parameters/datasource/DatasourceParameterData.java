package net.datenwerke.rs.base.service.parameters.datasource;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;

import org.hibernate.envers.Audited;

/**
 * 
 *
 */
@Entity
@Table(name="DATASOURCE_PARAMETER_DATA")
@Audited
@GenerateDto(
	dtoPackage="net.datenwerke.rs.base.client.parameters.datasource.dto",
	proxyableDto=false,
	createDecorator=true
)
public class DatasourceParameterData {

	@ExposeToClient
	private String key;
	
	@ExposeToClient
	private String value;
	
	@Version
	private Long version;
	
	@ExposeToClient(id=true)
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	
    public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public void update(DatasourceParameterData singleValue) {
		key = singleValue.getKey();
		value = singleValue.getValue();
	}

	public boolean keyAndValueMatch(DatasourceParameterData obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof DatasourceParameterData)) {
			return false;
		}
		DatasourceParameterData other = (DatasourceParameterData) obj;
		if (key == null) {
			if (other.key != null) {
				return false;
			}
		} else if (!key.equals(other.key)) {
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
