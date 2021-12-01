package net.datenwerke.rs.core.client.datasinkmanager.hookers;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.managerhelper.hooks.MainPanelViewProviderHook;
import net.datenwerke.gf.client.managerhelper.mainpanel.MainPanelView;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkFolderDto;
import net.datenwerke.rs.core.client.datasinkmanager.hooks.DatasinkDefinitionConfigProviderHook;
import net.datenwerke.rs.core.client.datasinkmanager.ui.forms.FolderForm;
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
		if(node instanceof DatasinkFolderDto)
			return getViewForDatasinkFolder();
		if(node instanceof DatasinkDefinitionDto)
			return getViewForDatasinks((DatasinkDefinitionDto) node);
		return null;
	}

	private List<MainPanelView> getViewForDatasinks(final DatasinkDefinitionDto datasinkDefinition) {
		final List<MainPanelView> views = new ArrayList<>();
		
		views.addAll(
			hookHandler.getHookers(DatasinkDefinitionConfigProviderHook.class)
				.stream()
				.filter(config -> config.consumes(datasinkDefinition))
				.flatMap(config -> config.getAdminViews(datasinkDefinition).stream())
				.collect(toList()));
		
		views.add(securityViewProvider.get());
		
		return views;
	}

	private List<MainPanelView> getViewForDatasinkFolder() {
		return Arrays.asList(folderFormProvider.get(), securityViewProvider.get());
	}

}
