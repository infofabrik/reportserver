package net.datenwerke.rs.adminutils.client.systemconsole.memory.hooks;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.adminutils.client.systemconsole.hooks.SystemConsoleViewDomainHook;
import net.datenwerke.rs.adminutils.client.systemconsole.locale.SystemConsoleMessages;
import net.datenwerke.rs.adminutils.client.systemconsole.memory.ui.MemoryInfoPanel;
import net.datenwerke.rs.enterprise.client.EnterpriseUiService;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class MemoryInfoSystemConsoleHooker implements SystemConsoleViewDomainHook {

   private final Provider<MemoryInfoPanel> mainWidgetProvider;
   private final Provider<EnterpriseUiService> enterpriseServiceProvider;

   @Inject
   public MemoryInfoSystemConsoleHooker(
         Provider<MemoryInfoPanel> mainWidgetProvider,
         Provider<EnterpriseUiService> enterpriseServiceProvider
         ) {
      this.mainWidgetProvider = mainWidgetProvider;
      this.enterpriseServiceProvider = enterpriseServiceProvider;
   }

   @Override
   public String getNavigationText() {
      return SystemConsoleMessages.INSTANCE.jvmLiveMemory();
   }

   @Override
   public ImageResource getNavigationIcon() {
      return BaseIcon.DATABASE.toImageResource();
   }

   @Override
   public Widget getMainWidget() {
      return mainWidgetProvider.get();
   }

   @Override
   public boolean consumes() {
      return enterpriseServiceProvider.get().isEnterprise();
   }

}
