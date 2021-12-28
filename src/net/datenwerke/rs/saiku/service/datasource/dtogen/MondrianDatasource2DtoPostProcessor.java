package net.datenwerke.rs.saiku.service.datasource.dtogen;

import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoPostProcessor;
import net.datenwerke.rs.saiku.client.datasource.dto.MondrianDatasourceDto;
import net.datenwerke.rs.saiku.service.datasource.MondrianDatasource;

public class MondrianDatasource2DtoPostProcessor implements Poso2DtoPostProcessor<MondrianDatasource, MondrianDatasourceDto> {

	@Override
	public void dtoCreated(MondrianDatasource poso, MondrianDatasourceDto dto) {
		if(null != poso.getPassword() && ! "".equals(poso.getPassword().trim()))
			dto.setHasPassword(true);
	}

	@Override
	public void dtoInstantiated(MondrianDatasource arg0,
			MondrianDatasourceDto arg1) {
		// TODO Auto-generated method stub
		
	}

}
