package net.datenwerke.security.service.treedb;

import com.google.inject.Inject;

import net.datenwerke.rs.utils.eventbus.EventBus;
import net.datenwerke.security.service.eventlogger.annotations.FireForceRemoveEntityEvents;
import net.datenwerke.security.service.eventlogger.annotations.FireMergeEntityEvents;
import net.datenwerke.security.service.eventlogger.annotations.FirePersistEntityEvents;
import net.datenwerke.security.service.eventlogger.annotations.FireRemoveEntityEvents;
import net.datenwerke.security.service.treedb.events.MoveNodeEvent;
import net.datenwerke.treedb.service.treedb.AbstractNode;
import net.datenwerke.treedb.service.treedb.TreeDBManagerImpl;

public abstract class LoggedTreeDbManagerImpl<A extends AbstractNode<A>> extends TreeDBManagerImpl<A> {

   @Inject
   protected EventBus eventBus;

   public void move(A node, A newParent) {
      eventBus.fireEvent(new MoveNodeEvent(node, newParent));
      super.move(node, newParent);
   }

   public void move(A node, A newParent, int index) {
      eventBus.fireEvent(new MoveNodeEvent(node, newParent, index));
      super.move(node, newParent, index);
   }

   @FirePersistEntityEvents
   public void persist(A node) {
      super.persist(node);
   }

   @FireMergeEntityEvents
   public A merge(A node) {
      return super.merge(node);
   }

   @FireRemoveEntityEvents
   public void remove(A node) {
      super.remove(node);
   }

   @FireForceRemoveEntityEvents
   public void forceRemove(A node) {
      super.forceRemove(node);
   }
}
