package net.datenwerke.rs.base.client.reportengines.table.hooks;

import com.sencha.gxt.widget.core.client.menu.Menu;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.TableReportDto;
import net.datenwerke.rs.base.client.reportengines.table.ui.TableReportPreviewView;

public interface TableReportPreviewCellEnhancerHook extends Hook{

	public boolean consumes(TableReportDto report, ColumnDto column, String value, String rawValue, String[] rowValues);

	public boolean enhanceMenu(TableReportPreviewView tableReportPreviewView, Menu menu, TableReportDto report, ColumnDto column, String rawValue, String value, String[] rowValues);

}
