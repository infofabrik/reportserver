package net.datenwerke.rs.dashboard.client.dashboard.hookers;

import java.util.Arrays;
import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.managerhelper.hooks.MainPanelViewProviderHook;
import net.datenwerke.gf.client.managerhelper.mainpanel.MainPanelView;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DadgetNodeDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardFolderDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardNodeDto;
import net.datenwerke.rs.dashboard.client.dashboard.ui.admin.forms.DadgetNodeForm;
import net.datenwerke.rs.dashboard.client.dashboard.ui.admin.forms.DashboardNodeForm;
import net.datenwerke.rs.dashboard.client.dashboard.ui.admin.forms.FolderForm;
import net.datenwerke.security.ext.client.security.ui.SecurityView;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

public class MainPanelViewProviderHooker implements MainPanelViewProviderHook {

	private final Provider<FolderForm> folderFormProvider;
	
	private final Provider<SecurityView> securityViewProvider;

	private final Provider<DadgetNodeForm> dadgetNodeFormProvider;

	private final  Provider<DashboardNodeForm> dashboardNodeFormProvider;
	
	
	@Inject
	public MainPanelViewProviderHooker(
			Provider<FolderForm> folderFormProvider,
			Provider<DadgetNodeForm> dadgetNodeFormProvider,
			Provider<DashboardNodeForm> dashboardNodeFormProvider,
			
			Provider<SecurityView> securityViewProvider
		){

		/* store objects */
		this.folderFormProvider = folderFormProvider;
		this.dadgetNodeFormProvider = dadgetNodeFormProvider;
		this.dashboardNodeFormProvider = dashboardNodeFormProvider;
		this.securityViewProvider = securityViewProvider;
	}
	
	public List<MainPanelView> mainPanelViewProviderHook_getView(AbstractNodeDto node) {
		if(node instanceof DashboardFolderDto)
			return getViewForFolder();
		if(node instanceof DadgetNodeDto)
			return getViewForDadgetNode();
		if(node instanceof DashboardNodeDto)
			return getViewForDashboardNode();
		return null;
	}

	private List<MainPanelView> getViewForDashboardNode() {
		return Arrays.asList(dashboardNodeFormProvider.get(), securityViewProvider.get());
	}

	private List<MainPanelView> getViewForDadgetNode() {
		return Arrays.asList(dadgetNodeFormProvider.get(), securityViewProvider.get());
	}

	private List<MainPanelView> getViewForFolder() {
		return Arrays.asList(folderFormProvider.get(), securityViewProvider.get());
	}

}
