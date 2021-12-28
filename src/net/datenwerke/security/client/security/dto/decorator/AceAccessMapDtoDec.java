package net.datenwerke.security.client.security.dto.decorator;

import net.datenwerke.security.client.security.dto.AceAccessMapDto;
import net.datenwerke.security.client.security.dto.RightDto;
import net.datenwerke.security.service.security.entities.AceAccessMap;

/**
 * Dto for {@link AceAccessMap}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
public class AceAccessMapDtoDec extends AceAccessMapDto {

   private static final long serialVersionUID = 1L;

   public AceAccessMapDtoDec() {
      super();
   }

   public void addAccessRight(long right) {
      Long access = getAccess();
      if (null == access)
         access = 0L;
      setAccess(access | right);
   }

   public boolean hasAccessRight(long right) {
      Long access = getAccess();
      if (null == access)
         return false;
      return (access & right) == right;
   }

   public boolean hasAccessRight(RightDto right) {
      return hasAccessRight(right.getBitField());
   }

   public void clearRights() {
      setAccess(0L);
   }

}
