package net.datenwerke.rs.reportdoc.client.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.util.List;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.reportdoc.client.dto.VariantTestCommandResultExtensionDto;
import net.datenwerke.rs.terminal.client.terminal.dto.pa.CommandResultExtensionDtoPA;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.reportdoc.service.terminal.commands.VariantTestCommandResultExtension.class)
public interface VariantTestCommandResultExtensionDtoPA extends CommandResultExtensionDtoPA {


	public static final VariantTestCommandResultExtensionDtoPA INSTANCE = GWT.create(VariantTestCommandResultExtensionDtoPA.class);


	/* Properties */
	public ValueProvider<VariantTestCommandResultExtensionDto,List<DatasourceDefinitionDto>> datasources();
	public ValueProvider<VariantTestCommandResultExtensionDto,ReportDto> report();


}
