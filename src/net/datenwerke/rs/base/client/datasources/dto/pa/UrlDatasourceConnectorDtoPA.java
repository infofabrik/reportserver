package net.datenwerke.rs.base.client.datasources.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.String;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.base.client.datasources.dto.UrlDatasourceConnectorDto;
import net.datenwerke.rs.base.client.datasources.dto.pa.DatasourceConnectorDtoPA;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.base.service.datasources.connectors.UrlDatasourceConnector.class)
public interface UrlDatasourceConnectorDtoPA extends DatasourceConnectorDtoPA {


	public static final UrlDatasourceConnectorDtoPA INSTANCE = GWT.create(UrlDatasourceConnectorDtoPA.class);


	/* Properties */
	public ValueProvider<UrlDatasourceConnectorDto,String> url();


}
