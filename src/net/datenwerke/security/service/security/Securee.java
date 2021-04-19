package net.datenwerke.security.service.security;

import java.util.List;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeMethodToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.rs.utils.entitycloner.annotation.EnclosedEntity;
import net.datenwerke.security.service.security.rights.Right;

/**
 * 
 *
 */
@GenerateDto(
	dtoPackage="net.datenwerke.security.client.security.dto"
)
public interface Securee {

	@ExposeMethodToClient
	public String getSecureeId();
	
	@ExposeMethodToClient
	public String getName();
	
	@EnclosedEntity
	@ExposeMethodToClient
	public List<Right> getRights();
	
}
