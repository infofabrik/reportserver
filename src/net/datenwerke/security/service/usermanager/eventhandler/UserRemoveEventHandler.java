package net.datenwerke.security.service.usermanager.eventhandler;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.utils.eventbus.EventBus;
import net.datenwerke.rs.utils.eventbus.EventHandler;
import net.datenwerke.security.service.eventlogger.jpa.AfterMergeEntityEvent;
import net.datenwerke.security.service.eventlogger.jpa.MergeEntityEvent;
import net.datenwerke.security.service.eventlogger.jpa.RemoveEntityEvent;
import net.datenwerke.security.service.treedb.entities.SecuredAbstractNode;
import net.datenwerke.security.service.treedb.entities.SecuredAbstractNode__;
import net.datenwerke.security.service.usermanager.entities.User;

public class UserRemoveEventHandler implements EventHandler<RemoveEntityEvent> {

   private final Provider<EntityManager> entityManagerProvider;
   private final EventBus eventBus;

   @Inject
   public UserRemoveEventHandler(Provider<EntityManager> entityManagerProvider, EventBus eventBus) {
      this.entityManagerProvider = entityManagerProvider;
      this.eventBus = eventBus;
   }

   @Override
   public void handle(RemoveEntityEvent event) {
      User user = (User) event.getObject();

      EntityManager em = entityManagerProvider.get();

      Query query = em.createQuery(
            "FROM " + SecuredAbstractNode.class.getName() + " WHERE " + SecuredAbstractNode__.owner + " = :owner");
      query.setParameter("owner", user);
      List<SecuredAbstractNode> nodes = query.getResultList();
      if (null != nodes && !nodes.isEmpty()) {
         for (SecuredAbstractNode node : nodes) {
            node.setOwner(null);

            eventBus.fireEvent(new MergeEntityEvent(node));

            node = em.merge(node);

            eventBus.fireEvent(new AfterMergeEntityEvent(node));
         }

      }
   }

}
