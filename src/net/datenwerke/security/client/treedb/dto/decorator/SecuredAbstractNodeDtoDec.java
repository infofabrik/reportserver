package net.datenwerke.security.client.treedb.dto.decorator;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import net.datenwerke.security.client.security.dto.RightDto;
import net.datenwerke.security.client.treedb.dto.SecuredAbstractNodeDto;

/**
 * Dto Decorator for {@link SecuredAbstractNodeDto}
 *
 */
abstract public class SecuredAbstractNodeDtoDec extends SecuredAbstractNodeDto {

   public static final String PROPERTY_ACCCESS_RIGHTS = "SECURED_ABSTRACT_NODE_ACCESS_RIGHTS"; //$NON-NLS-1$

   private static final long serialVersionUID = 1L;

   public SecuredAbstractNodeDtoDec() {
      super();
   }

   public void addAvailableRight(RightDto right) {
      Set<RightDto> availableRights = getAvailableAccessRights();
      if (null == availableRights)
         availableRights = new HashSet<RightDto>();

      availableRights.add(right);
      setAvailableAccessRights(availableRights);
   }

   public boolean isAccessRightsLoaded() {
      if (!isDtoProxy())
         return Boolean.TRUE.equals(this.availableAccessRightsSet);

      return dtoManager.unproxy(this).isAccessRightsLoaded();
   }

   public boolean hasAccessRight(Class<? extends RightDto> right) {
      Collection<RightDto> availableRights = getAvailableAccessRights();
      if (null == availableRights)
         return false;

      for (RightDto r : availableRights)
         if (r.getClass().equals(right))
            return true;

      return false;
   }

   public boolean hasInheritedAccessRight(Class<? extends RightDto> right) {
      Collection<RightDto> availableRights = getAvailableInheritedAccessRights();
      if (null == availableRights)
         return false;

      for (RightDto r : availableRights)
         if (r.getClass().equals(right))
            return true;

      return false;
   }

}
