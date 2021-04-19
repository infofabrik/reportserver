package net.datenwerke.rs.base.client.reportengines.table.previewenhancer;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;

import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenuItem;
import net.datenwerke.gxtdto.client.utilityservices.UtilsUIService;
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.TableReportDto;
import net.datenwerke.rs.base.client.reportengines.table.hooks.TableReportPreviewCellEnhancerHook;
import net.datenwerke.rs.base.client.reportengines.table.ui.TableReportPreviewView;
import net.datenwerke.rs.core.client.reportexecutor.locale.ReportexecutorMessages;

public class GeoLocationEnhancer implements TableReportPreviewCellEnhancerHook {

	final private UtilsUIService utilsService;
	
	@Inject
	public GeoLocationEnhancer(
		UtilsUIService utilsService
		){
		
		/* store objects */
		this.utilsService = utilsService;
	}
	
	@Override
	public boolean consumes(TableReportDto report, ColumnDto column, String value, String rawValue, String[] rowValues) {
		return null != column.getSemanticType() && "geolocation".equals(column.getSemanticType().toLowerCase());
	}

	@Override
	public boolean enhanceMenu(TableReportPreviewView tableReportPreviewView, Menu menu, TableReportDto report, ColumnDto column, final String value, String rawValue, String[] rowValues) {
		MenuItem lookupItem = new DwMenuItem(ReportexecutorMessages.INSTANCE.geolocationLookupText());
		menu.add(lookupItem);
		lookupItem.addSelectionHandler(new SelectionHandler<Item>() {
			
			@Override
			public void onSelection(SelectionEvent<Item> event) {
				utilsService.redirectInPopup("http://maps.google.de?q=" + value);
			}
		});
		
		return false;
	}

}
