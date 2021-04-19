package net.datenwerke.rs.base.client.datasources.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.base.client.datasources.dto.ArgumentDatasourceConnectorDto;
import net.datenwerke.rs.base.client.datasources.dto.decorator.ArgumentDatasourceConnectorDtoDec;
import net.datenwerke.rs.base.client.datasources.dto.pa.DatasourceConnectorDtoPA;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.base.service.datasources.connectors.ArgumentDatasourceConnector.class)
public interface ArgumentDatasourceConnectorDtoPA extends DatasourceConnectorDtoPA {


	public static final ArgumentDatasourceConnectorDtoPA INSTANCE = GWT.create(ArgumentDatasourceConnectorDtoPA.class);


	/* Properties */


}
