package net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.decorator;

import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskFileReferenceDto;

/**
 * Dto Decorator for {@link TsDiskFileReferenceDto}
 *
 */
public class TsDiskFileReferenceDtoDec extends TsDiskFileReferenceDto {

   private static final long serialVersionUID = 1L;

   public TsDiskFileReferenceDtoDec() {
      super();
   }

   @Override
   public String toTypeDescription() {
      if (null == getTypeStr())
         return BaseMessages.INSTANCE.unknown();
      
      return getTypeStr();
   }
   
   @Override
   public BaseIcon toIcon() {
      return BaseIcon.from(getIconStr());
   }

}
