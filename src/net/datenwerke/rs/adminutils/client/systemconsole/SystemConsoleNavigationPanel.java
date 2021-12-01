package net.datenwerke.rs.adminutils.client.systemconsole;

import java.util.Collection;

import com.google.inject.Inject;
import com.sencha.gxt.data.shared.SortDir;
import com.sencha.gxt.data.shared.Store.StoreSortInfo;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.widget.core.client.tree.Tree;

import net.datenwerke.gxtdto.client.baseex.widget.DwContentPanel;
import net.datenwerke.gxtdto.client.ui.helper.nav.NavigationModelData;
import net.datenwerke.gxtdto.client.ui.helper.nav.NavigationPanelHelper;
import net.datenwerke.gxtdto.client.utils.modelkeyprovider.BasicObjectModelKeyProvider;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.adminutils.client.systemconsole.hooks.SystemConsoleViewDomainHook;
import net.datenwerke.rs.adminutils.client.systemconsole.locale.SystemConsoleMessages;

/**
 * 
 *
 */
public class SystemConsoleNavigationPanel extends DwContentPanel {

	private final HookHandlerService hookHandler;
	private final NavigationPanelHelper navPanelHelper;
	private final SystemConsoleMainPanel mainPanel;
	
	@Inject
	public SystemConsoleNavigationPanel(
		HookHandlerService hookHandler,
		NavigationPanelHelper navPanelHelper,
		SystemConsoleMainPanel mainPanel
		){
		
		/* store objects */
		this.hookHandler = hookHandler;
		this.navPanelHelper = navPanelHelper;
		this.mainPanel = mainPanel;
		
		/* init */
		initializeUI();
	}

	private void initializeUI() {
		setHeading(SystemConsoleMessages.INSTANCE.systemConsole());
		
		/* display modules in tree */
		TreeStore<NavigationModelData<SystemConsoleViewDomainHook>> store = 
				new TreeStore<>(new BasicObjectModelKeyProvider<>());
		
		/* load data */
		Collection<SystemConsoleViewDomainHook> domains = hookHandler.getHookers(SystemConsoleViewDomainHook.class);
		
		domains.forEach(domain -> store
				.add(new NavigationModelData<SystemConsoleViewDomainHook>(domain.getNavigationText(), domain.getNavigationIcon(), domain)));
		
		/* sort store */
		store.addSortInfo(new StoreSortInfo<NavigationModelData<SystemConsoleViewDomainHook>>(NavigationModelData.nameValueProvider, SortDir.ASC));
		
		/* build tree */
		final Tree<NavigationModelData<SystemConsoleViewDomainHook>, String> tree = navPanelHelper.createNavigationTreePanel(store, model -> mainPanel.displaySpecificConsoleItem(model));
		
		/* add tree */
		add(tree);
	}
	

}
