package net.datenwerke.rs.dropbox.service.dropbox.definitions.dtogen;

import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoPostProcessor;
import net.datenwerke.rs.dropbox.client.dropbox.dto.DropboxDatasinkDto;
import net.datenwerke.rs.dropbox.service.dropbox.definitions.DropboxDatasink;

public class DropboxDatasink2DtoPostProcessor implements Poso2DtoPostProcessor<DropboxDatasink, DropboxDatasinkDto> {

	@Override
	public void dtoCreated(DropboxDatasink poso, DropboxDatasinkDto dto) {
		if (null != poso.getRefreshToken() && !"".equals(poso.getRefreshToken().trim()))
			dto.setHasRefreshToken(true);
		
		if (null != poso.getSecretKey() && !"".equals(poso.getSecretKey().trim()))
           dto.setHasSecretKey(true);
	}

	@Override
	public void dtoInstantiated(DropboxDatasink poso, DropboxDatasinkDto dto) {
		// TODO Auto-generated method stub
	}
}
