package net.datenwerke.rs.adminutils.client.systemconsole.connpool.hooks;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.adminutils.client.systemconsole.connpool.ui.ConnectionPoolInfoPanel;
import net.datenwerke.rs.adminutils.client.systemconsole.hooks.SystemConsoleViewDomainHook;
import net.datenwerke.rs.adminutils.client.systemconsole.locale.SystemConsoleMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class ConnectionPoolInfoSystemConsoleHooker implements SystemConsoleViewDomainHook {

   private final Provider<ConnectionPoolInfoPanel> mainWidgetProvider;

   @Inject
   public ConnectionPoolInfoSystemConsoleHooker(Provider<ConnectionPoolInfoPanel> mainWidgetProvider) {
      this.mainWidgetProvider = mainWidgetProvider;
   }

   @Override
   public String getNavigationText() {
      return SystemConsoleMessages.INSTANCE.connectionPool();
   }

   @Override
   public ImageResource getNavigationIcon() {
      return BaseIcon.AGGREGATION.toImageResource();
   }

   @Override
   public Widget getMainWidget() {
      return mainWidgetProvider.get();
   }

}
