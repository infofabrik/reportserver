package net.datenwerke.rs.dashboard.client.dashboard.ui;

import net.datenwerke.rs.dashboard.client.dashboard.dto.DadgetDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardDto;
import net.datenwerke.rs.dashboard.client.dashboard.ui.DashboardView.EditSuccessCallback;

public interface DashboardContainer {

   enum ConfigType {
      CONFIG, MISC
   }

   void reload(DashboardDto dashboard);

   void edited(DashboardDto dashboard);

   void remove(DashboardDto dashboard, DadgetDto dadget);

   void resizeDadget(DashboardDto dashboard, DadgetDto dadget, int height);

   void addDadget(DashboardDto dashboard, DadgetDto dadget);

   void dadgetConfigured(DashboardDto dashboard, DadgetDto dadget, ConfigType type, EditSuccessCallback callback);

}
