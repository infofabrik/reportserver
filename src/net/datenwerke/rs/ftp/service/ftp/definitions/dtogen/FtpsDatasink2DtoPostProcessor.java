package net.datenwerke.rs.ftp.service.ftp.definitions.dtogen;

import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoPostProcessor;
import net.datenwerke.rs.ftp.client.ftp.dto.FtpsDatasinkDto;
import net.datenwerke.rs.ftp.service.ftp.definitions.FtpsDatasink;

public class FtpsDatasink2DtoPostProcessor implements Poso2DtoPostProcessor<FtpsDatasink,FtpsDatasinkDto>{

	@Override
	public void dtoCreated(FtpsDatasink poso, FtpsDatasinkDto dto) {
		if(null != poso.getPassword() && ! "".equals(poso.getPassword().trim()))
			dto.setHasPassword(true);
	}

	@Override
	public void dtoInstantiated(FtpsDatasink poso, FtpsDatasinkDto dto) {
		// TODO Auto-generated method stub
		
	}

}