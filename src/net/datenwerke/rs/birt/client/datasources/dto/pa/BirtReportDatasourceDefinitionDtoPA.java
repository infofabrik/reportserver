package net.datenwerke.rs.birt.client.datasources.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.birt.client.datasources.dto.BirtReportDatasourceDefinitionDto;
import net.datenwerke.rs.core.client.datasourcemanager.dto.pa.DatasourceDefinitionDtoPA;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.birt.service.datasources.birtreport.entities.BirtReportDatasourceDefinition.class)
public interface BirtReportDatasourceDefinitionDtoPA extends DatasourceDefinitionDtoPA {


	public static final BirtReportDatasourceDefinitionDtoPA INSTANCE = GWT.create(BirtReportDatasourceDefinitionDtoPA.class);


	/* Properties */
	public ValueProvider<BirtReportDatasourceDefinitionDto,Integer> databaseCache();


}
