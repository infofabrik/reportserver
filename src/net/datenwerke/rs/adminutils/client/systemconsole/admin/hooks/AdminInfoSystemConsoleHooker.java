package net.datenwerke.rs.adminutils.client.systemconsole.admin.hooks;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.adminutils.client.systemconsole.admin.ui.AdminConsolePanel;
import net.datenwerke.rs.adminutils.client.systemconsole.hooks.SystemConsoleViewDomainHook;
import net.datenwerke.rs.adminutils.client.systemconsole.locale.SystemConsoleMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class AdminInfoSystemConsoleHooker implements SystemConsoleViewDomainHook {

   private final Provider<AdminConsolePanel> mainWidgetProvider;

   @Inject
   public AdminInfoSystemConsoleHooker(
         Provider<AdminConsolePanel> mainWidgetProvider
         ) {
      this.mainWidgetProvider = mainWidgetProvider;
   }

   @Override
   public String getNavigationText() {
      return SystemConsoleMessages.INSTANCE.adminConsole();
   }

   @Override
   public ImageResource getNavigationIcon() {
      return BaseIcon.STAR_O.toImageResource();
   }

   @Override
   public Widget getMainWidget() {
      return mainWidgetProvider.get();
   }

   @Override
   public boolean consumes() {
      return true;
   }

}
