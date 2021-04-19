package net.datenwerke.rs.base.ext.service.parameters.fileselection;

import net.datenwerke.gf.service.download.hooks.FileDownloadHandlerHook;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.base.ext.client.parameters.fileselection.FileSelectionParameterConfigurator;
import net.datenwerke.rs.base.ext.service.parameters.fileselection.hookers.FileSelectionParameterAdjustVariantHooker;
import net.datenwerke.rs.base.ext.service.parameters.fileselection.hookers.FileSelectionParameterDownloadHooker;
import net.datenwerke.rs.base.ext.service.parameters.fileselection.hookers.FileSelectionParameterInstanceCreatedFromDtoHooker;
import net.datenwerke.rs.base.ext.service.parameters.fileselection.hookers.FileSelectionParameterReportFromDtoHooker;
import net.datenwerke.rs.core.service.reportmanager.hooks.ParameterInstanceCreatedFromDtoHook;
import net.datenwerke.rs.core.service.reportmanager.hooks.ReportCreatedFromDtoHook;
import net.datenwerke.rs.core.service.reportmanager.hooks.VariantCreatorHook;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class FileSelectionParameterStartup {

	@Inject
	public FileSelectionParameterStartup(
		HookHandlerService hookHandler,
		
		Provider<FileSelectionParameterReportFromDtoHooker> reportFromDtoHooker,
		Provider<FileSelectionParameterInstanceCreatedFromDtoHooker> instanceCreatedHooker, 
		Provider<FileSelectionParameterAdjustVariantHooker> adjustVariantHooker,
		
		Provider<FileSelectionParameterDownloadHooker> downloadHooker
		) {

		hookHandler.attachHooker(ReportCreatedFromDtoHook.class, reportFromDtoHooker);
		hookHandler.attachHooker(ParameterInstanceCreatedFromDtoHook.class, instanceCreatedHooker);
		
		hookHandler.attachHooker(VariantCreatorHook.class, adjustVariantHooker);
		
		hookHandler.attachHooker(FileDownloadHandlerHook.class, downloadHooker);
	}
}
