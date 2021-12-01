package net.datenwerke.rs.base.service.datasources.connectors;

import java.io.IOException;
import java.io.InputStream;

import javax.persistence.Entity;

import org.apache.commons.io.IOUtils;
import org.hibernate.envers.Audited;

import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.rs.base.service.datasources.definitions.FormatBasedDatasourceConfig;

@GenerateDto(
		dtoPackage="net.datenwerke.rs.base.client.datasources.dto",
		createDecorator = true
	)
@Entity
@Audited
public class ArgumentDatasourceConnector extends DatasourceConnector {
	
	private static final transient String CONNECTOR_CFG_KEY = "ARGUMENT_DATASRC_CNCTR_CFG";

	@Override
	public InputStream getDataStream(FormatBasedDatasourceConfig dsConfig) throws IOException {
		DatasourceConnectorConfig ccfg = getConnectorConfigFor(dsConfig);
		if(null != ccfg)
			return IOUtils.toInputStream(ccfg.getValue());
		return null;
	}
	
	@Override
	public DatasourceConnectorConfig getConnectorConfigFor(FormatBasedDatasourceConfig dsConfig){
		for(DatasourceConnectorConfig ccfg : dsConfig.getConnectorConfig()){
			if(CONNECTOR_CFG_KEY.equals(ccfg.getKey())){
				return ccfg;
			}
		}
		return null;
	}

}
