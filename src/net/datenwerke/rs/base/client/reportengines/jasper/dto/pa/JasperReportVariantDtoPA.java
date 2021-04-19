package net.datenwerke.rs.base.client.reportengines.jasper.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.base.client.reportengines.jasper.dto.JasperReportVariantDto;
import net.datenwerke.rs.base.client.reportengines.jasper.dto.decorator.JasperReportVariantDtoDec;
import net.datenwerke.rs.base.client.reportengines.jasper.dto.pa.JasperReportDtoPA;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.base.service.reportengines.jasper.entities.JasperReportVariant.class)
public interface JasperReportVariantDtoPA extends JasperReportDtoPA {


	public static final JasperReportVariantDtoPA INSTANCE = GWT.create(JasperReportVariantDtoPA.class);


	/* Properties */
	public ValueProvider<JasperReportVariantDto,ReportDto> baseReport();


}
