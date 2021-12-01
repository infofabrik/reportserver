package net.datenwerke.rs.tsreportarea.client.tsreportarea.ui;

import java.util.Optional;

import com.google.gwt.dom.client.NativeEvent;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;

import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenu;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenuItem;
import net.datenwerke.rs.core.client.reportmanager.dto.interfaces.ReportContainerDto;
import net.datenwerke.rs.core.client.reportmanager.dto.interfaces.ReportVariantDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.client.reportmanager.helper.reportselector.ReportSelectionDialog;
import net.datenwerke.rs.core.client.reportmanager.helper.reportselector.ReportSelectionDialog.ReportSelectionDialogEventHandler;
import net.datenwerke.rs.core.client.reportmanager.hookers.ReportCatalogOnDemandRepositoryProvider;
import net.datenwerke.rs.core.client.reportmanager.hooks.ReportSelectionRepositoryProviderHook;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.locale.TsFavoriteMessages;

/**
 * 
 *
 */
public class ImportReportDialogCreator {

	private final Provider<ReportSelectionDialog> dialogProvider;
	
	@Inject
	public ImportReportDialogCreator(
		Provider<ReportSelectionDialog> dialogProvider
		){
		
		/* store objects */
		this.dialogProvider = dialogProvider;
	}
	
	public void displayDialog(final TsDiskMainComponent mainComponent){
		final ReportSelectionDialog reportSelector = dialogProvider.get();
		reportSelector.initRepositories(Optional.empty(), new ReportCatalogOnDemandRepositoryProvider.Config() {
			@Override
			public boolean includeVariants() {
				return false;
			}
			
			@Override
			public boolean showCatalog() {
				return true;
			}

         @Override
         public boolean filterOnSchedulableReports() {
            return false;
         }

         @Override
         public boolean showEntriesWithUnaccessibleHistoryPath() {
            return true;
         }

         @Override
         public boolean filterOnTeamSpaceImportableReports() {
            return true;
         }
		});
		
		reportSelector.setHeading(TsFavoriteMessages.INSTANCE.importReportText());
		reportSelector.setHeaderIcon(BaseIcon.REPORT_ADD);
		reportSelector.setClosable(true);
		reportSelector.setOnEsc(true);
		
		reportSelector.setEventHandler(new ReportSelectionDialogEventHandler() {
			
			@Override
			public boolean handleSubmit(ReportContainerDto container) {
				return false;
			}
			
			@Override
			public void handleDoubleClick(final ReportContainerDto report,
					ReportSelectionRepositoryProviderHook hooker, NativeEvent event, Object... info) {
				createMenu(report.getReport()).showAt(event.getClientX(), event.getClientY());
			}
			
			@Override
			public Menu getContextMenuFor(final ReportContainerDto report,
					ReportSelectionRepositoryProviderHook hooker, final Object... info) {
				return createMenu(report.getReport());
			}

			private Menu createMenu(final ReportDto report) {
				final Menu menu = new DwMenu();
				
				MenuItem addReference = new DwMenuItem(TsFavoriteMessages.INSTANCE.newReference(), BaseIcon.REPORT_LINK);
				menu.add(addReference);
				addReference.addSelectionHandler( event -> mainComponent.importReport(report, false, true) );
				
				if(report instanceof ReportVariantDto){
					MenuItem addCopy = new DwMenuItem(TsFavoriteMessages.INSTANCE.newCopy(), BaseIcon.REPORT_ADD);
					menu.add(addCopy);
					addCopy.addSelectionHandler( event -> mainComponent.importReport(report, true, false) );
				}
				
				return menu;
			}
		});
		
		reportSelector.show();
	}

}
