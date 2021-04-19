package net.datenwerke.rs.scripting.client.scripting.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.String;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.scripting.client.scripting.dto.AddReportExportFormatProviderDto;
import net.datenwerke.rs.terminal.client.terminal.dto.pa.CommandResultExtensionDtoPA;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.scripting.service.scripting.extensions.AddReportExportFormatProvider.class)
public interface AddReportExportFormatProviderDtoPA extends CommandResultExtensionDtoPA {


	public static final AddReportExportFormatProviderDtoPA INSTANCE = GWT.create(AddReportExportFormatProviderDtoPA.class);


	/* Properties */
	public ValueProvider<AddReportExportFormatProviderDto,String> description();
	public ValueProvider<AddReportExportFormatProviderDto,String> icon();
	public ValueProvider<AddReportExportFormatProviderDto,String> outputFormat();
	public ValueProvider<AddReportExportFormatProviderDto,ReportDto> reportType();
	public ValueProvider<AddReportExportFormatProviderDto,Boolean> skipDownload();
	public ValueProvider<AddReportExportFormatProviderDto,String> title();


}
