package net.datenwerke.rs.birt.client.datasources.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.String;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.birt.client.datasources.dto.BirtReportDatasourceConfigDto;
import net.datenwerke.rs.birt.client.datasources.dto.BirtReportDatasourceTargetTypeDto;
import net.datenwerke.rs.birt.client.reportengines.dto.BirtReportDto;
import net.datenwerke.rs.core.client.datasourcemanager.dto.pa.DatasourceDefinitionConfigDtoPA;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.birt.service.datasources.birtreport.entities.BirtReportDatasourceConfig.class)
public interface BirtReportDatasourceConfigDtoPA extends DatasourceDefinitionConfigDtoPA {


	public static final BirtReportDatasourceConfigDtoPA INSTANCE = GWT.create(BirtReportDatasourceConfigDtoPA.class);


	/* Properties */
	public ValueProvider<BirtReportDatasourceConfigDto,String> queryWrapper();
	public ValueProvider<BirtReportDatasourceConfigDto,BirtReportDto> report();
	public ValueProvider<BirtReportDatasourceConfigDto,String> target();
	public ValueProvider<BirtReportDatasourceConfigDto,BirtReportDatasourceTargetTypeDto> targetType();


}
