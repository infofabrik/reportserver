package net.datenwerke.rs.core.service.datasinkmanager.eventhandlers;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.core.service.datasinkmanager.DatasinkTreeService;
import net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkContainer;
import net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkContainer__;
import net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkDefinition;
import net.datenwerke.rs.utils.eventbus.EventHandler;
import net.datenwerke.security.service.eventlogger.jpa.ForceRemoveEntityEvent;

public class HandleDatasinkForceRemoveEventHandler implements EventHandler<ForceRemoveEntityEvent> {

   @Inject
   private Provider<EntityManager> entityManagerProvider;
   @Inject
   private DatasinkTreeService datasinkService;

   @Override
   public void handle(ForceRemoveEntityEvent event) {
      DatasinkDefinition ds = (DatasinkDefinition) event.getObject();

      EntityManager em = entityManagerProvider.get();
      Query query = em.createQuery("FROM " + DatasinkContainer.class.getSimpleName() + " WHERE "
            + DatasinkContainer__.datasink + " = :datasink");
      query.setParameter("datasink", ds);
      List<DatasinkContainer> containers = query.getResultList();

      if (null != containers && !containers.isEmpty()) {
         for (DatasinkContainer container : containers) {
            container.setDatasink(null);
            datasinkService.merge(container);
         }
      }
   }

}
