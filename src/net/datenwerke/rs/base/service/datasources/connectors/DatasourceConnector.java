package net.datenwerke.rs.base.service.datasources.connectors;

import java.io.IOException;
import java.io.InputStream;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.envers.Audited;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.rs.base.service.datasources.definitions.FormatBasedDatasourceConfig;

@Entity
@Table(name="DATASOURCE_CONNECTOR")
@GenerateDto(
	dtoPackage="net.datenwerke.rs.base.client.datasources.dto", createDecorator = true
)
@Audited
public abstract class DatasourceConnector {

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

	public abstract InputStream getDataStream(FormatBasedDatasourceConfig dsConfig) throws IOException;

	public abstract DatasourceConnectorConfig getConnectorConfigFor(FormatBasedDatasourceConfig dsConfig);

}
