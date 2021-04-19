package net.datenwerke.rs.core.client.datasourcemanager.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.Long;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionConfigDto;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceDefinitionConfig.class)
public interface DatasourceDefinitionConfigDtoPA extends PropertyAccess<DatasourceDefinitionConfigDto> {


	public static final DatasourceDefinitionConfigDtoPA INSTANCE = GWT.create(DatasourceDefinitionConfigDtoPA.class);

	@Path("dtoId")
	public ModelKeyProvider<DatasourceDefinitionConfigDto> dtoId();

	/* Properties */
	public ValueProvider<DatasourceDefinitionConfigDto,Long> id();


}
