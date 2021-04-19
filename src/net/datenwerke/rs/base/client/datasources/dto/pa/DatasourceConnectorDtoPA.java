package net.datenwerke.rs.base.client.datasources.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.Long;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.base.client.datasources.dto.DatasourceConnectorDto;
import net.datenwerke.rs.base.client.datasources.dto.decorator.DatasourceConnectorDtoDec;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.base.service.datasources.connectors.DatasourceConnector.class)
public interface DatasourceConnectorDtoPA extends PropertyAccess<DatasourceConnectorDto> {


	public static final DatasourceConnectorDtoPA INSTANCE = GWT.create(DatasourceConnectorDtoPA.class);

	@Path("dtoId")
	public ModelKeyProvider<DatasourceConnectorDto> dtoId();

	/* Properties */
	public ValueProvider<DatasourceConnectorDto,Long> id();


}
