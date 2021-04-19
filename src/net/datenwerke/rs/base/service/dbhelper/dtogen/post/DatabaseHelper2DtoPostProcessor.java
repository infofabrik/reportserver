package net.datenwerke.rs.base.service.dbhelper.dtogen.post;

import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoPostProcessor;
import net.datenwerke.rs.base.client.dbhelper.dto.DatabaseHelperDto;
import net.datenwerke.rs.base.service.dbhelper.DatabaseHelper;

public class DatabaseHelper2DtoPostProcessor implements Poso2DtoPostProcessor<DatabaseHelper, DatabaseHelperDto> {

	@Override
	public void dtoCreated(DatabaseHelper poso, DatabaseHelperDto dto) {
		checkJdbcDriver(poso,dto);
	}

	@Override
	public void dtoInstantiated(DatabaseHelper poso, DatabaseHelperDto dto) {
		checkJdbcDriver(poso,dto);
	}

	protected void checkJdbcDriver(DatabaseHelper poso, DatabaseHelperDto dto) {
		try{
			Class.forName(poso.getDriver());
			dto.setJdbcDriverAvailable(true);
		} catch(Throwable e){
			dto.setJdbcDriverAvailable(false);
		}
	}

}
