package net.datenwerke.rs.grideditor.client.grideditor.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.String;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.pa.ReportDtoPA;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFileDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorReportDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.GridEditorReportDtoDec;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.grideditor.service.grideditor.entities.GridEditorReport.class)
public interface GridEditorReportDtoPA extends ReportDtoPA {


	public static final GridEditorReportDtoPA INSTANCE = GWT.create(GridEditorReportDtoPA.class);


	/* Properties */
	public ValueProvider<GridEditorReportDto,String> arguments();
	public ValueProvider<GridEditorReportDto,FileServerFileDto> script();


}
