package net.datenwerke.rs.base.client.reportengines.jasper.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.util.List;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.base.client.reportengines.jasper.dto.JasperReportDto;
import net.datenwerke.rs.base.client.reportengines.jasper.dto.JasperReportJRXMLFileDto;
import net.datenwerke.rs.base.client.reportengines.jasper.dto.decorator.JasperReportDtoDec;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.pa.ReportDtoPA;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.base.service.reportengines.jasper.entities.JasperReport.class)
public interface JasperReportDtoPA extends ReportDtoPA {


	public static final JasperReportDtoPA INSTANCE = GWT.create(JasperReportDtoPA.class);


	/* Properties */
	public ValueProvider<JasperReportDto,JasperReportJRXMLFileDto> masterFile();
	public ValueProvider<JasperReportDto,List<JasperReportJRXMLFileDto>> subFiles();


}
