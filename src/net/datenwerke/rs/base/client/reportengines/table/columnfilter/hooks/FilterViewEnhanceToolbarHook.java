package net.datenwerke.rs.base.client.reportengines.table.columnfilter.hooks;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.rs.base.client.reportengines.table.columnfilter.propertywidgets.FilterView;
import net.datenwerke.rs.base.client.reportengines.table.dto.TableReportDto;

import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

public interface FilterViewEnhanceToolbarHook extends Hook {

	void enhanceToolbarLeft(ToolBar toolbar);

	void enhanceToolbarRight(ToolBar toolbar);

	void initialize(FilterView filterView, TableReportDto report);
	
}
