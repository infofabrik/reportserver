package net.datenwerke.rs.core.service.datasinkmanager.entities;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeMethodToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.rs.utils.entitycloner.annotation.EnclosedEntity;


@GenerateDto(
		dtoPackage="net.datenwerke.rs.core.client.datasinkmanager.dto"
	)
public interface DatasinkContainerProvider {

	@ExposeMethodToClient
	@EnclosedEntity
	public DatasinkContainer getDatasinkContainer();

	@ExposeMethodToClient
	@EnclosedEntity
	public void setDatasinkContainer(DatasinkContainer datasinkContainer);

}
