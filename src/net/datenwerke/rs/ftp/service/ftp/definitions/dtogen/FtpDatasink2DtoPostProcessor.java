package net.datenwerke.rs.ftp.service.ftp.definitions.dtogen;

import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoPostProcessor;
import net.datenwerke.rs.ftp.client.ftp.dto.FtpDatasinkDto;
import net.datenwerke.rs.ftp.service.ftp.definitions.FtpDatasink;

public class FtpDatasink2DtoPostProcessor implements Poso2DtoPostProcessor<FtpDatasink,FtpDatasinkDto> {

	@Override
	public void dtoCreated(FtpDatasink poso, FtpDatasinkDto dto) {
		if(null != poso.getPassword() && ! "".equals(poso.getPassword().trim()))
			dto.setHasPassword(true);
	}

	@Override
	public void dtoInstantiated(FtpDatasink poso, FtpDatasinkDto dto) {
		// TODO Auto-generated method stub
		
	}

}

