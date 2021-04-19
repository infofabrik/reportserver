package net.datenwerke.rs.base.service.datasources.definitions;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.gf.base.service.annotations.Indexed;

@Entity
@Table(name="CSV_DATASOURCE_CONF")
@Audited
@GenerateDto(
dtoPackage="net.datenwerke.rs.base.client.datasources.dto"
)
@Indexed
public class CsvDatasourceConfig extends FormatBasedDatasourceConfig {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4558842881453487382L;

	@ExposeToClient(disableHtmlEncode=true)
	@Column(length = 4096)
	private String queryWrapper;

	public String getQueryWrapper() {
		return queryWrapper;
	}

	public void setQueryWrapper(String queryWrapper) {
		this.queryWrapper = queryWrapper;
	}

}
