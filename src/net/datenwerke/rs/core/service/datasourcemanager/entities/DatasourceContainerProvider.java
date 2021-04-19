package net.datenwerke.rs.core.service.datasourcemanager.entities;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeMethodToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.rs.utils.entitycloner.annotation.EnclosedEntity;


@GenerateDto(
		dtoPackage="net.datenwerke.rs.core.client.datasourcemanager.dto"
	)
public interface DatasourceContainerProvider {

	@ExposeMethodToClient
	@EnclosedEntity
	public DatasourceContainer getDatasourceContainer();

	@ExposeMethodToClient
	@EnclosedEntity
	public void setDatasourceContainer(DatasourceContainer datasourceContainer);

}
