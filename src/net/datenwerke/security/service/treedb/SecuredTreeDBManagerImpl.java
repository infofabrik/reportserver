package net.datenwerke.security.service.treedb;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import net.datenwerke.security.service.eventlogger.annotations.FireForceRemoveEntityEvents;
import net.datenwerke.security.service.eventlogger.annotations.FirePersistEntityEvents;
import net.datenwerke.security.service.eventlogger.annotations.FireRemoveEntityEvents;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.annotation.UpdateOwner;
import net.datenwerke.security.service.security.rights.Read;
import net.datenwerke.security.service.security.rights.Write;
import net.datenwerke.security.service.treedb.entities.SecuredAbstractNode;

public abstract class SecuredTreeDBManagerImpl<A extends SecuredAbstractNode<A>> extends LoggedTreeDbManagerImpl<A> {

   @Inject
   protected SecurityService securityService;

   @Override
   public List<A> getVirtualRoots() {
      List<A> virtualRoots = new ArrayList<A>();

      fillVirtualRoots(getRoots(), virtualRoots);

      return virtualRoots;
   }

   protected void fillVirtualRoots(List<A> nodes, List<A> virtualRoots) {
      for (A node : nodes) {
         if (!isFolder(node))
            continue;

         if (securityService.checkRights(node, Read.class))
            virtualRoots.add(node);
         else
            fillVirtualRoots(node.getChildren(), virtualRoots);
      }
   }

   @Override
   public void move(A node, A newParent) {
      move(node, newParent, true);
   }

   public void move(A node, A newParent, boolean checkRights) {
      if (null == node || null == newParent)
         throw new IllegalArgumentException();

      if (checkRights)
         testMoveRights(node, newParent);

      super.move(node, newParent);
   }

   @Override
   public void move(A node, A newParent, int index) {
      move(node, newParent, index, true);
   }

   public void move(A node, A newParent, int index, boolean checkRights) {
      if (null == node || null == newParent)
         throw new IllegalArgumentException();

      if (checkRights)
         testMoveRights(node, newParent);

      super.move(node, newParent, index);
   }

   protected void testMoveRights(A node, A newParent) {
      securityService.assertRights(newParent, Read.class, Write.class);
      securityService.assertRights(node, Read.class, Write.class);
   }

   @Override
   protected A copy(A node, A newParent) {
      testCopyRights(node, newParent);

      return super.copy(node, newParent);
   }

   public A copy(A source, A target, boolean deep, boolean checkRights) {
      if (checkRights)
         testCopyRights(source, target);
      A copiedNode = super.copy(source, target);

      if (deep)
         for (A child : source.getChildren())
            copy(child, copiedNode, true, checkRights);

      return copiedNode;
   }

   protected void testCopyRights(A node, A newParent) {
      securityService.assertRights(newParent, Read.class, Write.class);
      securityService.assertRights(node, Read.class);
   }

   @Override
   @FireRemoveEntityEvents
   public void remove(A node) {
      super.remove(node);
   };

   @Override
   @FireForceRemoveEntityEvents
   public void forceRemove(A node) {
      super.forceRemove(node);
   };

   @Override
   @UpdateOwner(name = "node")
   @FirePersistEntityEvents
   public void persist(@Named("node") A node) {
      super.persist(node);
   }

}
