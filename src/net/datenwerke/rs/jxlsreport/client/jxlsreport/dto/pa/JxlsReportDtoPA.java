package net.datenwerke.rs.jxlsreport.client.jxlsreport.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.pa.ReportDtoPA;
import net.datenwerke.rs.jxlsreport.client.jxlsreport.dto.JxlsReportDto;
import net.datenwerke.rs.jxlsreport.client.jxlsreport.dto.JxlsReportFileDto;
import net.datenwerke.rs.jxlsreport.client.jxlsreport.dto.decorator.JxlsReportDtoDec;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.jxlsreport.service.jxlsreport.entities.JxlsReport.class)
public interface JxlsReportDtoPA extends ReportDtoPA {


	public static final JxlsReportDtoPA INSTANCE = GWT.create(JxlsReportDtoPA.class);


	/* Properties */
	public ValueProvider<JxlsReportDto,Boolean> jxlsOne();
	public ValueProvider<JxlsReportDto,JxlsReportFileDto> reportFile();


}
