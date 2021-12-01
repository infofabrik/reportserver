package net.datenwerke.rs.base.service.datasources.definitions;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.JoinTable;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.rs.base.service.datasources.connectors.DatasourceConnector;
import net.datenwerke.rs.base.service.datasources.connectors.DatasourceConnectorConfig;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceDefinition;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceDefinitionConfig;
import net.datenwerke.rs.utils.entitycloner.annotation.EnclosedEntity;

@GenerateDto(
	dtoPackage="net.datenwerke.rs.base.client.datasources.dto",
	abstractDto=true
)
@MappedSuperclass
public abstract class FormatBasedDatasourceConfig extends DatasourceDefinitionConfig {

	private static final long serialVersionUID = 1699176959776661620L;
	
	@EnclosedEntity
	@OneToMany(cascade=CascadeType.ALL)
	@ExposeToClient
	@JoinTable(name="DATASOURCE_FBCFG_2_DSCC")
	private List<DatasourceConnectorConfig> connectorConfig;

	public List<DatasourceConnectorConfig> getConnectorConfig() {
		return connectorConfig;
	}

	public void setConnectorConfig(List<DatasourceConnectorConfig> connectorConfig) {
		this.connectorConfig = connectorConfig;
	}
	
	@Override
	public boolean contentEquals(DatasourceDefinition definition, DatasourceDefinitionConfig config) {
		if(! (config instanceof FormatBasedDatasourceConfig))
			return false;
		if(! (definition instanceof FormatBasedDatasourceDefinition))
			return false;
		
		FormatBasedDatasourceDefinition def = (FormatBasedDatasourceDefinition) definition;
		DatasourceConnector connector = def.getConnector();
		if(null == connector)
			return true;
		
		FormatBasedDatasourceConfig otherConf = (FormatBasedDatasourceConfig) config;
		
		if(null == getConnectorConfig())
			return null == otherConf.getConnectorConfig() || otherConf.getConnectorConfig().isEmpty();
		
		DatasourceConnectorConfig connectorConfig = connector.getConnectorConfigFor(this);
		DatasourceConnectorConfig otherConnectorConfig = connector.getConnectorConfigFor(otherConf);

		return null == connectorConfig ? null == otherConnectorConfig : connectorConfig.contentEquals(otherConnectorConfig);
	}
}
