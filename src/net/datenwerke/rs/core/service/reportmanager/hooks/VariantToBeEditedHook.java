package net.datenwerke.rs.core.service.reportmanager.hooks;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.hookservices.annotations.HookConfig;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;

@HookConfig
public interface VariantToBeEditedHook extends Hook {

	/**
	 * Called just before the edits in a variant are going to be saved.
	 * 
	 * @param referenceVariant the original, not yet saved report. This shows the
	 *                         current state of the report in the database.
	 * @param reportDto        the report with the changes. 
	 * @param executorToken    the executor token.
	 * @throws ServerCallFailedException
	 */
	public void variantToBeEdited(Report referenceVariant, ReportDto reportDto, String executorToken) throws ServerCallFailedException;

}
