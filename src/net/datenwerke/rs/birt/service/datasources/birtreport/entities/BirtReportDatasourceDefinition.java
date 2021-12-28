package net.datenwerke.rs.birt.service.datasources.birtreport.entities;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.envers.Audited;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.rs.core.service.datasourcemanager.entities.CacheableDatasource;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceDefinition;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceDefinitionConfig;

@Entity
@Table(name="BIRT_REPORT_DATASRC")
@Audited
@GenerateDto(
	dtoPackage="net.datenwerke.rs.birt.client.datasources.dto"
)
public class BirtReportDatasourceDefinition extends DatasourceDefinition implements CacheableDatasource {

	private static final long serialVersionUID = -2945350730488549534L;

	@Override
	@Transient
	public DatasourceDefinitionConfig createConfigObject() {
		return new BirtReportDatasourceConfig();
	}
	
	@ExposeToClient
	private int databaseCache = 0;

	public int getDatabaseCache() {
		return databaseCache;
	}

	public void setDatabaseCache(int databaseCache) {
		this.databaseCache = databaseCache;
	}


}
