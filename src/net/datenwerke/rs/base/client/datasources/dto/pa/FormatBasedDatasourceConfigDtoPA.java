package net.datenwerke.rs.base.client.datasources.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.util.List;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.base.client.datasources.dto.DatasourceConnectorConfigDto;
import net.datenwerke.rs.base.client.datasources.dto.FormatBasedDatasourceConfigDto;
import net.datenwerke.rs.core.client.datasourcemanager.dto.pa.DatasourceDefinitionConfigDtoPA;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.base.service.datasources.definitions.FormatBasedDatasourceConfig.class)
public interface FormatBasedDatasourceConfigDtoPA extends DatasourceDefinitionConfigDtoPA {


	public static final FormatBasedDatasourceConfigDtoPA INSTANCE = GWT.create(FormatBasedDatasourceConfigDtoPA.class);


	/* Properties */
	public ValueProvider<FormatBasedDatasourceConfigDto,List<DatasourceConnectorConfigDto>> connectorConfig();


}
