package net.datenwerke.rs.reportdoc.client.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.reportdoc.client.dto.DeployAnalyzeCommandResultExtensionDto;
import net.datenwerke.rs.terminal.client.terminal.dto.pa.CommandResultExtensionDtoPA;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.reportdoc.service.terminal.commands.DeployAnalyzeCommandResultExtension.class)
public interface DeployAnalyzeCommandResultExtensionDtoPA extends CommandResultExtensionDtoPA {


	public static final DeployAnalyzeCommandResultExtensionDtoPA INSTANCE = GWT.create(DeployAnalyzeCommandResultExtensionDtoPA.class);


	/* Properties */
	public ValueProvider<DeployAnalyzeCommandResultExtensionDto,Boolean> ignoreCase();
	public ValueProvider<DeployAnalyzeCommandResultExtensionDto,ReportDto> leftReport();
	public ValueProvider<DeployAnalyzeCommandResultExtensionDto,ReportDto> rightReport();


}
