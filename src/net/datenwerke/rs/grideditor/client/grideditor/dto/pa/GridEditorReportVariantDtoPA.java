package net.datenwerke.rs.grideditor.client.grideditor.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorReportVariantDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.GridEditorReportVariantDtoDec;
import net.datenwerke.rs.grideditor.client.grideditor.dto.pa.GridEditorReportDtoPA;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.grideditor.service.grideditor.entities.GridEditorReportVariant.class)
public interface GridEditorReportVariantDtoPA extends GridEditorReportDtoPA {


	public static final GridEditorReportVariantDtoPA INSTANCE = GWT.create(GridEditorReportVariantDtoPA.class);


	/* Properties */
	public ValueProvider<GridEditorReportVariantDto,ReportDto> baseReport();


}
