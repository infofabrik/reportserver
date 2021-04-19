package net.datenwerke.rs.reportdoc.client.terminal.commandproc;

import java.util.List;

import com.google.inject.Inject;

import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.reportdoc.client.ReportDocumentationUiService;
import net.datenwerke.rs.reportdoc.client.dto.VariantTestCommandResultExtensionDto;
import net.datenwerke.rs.terminal.client.terminal.dto.CommandResultDto;
import net.datenwerke.rs.terminal.client.terminal.hooks.CommandResultProcessorHook;

public class VariantTestTerminalCommandProcessor implements CommandResultProcessorHook {
	private final ReportDocumentationUiService reportDocService;

	@Inject
	public VariantTestTerminalCommandProcessor(ReportDocumentationUiService reportDocService) {
		this.reportDocService = reportDocService;
	}

	@Override
	public void process(CommandResultDto result) {
		if (result.getExtensions().size() == 1
				&& result.getExtensions().get(0) instanceof VariantTestCommandResultExtensionDto) {
			final VariantTestCommandResultExtensionDto variantTestCmd = (VariantTestCommandResultExtensionDto) result
					.getExtensions().get(0);
			final ReportDto report = variantTestCmd.getReport();
			final List<DatasourceDefinitionDto> datasources = variantTestCmd.getDatasources();

			reportDocService.openVariantTestForopen(report, datasources);

		}

	}

}