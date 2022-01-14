package net.datenwerke.gf.client.managerhelper.hooks;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.google.inject.Inject;

import net.datenwerke.gf.client.managerhelper.mainpanel.MainPanelView;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

public abstract class MainPanelViewProviderHookImpl implements MainPanelViewProviderHook {

   protected final HookHandlerService hookHandler;

   @Inject
   public MainPanelViewProviderHookImpl(HookHandlerService hookHandler) {
      this.hookHandler = hookHandler;
   }

   @Override
   public List<MainPanelView> mainPanelViewProviderHook_getView(AbstractNodeDto node) {
      List<MainPanelView> views = new ArrayList<MainPanelView>();

      views.addAll(getPrimaryViews(node));

      for (MainPanelViewProviderConfigHook config : hookHandler.getHookers(MainPanelViewProviderConfigHook.class)) {
         if (config.applies(getViewProviderId(), node)) {
            views.addAll(config.gatherViews(getViewProviderId(), node));
         }
      }

      return views;
   }

   protected Collection<? extends MainPanelView> getPrimaryViews(AbstractNodeDto node) {
      return Collections.emptyList();
   }

   abstract public String getViewProviderId();

}
