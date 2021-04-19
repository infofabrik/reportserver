package net.datenwerke.rs.base.service.datasources.connectors;

import java.io.InputStream;

import javax.persistence.Entity;
import javax.persistence.Lob;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.rs.base.service.datasources.definitions.FormatBasedDatasourceConfig;

import org.apache.commons.io.IOUtils;
import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;

@GenerateDto(
		dtoPackage="net.datenwerke.rs.base.client.datasources.dto"
	)
@Entity
@Audited
public class TextDatasourceConnector extends DatasourceConnector {

	@Lob
	@Type(type = "net.datenwerke.rs.utils.hibernate.RsClobType")
	@ExposeToClient(view=DtoView.ALL)
	private String data;

	public void setData(String data) {
		this.data = data;
	}

	public String getData() {
		return data;
	}

	@Override
	public InputStream getDataStream(FormatBasedDatasourceConfig dsConfig) {
		return IOUtils.toInputStream(data);
	}
	
	@Override
	public DatasourceConnectorConfig getConnectorConfigFor(FormatBasedDatasourceConfig dsConfig) {
		return null;
	}
}
