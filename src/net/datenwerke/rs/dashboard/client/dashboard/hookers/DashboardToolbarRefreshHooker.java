package net.datenwerke.rs.dashboard.client.dashboard.hookers;

import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.toolbar.SeparatorToolItem;

import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.DwToolBar;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardDto;
import net.datenwerke.rs.dashboard.client.dashboard.hooks.DashboardToolbarHook;
import net.datenwerke.rs.dashboard.client.dashboard.ui.DadgetPanel;
import net.datenwerke.rs.dashboard.client.dashboard.ui.DashboardMainComponent;
import net.datenwerke.rs.dashboard.client.dashboard.ui.DashboardView;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class DashboardToolbarRefreshHooker implements DashboardToolbarHook {

   private DashboardView dashboardView;

   @Override
   public void addLeft(DwToolBar toolbar, DashboardMainComponent dashboardMainComponent) {

   }

   @Override
   public void addRight(DwToolBar toolbar, final DashboardMainComponent dashboardMainComponent) {
      DwTextButton refreshBtn = new DwTextButton(BaseMessages.INSTANCE.refresh(), BaseIcon.REFRESH);

      toolbar.add(refreshBtn);
      toolbar.add(new SeparatorToolItem());

      refreshBtn.addSelectHandler(new SelectHandler() {
         @Override
         public void onSelect(SelectEvent event) {
            if (null != dashboardView) {
               for (DadgetPanel dp : dashboardView.getAllDagetPanels())
                  dp.refresh();
            }
         }
      });
   }

   @Override
   public void dashboardDisplayed(DashboardDto dashboard, DashboardView dashboardView) {
      this.dashboardView = dashboardView;
   }

   @Override
   public void dashboardChanged(DashboardDto dashboard, DashboardView view) {
      dashboardDisplayed(dashboard, view);
   }

}
