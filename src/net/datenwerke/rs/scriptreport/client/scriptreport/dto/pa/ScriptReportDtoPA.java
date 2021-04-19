package net.datenwerke.rs.scriptreport.client.scriptreport.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.String;
import java.util.List;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.pa.ReportDtoPA;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFileDto;
import net.datenwerke.rs.scriptreport.client.scriptreport.dto.ScriptReportDto;
import net.datenwerke.rs.scriptreport.client.scriptreport.dto.decorator.ScriptReportDtoDec;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.scriptreport.service.scriptreport.entities.ScriptReport.class)
public interface ScriptReportDtoPA extends ReportDtoPA {


	public static final ScriptReportDtoPA INSTANCE = GWT.create(ScriptReportDtoPA.class);


	/* Properties */
	public ValueProvider<ScriptReportDto,String> arguments();
	public ValueProvider<ScriptReportDto,List<String>> exportFormats();
	public ValueProvider<ScriptReportDto,FileServerFileDto> script();


}
