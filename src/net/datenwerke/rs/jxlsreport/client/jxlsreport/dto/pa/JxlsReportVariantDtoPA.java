package net.datenwerke.rs.jxlsreport.client.jxlsreport.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.jxlsreport.client.jxlsreport.dto.JxlsReportVariantDto;
import net.datenwerke.rs.jxlsreport.client.jxlsreport.dto.decorator.JxlsReportVariantDtoDec;
import net.datenwerke.rs.jxlsreport.client.jxlsreport.dto.pa.JxlsReportDtoPA;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.jxlsreport.service.jxlsreport.entities.JxlsReportVariant.class)
public interface JxlsReportVariantDtoPA extends JxlsReportDtoPA {


	public static final JxlsReportVariantDtoPA INSTANCE = GWT.create(JxlsReportVariantDtoPA.class);


	/* Properties */
	public ValueProvider<JxlsReportVariantDto,ReportDto> baseReport();


}
