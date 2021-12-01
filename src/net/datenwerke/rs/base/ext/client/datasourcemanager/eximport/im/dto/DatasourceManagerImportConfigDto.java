package net.datenwerke.rs.base.ext.client.datasourcemanager.eximport.im.dto;

import net.datenwerke.rs.core.client.datasourcemanager.dto.AbstractDatasourceManagerNodeDto;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionDto;
import net.datenwerke.treedb.ext.client.eximport.im.dto.TreeImportConfigDto;

public class DatasourceManagerImportConfigDto extends TreeImportConfigDto<AbstractDatasourceManagerNodeDto> {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1879599255965355795L;

	private DatasourceDefinitionDto defaultDatasource;
	
	public void setDefaultDatasource(DatasourceDefinitionDto defaultDatasource) {
		this.defaultDatasource = defaultDatasource;
	}
	
	public DatasourceDefinitionDto getDefaultDatasource() {
		return defaultDatasource;
	}
	
}
