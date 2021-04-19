package net.datenwerke.rs.base.ext.client.dashboardmanager.eximport.ex.rpc;

import com.google.gwt.user.client.rpc.AsyncCallback;

import net.datenwerke.rs.dashboard.client.dashboard.dto.AbstractDashboardManagerNodeDto;

public interface DashboardManagerExportRpcServiceAsync {

	void quickExport(AbstractDashboardManagerNodeDto dto,
			AsyncCallback<Void> callback);

	void loadResult(AsyncCallback<String> callback);

}
