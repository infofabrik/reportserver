package net.datenwerke.gf.client.administration.ui;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.widget.core.client.tree.Tree;

import net.datenwerke.gf.client.administration.hooks.AdminModuleProviderHook;
import net.datenwerke.gf.client.administration.interfaces.AdminModule;
import net.datenwerke.gf.client.administration.locale.AdministrationMessages;
import net.datenwerke.gxtdto.client.baseex.widget.DwContentPanel;
import net.datenwerke.gxtdto.client.eventbus.events.SubmoduleDisplayRequest;
import net.datenwerke.gxtdto.client.theme.CssClassConstant;
import net.datenwerke.gxtdto.client.ui.helper.nav.NavItemSelectionCallback;
import net.datenwerke.gxtdto.client.ui.helper.nav.NavigationModelData;
import net.datenwerke.gxtdto.client.ui.helper.nav.NavigationPanelHelper;
import net.datenwerke.gxtdto.client.utils.modelkeyprovider.BasicObjectModelKeyProvider;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;

/**
 * 
 *
 */
@Singleton
public class AdministrationNavPanel extends DwContentPanel {

   @CssClassConstant
   public static final String CSS_NAME = "rs-admin-nav";

   final private NavigationPanelHelper navPanelHelper;
   final private HookHandlerService hookHandler;
   private AdministrationPanel mainPanel;
   final private EventBus eventBus;
   final private Provider<AdministrationPanel> adminPanelProvider;

   private List<AdminModule> adminModules;
   private Tree<NavigationModelData<AdminModule>, String> tree;

   @Inject
   public AdministrationNavPanel(NavigationPanelHelper navPanelHelper, final HookHandlerService hookHandler,
         final EventBus eventBus, final Provider<AdministrationPanel> adminPanel) {
      super();

      /* store objects */
      this.navPanelHelper = navPanelHelper;
      this.hookHandler = hookHandler;
      this.eventBus = eventBus;
      this.adminPanelProvider = adminPanel;

      initModules();

      /* init */
      initializeUI();
   }

   @Override
   public String getCssName() {
      return super.getCssName() + " " + CSS_NAME;
   }

   public void setAdministrationPanel(AdministrationPanel panel) {
      this.mainPanel = panel;

      if (!adminModules.isEmpty())
         mainPanel.showAdminModule(adminModules.get(0));
   }

   private void initModules() {
      /* load modules */
      adminModules = new ArrayList<AdminModule>();
      for (AdminModuleProviderHook hooker : hookHandler.getHookers(AdminModuleProviderHook.class))
         adminModules.add(hooker.getModule());
   }

   private void initializeUI() {
      setHeading(AdministrationMessages.INSTANCE.administrationHeader());

      /* display modules in tree */
      TreeStore<NavigationModelData<AdminModule>> store = new TreeStore<NavigationModelData<AdminModule>>(
            new BasicObjectModelKeyProvider<NavigationModelData<AdminModule>>());

      /* add modules */
      for (AdminModule module : adminModules)
         store.add(
               new NavigationModelData<AdminModule>(module.getNavigationText(), module.getNavigationIcon(), module));

      // TODO: Theme
      /*
       * tree = navPanelHelper.createNavigationTreePanel(store, new
       * NavItemSelectionCallback<AdminModule>() {
       * 
       * @Override public void selected(AdminModule model) {
       * mainPanel.showAdminModule(model); } }, (RepserTreeMenuAppearance)
       * GWT.create(RepserTreeMenuAppearance.class));
       */
      tree = navPanelHelper.createNavigationTreePanel(store, new NavItemSelectionCallback<AdminModule>() {
         @Override
         public void selected(AdminModule model) {
            mainPanel.showAdminModule(model);
         }
      });

      /* add tree */
      add(tree);
   }

   public void handleDisplayRequest(SubmoduleDisplayRequest event) {
      for (NavigationModelData<AdminModule> nmd : tree.getStore().getAll()) {
         if (nmd.getModel().getMainWidget() == event.getSubmodule()) {
            eventBus.fireEvent(new SubmoduleDisplayRequest(adminPanelProvider.get(), null));
            tree.getSelectionModel().select(nmd, false);
            return;
         }
      }
   }

}
