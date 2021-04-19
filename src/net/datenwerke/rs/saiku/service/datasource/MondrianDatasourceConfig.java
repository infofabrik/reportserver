package net.datenwerke.rs.saiku.service.datasource;

import javax.persistence.Entity;
import javax.persistence.Table;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceDefinitionConfig;

import org.hibernate.envers.Audited;

@Entity
@Table(name="MONDRIAN_DATASOURCE_CFG")
@Audited
@GenerateDto(
	dtoPackage="net.datenwerke.rs.saiku.client.datasource.dto"
)
public class MondrianDatasourceConfig extends DatasourceDefinitionConfig {
	
	private static final long serialVersionUID = 2267825708465524128L;
	
	@ExposeToClient
	private String cubeName;

	
	public MondrianDatasourceConfig() {

	}

	
	
	public String getCubeName() {
		return cubeName;
	}

	public void setCubeName(String cube) {
		this.cubeName = cube;
	}

	
	
}
