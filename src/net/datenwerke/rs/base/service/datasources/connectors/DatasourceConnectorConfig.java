package net.datenwerke.rs.base.service.datasources.connectors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;

@Entity
@Table(name="DATASOURCE_CONNECTOR_CFG")
@Audited
@GenerateDto(
	dtoPackage="net.datenwerke.rs.base.client.datasources.dto"
)
public class DatasourceConnectorConfig {
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@ExposeToClient(id=true)
	private Long id;
	
	@ExposeToClient
	private String key;
	
	@Lob
	@Type(type = "net.datenwerke.rs.utils.hibernate.RsClobType")
	@ExposeToClient
	private String value;

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

	public Long getId() {
		return id;
	}

	public boolean contentEquals(DatasourceConnectorConfig otherConnectorConfig) {
		return null == value ? otherConnectorConfig == null : value.equals(otherConnectorConfig.getValue());
	}
	
}
