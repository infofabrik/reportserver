package net.datenwerke.rs.dashboard.client.dashboard;

import com.google.inject.ImplementedBy;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.loader.ListLoadConfig;
import com.sencha.gxt.data.shared.loader.ListLoadResult;
import com.sencha.gxt.data.shared.loader.ListLoader;
import com.sencha.gxt.widget.core.client.button.IconButton;

import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardDto;
import net.datenwerke.rs.dashboard.client.dashboard.hooks.DadgetProcessorHook;
import net.datenwerke.rs.dashboard.client.dashboard.ui.DadgetPanel;

@ImplementedBy(DashboardUiServiceImpl.class)
public interface DashboardUiService {

	IconButton addParameterToolButtonTo(DadgetPanel dadgetPanel, DadgetProcessorHook dadgetProcessor);

	void showHideParameterToolButton(DadgetPanel panel, ReportDto report);

	ListStore<DashboardDto> getAllDashboardsStore();

	ListLoader<ListLoadConfig, ListLoadResult<DashboardDto>> getAllDashboardsLoader();

	ListStore<DashboardDto> getDashboardStore();

	ListLoader<ListLoadConfig, ListLoadResult<DashboardDto>> getDashboardsLoader();

}
