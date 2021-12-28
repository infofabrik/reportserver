package net.datenwerke.rs.dashboard.service.dashboard.eventhandler;

import java.util.List;

import com.google.inject.Inject;

import net.datenwerke.rs.dashboard.service.dashboard.DadgetService;
import net.datenwerke.rs.dashboard.service.dashboard.entities.DashboardNode;
import net.datenwerke.rs.dashboard.service.dashboard.entities.DashboardReference;
import net.datenwerke.rs.utils.eventbus.EventHandler;
import net.datenwerke.rs.utils.exception.exceptions.NeedForcefulDeleteException;
import net.datenwerke.security.service.eventlogger.jpa.RemoveEntityEvent;

public class HandleDashboardNodeRemoveEventHandler implements EventHandler<RemoveEntityEvent> {

   private final DadgetService dadgetService;

   @Inject
   public HandleDashboardNodeRemoveEventHandler(DadgetService dadgetService) {
      this.dadgetService = dadgetService;
   }

   @Override
   public void handle(RemoveEntityEvent event) {
      DashboardNode node = (DashboardNode) event.getObject();

      List<DashboardReference> container = dadgetService.getDashboardContainerssWith(node);

      if (null != container && !container.isEmpty()) {
         String error = "Dashboard is used.";
         throw new NeedForcefulDeleteException(error);
      }
   }

}
