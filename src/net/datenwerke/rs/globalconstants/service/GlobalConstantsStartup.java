package net.datenwerke.rs.globalconstants.service;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.eximport.hooks.ExporterProviderHook;
import net.datenwerke.eximport.hooks.ImporterProviderHook;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.service.reportmanager.hooks.ParameterSetReplacementProviderHook;
import net.datenwerke.rs.eximport.service.eximport.hooks.ExportAllHook;
import net.datenwerke.rs.eximport.service.eximport.hooks.ImportAllHook;
import net.datenwerke.rs.globalconstants.service.globalconstants.eximport.GlobalConstantExporter;
import net.datenwerke.rs.globalconstants.service.globalconstants.eximport.GlobalConstantImporter;
import net.datenwerke.rs.globalconstants.service.globalconstants.eximport.hookers.ExportAllGlobalConstantsHooker;
import net.datenwerke.rs.globalconstants.service.globalconstants.eximport.hookers.ImportAllGlobalConstantsHooker;
import net.datenwerke.rs.globalconstants.service.globalconstants.hookers.ParameterSetReplacementProviderHooker;

public class GlobalConstantsStartup {

	@Inject
	public GlobalConstantsStartup(
		HookHandlerService hookHandler,
		Provider<ParameterSetReplacementProviderHooker> replacementProvider,
		
		Provider<GlobalConstantExporter> exporterProvider,
		Provider<GlobalConstantImporter> importerProvider,
		
		Provider<ExportAllGlobalConstantsHooker> exportAllGlobalConstants,
		Provider<ImportAllGlobalConstantsHooker> importAllGlobalConstants
		
		){
		
		hookHandler.attachHooker(ParameterSetReplacementProviderHook.class, replacementProvider);
		
		hookHandler.attachHooker(ExporterProviderHook.class, new ExporterProviderHook(exporterProvider));
		hookHandler.attachHooker(ImporterProviderHook.class, new ImporterProviderHook(importerProvider));
		hookHandler.attachHooker(ExportAllHook.class, exportAllGlobalConstants);
		hookHandler.attachHooker(ImportAllHook.class, importAllGlobalConstants);
	}
}
