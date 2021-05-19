package net.datenwerke.rs.scheduleasfile.client.scheduleasfile;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gxtdto.client.objectinformation.hooks.ObjectInfoKeyInfoProvider;
import net.datenwerke.gxtdto.client.waitonevent.WaitOnEventUIService;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.client.reportexporter.hooks.ExportExternalEntryProviderHook;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.filter.TeamspaceFilter;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.hookers.ExportToTeamSpaceHooker;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.hookers.HandleExecutedReportFileHooker;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.hookers.TSMenuDownloadHooker;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.hookers.TeamSpaceExportSnippetProviderHook;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.objectinfo.ExecutedReportObjectInfoHooker;
import net.datenwerke.rs.scheduler.client.scheduler.hooks.ScheduleExportSnippetProviderHook;
import net.datenwerke.rs.scheduler.client.scheduler.schedulereportlist.hooks.ScheduledReportListFilter;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.hooks.GeneralReferenceHandlerHook;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.hooks.TsFavoriteMenuHook;

public class ScheduleAsFileUiStartup {

	@Inject
	public ScheduleAsFileUiStartup(
		final HookHandlerService hookHandler,
		
		Provider<TeamSpaceExportSnippetProviderHook> teamspaceExportSnippetProvider,
		Provider<HandleExecutedReportFileHooker> handleExecutedFileProvider,
		
		Provider<ExecutedReportObjectInfoHooker> executedReportObjectInfo,
		Provider<TSMenuDownloadHooker> tsMenuDownloadHooker, 
		
		Provider<TeamspaceFilter> teamSpaceFilter,
		
		final Provider<ExportToTeamSpaceHooker> exportToTeamSpaceHooker,
		
		final WaitOnEventUIService waitOnEventService
		){
		
		hookHandler.attachHooker(ScheduledReportListFilter.class, teamSpaceFilter);
		
		/* export */
		hookHandler.attachHooker(ExportExternalEntryProviderHook.class, exportToTeamSpaceHooker, HookHandlerService.PRIORITY_HIGH + 30);

		hookHandler.attachHooker(ScheduleExportSnippetProviderHook.class, teamspaceExportSnippetProvider, HookHandlerService.PRIORITY_HIGH + 10);
		
		hookHandler.attachHooker(GeneralReferenceHandlerHook.class, handleExecutedFileProvider);
		
		hookHandler.attachHooker(TsFavoriteMenuHook.class, tsMenuDownloadHooker);
		
		/* object info */
		hookHandler.attachHooker(ObjectInfoKeyInfoProvider.class, executedReportObjectInfo);


	}
}
