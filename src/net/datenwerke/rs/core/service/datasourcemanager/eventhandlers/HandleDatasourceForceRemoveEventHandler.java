package net.datenwerke.rs.core.service.datasourcemanager.eventhandlers;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.core.service.datasourcemanager.DatasourceService;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceContainer;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceContainer__;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceDefinition;
import net.datenwerke.rs.utils.eventbus.EventHandler;
import net.datenwerke.security.service.eventlogger.jpa.ForceRemoveEntityEvent;

public class HandleDatasourceForceRemoveEventHandler implements EventHandler<ForceRemoveEntityEvent> {

   @Inject
   private Provider<EntityManager> entityManagerProvider;
   @Inject
   private DatasourceService datasourceService;

   @Override
   public void handle(ForceRemoveEntityEvent event) {
      DatasourceDefinition ds = (DatasourceDefinition) event.getObject();

      EntityManager em = entityManagerProvider.get();
      Query query = em.createQuery("FROM " + DatasourceContainer.class.getSimpleName() + " WHERE "
            + DatasourceContainer__.datasource + " = :ds");
      query.setParameter("ds", ds);
      final List<DatasourceContainer> containers = query.getResultList();

      if (null != containers && !containers.isEmpty()) {
         containers
            .forEach(container -> {
               container.setDatasource(null);
               datasourceService.merge(container);
            });
      }
   }

}
