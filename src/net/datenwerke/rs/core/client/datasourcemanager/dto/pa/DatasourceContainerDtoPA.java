package net.datenwerke.rs.core.client.datasourcemanager.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.Long;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceContainerDto;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionConfigDto;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionDto;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceContainer.class)
public interface DatasourceContainerDtoPA extends PropertyAccess<DatasourceContainerDto> {


	public static final DatasourceContainerDtoPA INSTANCE = GWT.create(DatasourceContainerDtoPA.class);

	@Path("dtoId")
	public ModelKeyProvider<DatasourceContainerDto> dtoId();

	/* Properties */
	public ValueProvider<DatasourceContainerDto,DatasourceDefinitionDto> datasource();
	public ValueProvider<DatasourceContainerDto,DatasourceDefinitionConfigDto> datasourceConfig();
	public ValueProvider<DatasourceContainerDto,Long> id();


}
