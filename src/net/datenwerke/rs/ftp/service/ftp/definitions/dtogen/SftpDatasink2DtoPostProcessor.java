package net.datenwerke.rs.ftp.service.ftp.definitions.dtogen;

import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoPostProcessor;
import net.datenwerke.rs.ftp.client.ftp.dto.SftpDatasinkDto;
import net.datenwerke.rs.ftp.service.ftp.definitions.SftpDatasink;

public class SftpDatasink2DtoPostProcessor implements Poso2DtoPostProcessor<SftpDatasink,SftpDatasinkDto> {

	@Override
	public void dtoCreated(SftpDatasink poso, SftpDatasinkDto dto) {
		if(null != poso.getPassword() && ! "".equals(poso.getPassword().trim()))
			dto.setHasPassword(true);
	}

	@Override
	public void dtoInstantiated(SftpDatasink poso, SftpDatasinkDto dto) {
		// TODO Auto-generated method stub
		
	}

}

