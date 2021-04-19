package net.datenwerke.rs.saiku.client.datasource.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.String;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.core.client.datasourcemanager.dto.pa.DatasourceDefinitionConfigDtoPA;
import net.datenwerke.rs.saiku.client.datasource.dto.MondrianDatasourceConfigDto;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.saiku.service.datasource.MondrianDatasourceConfig.class)
public interface MondrianDatasourceConfigDtoPA extends DatasourceDefinitionConfigDtoPA {


	public static final MondrianDatasourceConfigDtoPA INSTANCE = GWT.create(MondrianDatasourceConfigDtoPA.class);


	/* Properties */
	public ValueProvider<MondrianDatasourceConfigDto,String> cubeName();


}
