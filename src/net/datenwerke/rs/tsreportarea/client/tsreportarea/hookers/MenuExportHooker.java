package net.datenwerke.rs.tsreportarea.client.tsreportarea.hookers;

import java.util.List;

import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;

import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenu;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenuItem;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.rs.core.client.reportexecutor.ReportExecutorDao;
import net.datenwerke.rs.core.client.reportexporter.ReportExporterUIService;
import net.datenwerke.rs.core.client.reportexporter.exporter.ReportExporter;
import net.datenwerke.rs.core.client.reportexporter.exporter.ReportExporter.ConfigurationFinishedCallback;
import net.datenwerke.rs.core.client.reportmanager.ReportManagerTreeLoaderDao;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.teamspace.client.teamspace.TeamSpaceUIService;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.AbstractTsDiskNodeDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskReportReferenceDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.hooks.TsFavoriteMenuHook;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.locale.TsFavoriteMessages;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.ui.ItemSelector;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.ui.TsDiskMainComponent;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

public class MenuExportHooker implements TsFavoriteMenuHook {

	private final TeamSpaceUIService teamSpaceService;
	private final ReportExporterUIService reportExporterService;
	private final ReportExecutorDao reportExecutorDao;
	private final ReportManagerTreeLoaderDao reportLoaderDao;

	
	@Inject
	public MenuExportHooker(
		ReportExporterUIService reportExporterService,
		TeamSpaceUIService teamSpaceService,
		ReportExecutorDao reportExecutorDao,
		ReportManagerTreeLoaderDao reportLoaderDao
		){
	
		this.reportExporterService = reportExporterService;
		this.teamSpaceService = teamSpaceService;
		this.reportExecutorDao = reportExecutorDao;
		this.reportLoaderDao = reportLoaderDao;
	}
	
	
	@Override
	public boolean addContextMenuEntries(Menu menu, final List<AbstractTsDiskNodeDto> items, ItemSelector selector, 
			final TsDiskMainComponent mainComponent) {
		if(null == items || items.isEmpty() || items.size() > 1)
			return false;
		if(! teamSpaceService.isGuest(mainComponent.getCurrentSpace()))
			return false;
		if(! (items.get(0) instanceof TsDiskReportReferenceDto))
			return false;
		
		final TsDiskReportReferenceDto reference = (TsDiskReportReferenceDto) items.get(0);
		
		
		if(null != reference.getReport()){
			final MenuItem exportItem = new DwMenuItem(TsFavoriteMessages.INSTANCE.exportLabel(), BaseIcon.PLAY_CIRCLE_O);
			final Menu subMenu = new DwMenu();
			menu.add(exportItem);
			exportItem.disable();
			exportItem.setSubMenu(subMenu);
			
			/* load reference to ensure we have properties */
			reportLoaderDao.loadNode(reference.getReport(), new RsAsyncCallback<AbstractNodeDto>(){
				@Override
				public void onSuccess(AbstractNodeDto result) {
					super.onSuccess(result);
					
					if(! (result instanceof ReportDto))
						return;
					
					for (final ReportExporter exporter : reportExporterService
							.getCleanedUpAvailableExporters((ReportDto) result)) {
						MenuItem subExportItem = new DwMenuItem(exporter.getExportTitle(), exporter.getIcon());
						subMenu.add(subExportItem);
						subExportItem.addSelectionHandler(event -> {
							exporter.displayConfiguration(reference.getReport(), null, true,
									new ConfigurationFinishedCallback() {
										@Override
										public void success() {
											reportExecutorDao.loadFullReportForExecution(reference.getReport(),
													new RsAsyncCallback<ReportDto>() {
														@Override
														public void onSuccess(ReportDto result) {
															exporter.export(result, null);
														}
													});
										}
									});
						});
					}
					
					if (subMenu.getWidgetCount() > 0)
						exportItem.enable();
					
				}
				
			});

		}
		
		
		return true;
	}

	

}
