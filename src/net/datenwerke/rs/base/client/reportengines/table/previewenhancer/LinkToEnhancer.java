package net.datenwerke.rs.base.client.reportengines.table.previewenhancer;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.http.client.URL;
import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;

import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenuItem;
import net.datenwerke.gxtdto.client.utilityservices.UtilsUIService;
import net.datenwerke.gxtdto.client.utils.StringEscapeUtils;
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.TableReportDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.decorator.TableReportDtoDec;
import net.datenwerke.rs.base.client.reportengines.table.hooks.TableReportPreviewCellEnhancerHook;
import net.datenwerke.rs.base.client.reportengines.table.ui.TableReportPreviewView;
import net.datenwerke.rs.core.client.reportexecutor.locale.ReportexecutorMessages;

public class LinkToEnhancer implements TableReportPreviewCellEnhancerHook {

	private final UtilsUIService utilsService;
	
	@Inject
	public LinkToEnhancer(
		UtilsUIService utilsService
		){
		
		/* store objects */
		this.utilsService = utilsService;
	}
	
	@Override
	public boolean consumes(TableReportDto report, ColumnDto column, String value, String rawValue, String[] rowValues) {
		return null != column.getSemanticType() && column.getSemanticType().toLowerCase().startsWith("linkto|");
	}

	@Override
	public boolean enhanceMenu(TableReportPreviewView tableReportPreviewView, Menu menu, TableReportDto report, final ColumnDto column, final String value, String rawValue, String[] rowValues) {
		MenuItem lookupItem = new DwMenuItem(ReportexecutorMessages.INSTANCE.linktomsg());
		menu.add(lookupItem);
		
		String semanticType = column.getSemanticType();
		String url = semanticType.substring(7, semanticType.length());
		url = StringEscapeUtils.unescapeXml(url);
		
		url = replaceValues(report, url, value, rowValues);
		
		final String target = url;
		
		lookupItem.addSelectionHandler(new SelectionHandler<Item>() {
			
			@Override
			public void onSelection(SelectionEvent<Item> event) {
				utilsService.redirectInPopup(target);
			}
		});
		
		return false;
	}

	private String replaceValues(TableReportDto report, String url, String value, String[] rowValues) {
		String replacementUrl = url.replace("${_value}", URL.encode(value));
		
		int startIndex = url.indexOf("${");
		int endIndex = url.indexOf("}");
		while(startIndex >= 0) {
		     String colName = url.substring(startIndex+2, endIndex);
		     ColumnDto col = ((TableReportDtoDec)report).getVisibleColumnByName(colName);
		     if (null != col) {
		    	 int colIndex = ((TableReportDtoDec)report).getVisibleColumnPositionByName(colName);
		    	 replacementUrl = replacementUrl.replace("${" + colName + "}", URL.encode(rowValues[colIndex]));
		     } 
		     startIndex = url.indexOf("${", endIndex+1);
		     endIndex = url.indexOf("}", endIndex+1);
		}
		
		return replacementUrl;
	}

}
