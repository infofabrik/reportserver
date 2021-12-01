package net.datenwerke.rs.base.service.datasources.definitions.dtogen;

import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoPostProcessor;
import net.datenwerke.rs.base.client.datasources.dto.DatabaseDatasourceDto;
import net.datenwerke.rs.base.service.datasources.definitions.DatabaseDatasource;

public class DatabaseDatasource2DtoPostProcessor implements Poso2DtoPostProcessor<DatabaseDatasource, DatabaseDatasourceDto> {

	@Override
	public void dtoCreated(DatabaseDatasource poso, DatabaseDatasourceDto dto) {
		if(null != poso.getPassword() && ! "".equals(poso.getPassword().trim()))
			dto.setHasPassword(true);
	}

	@Override
	public void dtoInstantiated(DatabaseDatasource arg0,
			DatabaseDatasourceDto arg1) {
		// TODO Auto-generated method stub
		
	}

}
