package net.datenwerke.rs.dashboard.client.dashboard.hooks;

import net.datenwerke.gxtdto.client.utilityservices.toolbar.DwToolBar;
import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardDto;
import net.datenwerke.rs.dashboard.client.dashboard.ui.DashboardMainComponent;
import net.datenwerke.rs.dashboard.client.dashboard.ui.DashboardView;

public interface DashboardToolbarHook extends Hook {

   public void addLeft(DwToolBar toolbar, DashboardMainComponent dashboardMainComponent);

   public void addRight(DwToolBar toolbar, DashboardMainComponent dashboardMainComponent);

   public void dashboardDisplayed(DashboardDto dashboard, DashboardView dashboardView);

   public void dashboardChanged(DashboardDto dashboard, DashboardView view);
}
