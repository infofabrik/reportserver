package net.datenwerke.rs.core.client.datasourcemanager.dto;

import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceContainerDto;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceContainerProvider;

/**
 * Dto for {@link DatasourceContainerProvider}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public interface DatasourceContainerProviderDto {

	public DatasourceContainerDto getDatasourceContainer();

	public void setDatasourceContainer(DatasourceContainerDto datasourceContainer);


}
