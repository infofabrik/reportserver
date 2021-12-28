package net.datenwerke.rs.base.service.datasources.definitions;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceDefinition;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceDefinitionConfig;

/**
 * 
 *
 */
@Entity
@Table(name="DATABASE_DATASOURCE_CONF")
@Audited
@GenerateDto(
	dtoPackage="net.datenwerke.rs.base.client.datasources.dto"
)
public class DatabaseDatasourceConfig extends DatasourceDefinitionConfig {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2133375471305510767L;
	
	@ExposeToClient(allowArbitraryLobSize=true,disableHtmlEncode=true)
	@Lob
	@Type(type = "net.datenwerke.rs.utils.hibernate.RsClobType")
	protected String query;
	
	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	@Override
	public boolean contentEquals(DatasourceDefinition definition, DatasourceDefinitionConfig config) {
		if(! (config instanceof DatabaseDatasourceConfig))
			return false;
		return null == query ? null == ((DatabaseDatasourceConfig)config).getQuery() : query.equals(((DatabaseDatasourceConfig)config).getQuery());
	}
	
}
