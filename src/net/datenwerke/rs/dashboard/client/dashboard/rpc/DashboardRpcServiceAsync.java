package net.datenwerke.rs.dashboard.client.dashboard.rpc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.data.shared.loader.ListLoadResult;

import net.datenwerke.gf.client.history.HistoryLocation;
import net.datenwerke.rs.core.client.parameters.dto.ParameterInstanceDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DadgetDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardContainerDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardNodeDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardReferenceDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.FavoriteListDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.AbstractTsDiskNodeDto;


public interface DashboardRpcServiceAsync {

	void getDashboardForUser(AsyncCallback<DashboardContainerDto> callback);

	void createDashboardForUser(AsyncCallback<DashboardDto> callback);

	void removeDashboard(DashboardDto dashboard,
			AsyncCallback<Void> callback);
	
	void getExplicitPrimaryDashboard(AsyncCallback<DashboardDto> callback);

	void reloadDashboard(DashboardDto dashboard, AsyncCallback<DashboardDto> callbar);
	
	void editDashboard(DashboardDto dashboard,
			AsyncCallback<DashboardDto> callback);

	void addDadgetToDashboard(DashboardDto dashboard, DadgetDto dadget,
			AsyncCallback<DashboardDto> callback);

	void removeDadgetFromDashboard(DashboardDto dashboard, DadgetDto dadget,
			AsyncCallback<DashboardDto> callback);

	void editDadgetToDashboard(DashboardDto dashboardDto, DadgetDto dadget,
			AsyncCallback<DashboardDto> callback);

	void addToFavorites(AbstractTsDiskNodeDto node, AsyncCallback<Void> callback);
	
	void removeFromFavorites(AbstractTsDiskNodeDto node, AsyncCallback<Void> callback);

	void loadFavorites(AsyncCallback<FavoriteListDto> callback);

	void importReferencedDashboardForUser(DashboardNodeDto dashboard,
			AsyncCallback<DashboardDto> callback);

	void editDashboards(ArrayList<DashboardDto> dashboards,
			AsyncCallback<Void> callback);

	void getDashboardParameterInstances(DashboardDto dashboardDto, AsyncCallback<Map<String,ParameterInstanceDto>> callback);

	void setDashboardParameterInstances(DashboardDto dashboardDto, Set<ParameterInstanceDto> parameterInstances, AsyncCallback<List<DadgetDto>> callback);

	void getDadgetParameterInstances(DadgetDto dadgetDto, AsyncCallback<Map<String, ParameterInstanceDto>> callback);

	void setDadgetParameterInstances(DadgetDto dadgetDto, Set<ParameterInstanceDto> parameterInstances,
			AsyncCallback<DadgetDto> callback);

	void resetReferencedDashboard(DashboardReferenceDto dashboardDto, AsyncCallback<DashboardDto> callback);

	void loadDashboardForDisplay(DashboardDto dashboard, AsyncCallback<DashboardDto> transformAndKeepCallback);

	void changeDashboardOrder(ArrayList<Long> dashboardIds, AsyncCallback<Void> callback);

	void loadDashboardForDisplayFrom(HistoryLocation location, AsyncCallback<DashboardDto> callback);

	void loadAllDashboards(AsyncCallback<ListLoadResult<DashboardDto>> callback);
	
	void loadDashboards(AsyncCallback<ListLoadResult<DashboardDto>> callback);
	
	void setPrimaryDashboard(DashboardDto dashboardDto, AsyncCallback<Void> callback);
	
}
