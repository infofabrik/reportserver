package net.datenwerke.rs.base.client.datasources.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.Long;
import java.lang.String;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.base.client.datasources.dto.DatasourceConnectorConfigDto;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.base.service.datasources.connectors.DatasourceConnectorConfig.class)
public interface DatasourceConnectorConfigDtoPA extends PropertyAccess<DatasourceConnectorConfigDto> {


	public static final DatasourceConnectorConfigDtoPA INSTANCE = GWT.create(DatasourceConnectorConfigDtoPA.class);

	@Path("dtoId")
	public ModelKeyProvider<DatasourceConnectorConfigDto> dtoId();

	/* Properties */
	public ValueProvider<DatasourceConnectorConfigDto,Long> id();
	public ValueProvider<DatasourceConnectorConfigDto,String> key();
	public ValueProvider<DatasourceConnectorConfigDto,String> value();


}
