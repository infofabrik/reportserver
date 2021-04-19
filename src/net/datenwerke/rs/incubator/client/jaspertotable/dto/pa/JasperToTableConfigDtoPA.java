package net.datenwerke.rs.incubator.client.jaspertotable.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.Boolean;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceContainerDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.pa.ReportPropertyDtoPA;
import net.datenwerke.rs.incubator.client.jaspertotable.dto.JasperToTableConfigDto;
import net.datenwerke.rs.incubator.client.jaspertotable.dto.decorator.JasperToTableConfigDtoDec;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.incubator.service.jaspertotable.entities.JasperToTableConfig.class)
public interface JasperToTableConfigDtoPA extends ReportPropertyDtoPA {


	public static final JasperToTableConfigDtoPA INSTANCE = GWT.create(JasperToTableConfigDtoPA.class);


	/* Properties */
	public ValueProvider<JasperToTableConfigDto,DatasourceContainerDto> datasourceContainer();
	public ValueProvider<JasperToTableConfigDto,Boolean> active();


}
