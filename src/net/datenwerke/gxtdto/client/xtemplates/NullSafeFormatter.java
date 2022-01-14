package net.datenwerke.gxtdto.client.xtemplates;

import com.sencha.gxt.core.client.XTemplates;

public class NullSafeFormatter implements XTemplates.Formatter<Object> {

   @Override
   public String format(Object data) {
      return null == data ? "" : String.valueOf(data);
   }

   public static NullSafeFormatter getFormat() {
      return new NullSafeFormatter();
   }

}
