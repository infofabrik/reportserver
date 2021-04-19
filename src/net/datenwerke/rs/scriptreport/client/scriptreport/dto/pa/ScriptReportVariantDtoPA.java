package net.datenwerke.rs.scriptreport.client.scriptreport.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.scriptreport.client.scriptreport.dto.ScriptReportVariantDto;
import net.datenwerke.rs.scriptreport.client.scriptreport.dto.decorator.ScriptReportVariantDtoDec;
import net.datenwerke.rs.scriptreport.client.scriptreport.dto.pa.ScriptReportDtoPA;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.scriptreport.service.scriptreport.entities.ScriptReportVariant.class)
public interface ScriptReportVariantDtoPA extends ScriptReportDtoPA {


	public static final ScriptReportVariantDtoPA INSTANCE = GWT.create(ScriptReportVariantDtoPA.class);


	/* Properties */
	public ValueProvider<ScriptReportVariantDto,ReportDto> baseReport();


}
