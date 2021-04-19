package net.datenwerke.rs.dashboard.client.dashboard;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.sencha.gxt.data.shared.loader.ListLoadResult;

import net.datenwerke.gf.client.history.HistoryLocation;
import net.datenwerke.gxtdto.client.dtomanager.Dao;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.rs.core.client.parameters.dto.ParameterInstanceDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DadgetDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardContainerDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardNodeDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardReferenceDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.FavoriteListDto;
import net.datenwerke.rs.dashboard.client.dashboard.rpc.DashboardRpcServiceAsync;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.AbstractTsDiskNodeDto;

public class DashboardDao extends Dao {

	private final DashboardRpcServiceAsync asyncService;

	@Inject
	public DashboardDao(DashboardRpcServiceAsync asyncService) {
		this.asyncService = asyncService;
	}
	
	public void getDashboardForUser(AsyncCallback<DashboardContainerDto> callback){
		asyncService.getDashboardForUser(transformAndKeepCallback(callback));
	}
	
	public void getExplicitPrimaryDashboard(AsyncCallback<DashboardDto> callback){
		asyncService.getExplicitPrimaryDashboard(transformAndKeepCallback(callback));
	}
	
	public void createDashboardForUser(AsyncCallback<DashboardDto> callback){
		asyncService.createDashboardForUser(transformAndKeepCallback(callback));
	}

	public void removeDashboard(DashboardDto dashboard,
			AsyncCallback<Void> callback){
		asyncService.removeDashboard(dashboard, transformAndKeepCallback(callback));
	}

	public void editDashboard(DashboardDto dashboard,
			AsyncCallback<DashboardDto> callback){
		asyncService.editDashboard(dashboard, transformAndKeepCallback(callback));
	}
	
	public void addDadgetToDashboard(DashboardDto dashboard, DadgetDto dadget,
			AsyncCallback<DashboardDto> callback){
		asyncService.addDadgetToDashboard(dashboard, dadget, transformAndKeepCallback(callback));
	}
	
	public void removeDadgetFromDashboard(DashboardDto dashboard, DadgetDto dadget,
			AsyncCallback<DashboardDto> callback){
		asyncService.removeDadgetFromDashboard(dashboard, dadget, transformAndKeepCallback(callback));
	}
	
	public void editDadgetToDashboard(DashboardDto dashboardDto, DadgetDto dadget,
			AsyncCallback<DashboardDto> callback){
		asyncService.editDadgetToDashboard(dashboardDto, dadget, transformAndKeepCallback(callback));
	}
	
	public void editDashboards(ArrayList<DashboardDto> dashboardDtos, AsyncCallback<Void> callback){
		asyncService.editDashboards(dashboardDtos, transformAndKeepCallback(callback));
	}
	
	public void addToFavorites(AbstractTsDiskNodeDto node, AsyncCallback<Void> callback){
		asyncService.addToFavorites(node, transformAndKeepCallback(callback));
	}
	
	public void removeFromFavorites(AbstractTsDiskNodeDto node, AsyncCallback<Void> callback){
		asyncService.removeFromFavorites(node, transformAndKeepCallback(callback));
	}
	
	public void loadFavorites(AsyncCallback<FavoriteListDto> callback){
		asyncService.loadFavorites(transformAndKeepCallback(callback));
	}
	
	public void importReferencedDashboardForUser(DashboardNodeDto dashboard,
			AsyncCallback<DashboardDto> callback){
		asyncService.importReferencedDashboardForUser(dashboard, transformAndKeepCallback(callback));
	}
	
	public void reloadDashboard(DashboardDto dashboard, AsyncCallback<DashboardDto> callback){
		asyncService.reloadDashboard(dashboard, transformAndKeepCallback(callback));
	}

	public void getDashboardParameterInstances(DashboardDto dashboardDto, AsyncCallback<Map<String,ParameterInstanceDto>> callback){
		asyncService.getDashboardParameterInstances(dashboardDto, transformAndKeepCallback(callback));
	}

	public void setDashboardParameterInstances(DashboardDto dashboardDto, Set<ParameterInstanceDto> parameterInstances, AsyncCallback<List<DadgetDto>> callback){
		asyncService.setDashboardParameterInstances(dashboardDto, parameterInstances, transformAndKeepCallback(callback));
	}

	public void getDadgetParameterInstances(DadgetDto dadgetDto, AsyncCallback<Map<String, ParameterInstanceDto>> callback){
		asyncService.getDadgetParameterInstances(dadgetDto, transformAndKeepCallback(callback));
	}

	public void setDadgetParameterInstances(DadgetDto dadgetDto, Set<ParameterInstanceDto> parameterInstances,
			AsyncCallback<DadgetDto> callback){
		asyncService.setDadgetParameterInstances(dadgetDto, parameterInstances, transformAndKeepCallback(callback));
	}
	
	public void resetReferencedDashboard(DashboardReferenceDto dashboardDto, AsyncCallback<DashboardDto> callback){
		asyncService.resetReferencedDashboard(dashboardDto, transformAndKeepCallback(callback));
	}

	public void loadDashboardForDisplay(DashboardDto dashboard, AsyncCallback<DashboardDto> callback) {
		asyncService.loadDashboardForDisplay(dashboard, transformAndKeepCallback(callback));
	}
	
	public void loadDashboardForDisplayFrom(HistoryLocation location, AsyncCallback<DashboardDto> callback) {
		asyncService.loadDashboardForDisplayFrom(location, transformAndKeepCallback(callback));
	}
	
	public void changeDashboardOrder(ArrayList<Long> dashboardIds, AsyncCallback<Void> callback) {
		asyncService.changeDashboardOrder(dashboardIds, callback);
	}
	
	public void loadAllDashboards(AsyncCallback<ListLoadResult<DashboardDto>> callback) {
		asyncService.loadAllDashboards(transformAndKeepCallback(callback));
	}
	
	public void loadDashboards(AsyncCallback<ListLoadResult<DashboardDto>> callback) {
		asyncService.loadDashboards(transformAndKeepCallback(callback));
	}
	
	public void setPrimaryDashboard(DashboardDto dashboardDto, RsAsyncCallback<Void> callback) {
		asyncService.setPrimaryDashboard(dashboardDto, transformAndKeepCallback(callback));
	}

}
