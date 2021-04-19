package net.datenwerke.security.service.security.rights;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeMethodToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;

/**
 * 
 *
 */
@GenerateDto(
	dtoPackage="net.datenwerke.security.client.security.dto"
)
public interface Right {

	@ExposeMethodToClient
	public String getAbbreviation();
	
	@ExposeMethodToClient
	public String getDescription();

	@ExposeMethodToClient
	public long getBitField();
}
