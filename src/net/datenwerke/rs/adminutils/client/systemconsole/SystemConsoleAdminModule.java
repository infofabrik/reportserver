package net.datenwerke.rs.adminutils.client.systemconsole;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.administration.interfaces.AdminModule;
import net.datenwerke.rs.adminutils.client.systemconsole.locale.SystemConsoleMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class SystemConsoleAdminModule implements AdminModule {

   private final Provider<SystemConsoleView> systemConsoleViewProvider;

   private SystemConsoleView systemConsoleViewInstance;

   @Inject
   public SystemConsoleAdminModule(Provider<SystemConsoleView> systemConsoleViewProvider) {
      this.systemConsoleViewProvider = systemConsoleViewProvider;
   }

   @Override
   public Widget getMainWidget() {
      if (null == systemConsoleViewInstance)
         systemConsoleViewInstance = systemConsoleViewProvider.get();
      return systemConsoleViewInstance;
   }

   @Override
   public ImageResource getNavigationIcon() {
      return BaseIcon.AREA_CHART.toImageResource();
   }

   @Override
   public String getNavigationText() {
      return SystemConsoleMessages.INSTANCE.systemConsole();
   }

   @Override
   public void notifyOfSelection() {
   }
}
