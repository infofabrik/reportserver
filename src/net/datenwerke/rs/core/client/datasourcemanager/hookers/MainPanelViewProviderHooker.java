package net.datenwerke.rs.core.client.datasourcemanager.hookers;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.managerhelper.hooks.MainPanelViewProviderHook;
import net.datenwerke.gf.client.managerhelper.mainpanel.MainPanelView;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionDto;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceFolderDto;
import net.datenwerke.rs.core.client.datasourcemanager.hooks.DatasourceDefinitionConfigProviderHook;
import net.datenwerke.rs.core.client.datasourcemanager.ui.forms.FolderForm;
import net.datenwerke.security.ext.client.security.ui.SecurityView;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

public class MainPanelViewProviderHooker implements MainPanelViewProviderHook {

	private final HookHandlerService hookHandler;
	private final Provider<FolderForm> folderFormProvider;
	
	private final Provider<SecurityView> securityViewProvider;
	
	
	@Inject
	public MainPanelViewProviderHooker(
			HookHandlerService hookHandler,
			Provider<FolderForm> folderFormProvider,
			
			Provider<SecurityView> securityViewProvider
		){

		/* store objects */
		this.hookHandler = hookHandler;
		this.folderFormProvider = folderFormProvider;
		this.securityViewProvider = securityViewProvider;
	}
	
	public List<MainPanelView> mainPanelViewProviderHook_getView(AbstractNodeDto node) {
		if(node instanceof DatasourceFolderDto)
			return getViewForDatasourceFolder();
		if(node instanceof DatasourceDefinitionDto)
			return getViewForDatasources((DatasourceDefinitionDto) node);
		return null;
	}

	private List<MainPanelView> getViewForDatasources(final DatasourceDefinitionDto dsd) {
		final List<MainPanelView> views = new ArrayList<>();
		
		views.addAll(
			hookHandler.getHookers(DatasourceDefinitionConfigProviderHook.class)
				.stream()
				.filter(config -> config.consumes(dsd))
				.flatMap(config -> config.getAdminViews(dsd).stream())
				.collect(toList()));
		
		views.add(securityViewProvider.get());
		
		return views;
	}

	private List<MainPanelView> getViewForDatasourceFolder() {
		return Arrays.asList(folderFormProvider.get(), securityViewProvider.get());
	}

}
