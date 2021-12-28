package net.datenwerke.security.ext.client.security.ui.genericview;

import java.util.Collection;

import com.google.inject.Inject;
import com.sencha.gxt.data.shared.SortDir;
import com.sencha.gxt.data.shared.Store.StoreSortInfo;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.widget.core.client.tree.Tree;

import net.datenwerke.gxtdto.client.baseex.widget.DwContentPanel;
import net.datenwerke.gxtdto.client.ui.helper.nav.NavItemSelectionCallback;
import net.datenwerke.gxtdto.client.ui.helper.nav.NavigationModelData;
import net.datenwerke.gxtdto.client.ui.helper.nav.NavigationPanelHelper;
import net.datenwerke.gxtdto.client.utils.modelkeyprovider.BasicObjectModelKeyProvider;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.security.client.security.hooks.GenericSecurityViewDomainHook;
import net.datenwerke.security.ext.client.security.locale.SecurityMessages;

/**
 * 
 *
 */
public class GenericSecurityNavigationPanel extends DwContentPanel {

   private final HookHandlerService hookHandler;
   private final NavigationPanelHelper navPanelHelper;
   private final GenericSecurityMainPanel mainPanel;

   @Inject
   public GenericSecurityNavigationPanel(HookHandlerService hookHandler, NavigationPanelHelper navPanelHelper,
         GenericSecurityMainPanel mainPanel) {

      /* store objects */
      this.hookHandler = hookHandler;
      this.navPanelHelper = navPanelHelper;
      this.mainPanel = mainPanel;

      /* init */
      initializeUI();
   }

   private void initializeUI() {
      setHeading(SecurityMessages.INSTANCE.genericSecurityViewHeading());

      /* display modules in tree */
      TreeStore<NavigationModelData<GenericSecurityViewDomainHook>> store = new TreeStore<NavigationModelData<GenericSecurityViewDomainHook>>(
            new BasicObjectModelKeyProvider<NavigationModelData<GenericSecurityViewDomainHook>>());

      /* load data */
      Collection<GenericSecurityViewDomainHook> domains = hookHandler.getHookers(GenericSecurityViewDomainHook.class);
      for (GenericSecurityViewDomainHook domain : domains)
         store.add(new NavigationModelData<GenericSecurityViewDomainHook>(
               domain.genericSecurityViewDomainHook_getName(), domain.genericSecurityViewDomainHook_getIcon(), domain));

      /* sort store */
      store.addSortInfo(new StoreSortInfo<NavigationModelData<GenericSecurityViewDomainHook>>(
            NavigationModelData.nameValueProvider, SortDir.ASC));

      /* build tree */
      final Tree<NavigationModelData<GenericSecurityViewDomainHook>, String> tree = navPanelHelper
            .createNavigationTreePanel(store, new NavItemSelectionCallback<GenericSecurityViewDomainHook>() {
               @Override
               public void selected(GenericSecurityViewDomainHook model) {
                  mainPanel.displayGenericRightsItem(model);
               }
            });

      /* add tree */
      add(tree);
   }

}
