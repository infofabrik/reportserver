package net.datenwerke.eximport.obj;

import net.datenwerke.eximport.ExImportHelperService;
import nu.xom.Element;

/**
 * 
 *
 */
public class SimpleItemProperty extends ItemProperty {

   protected final String value;

   public SimpleItemProperty(String name, Class<?> type, String value, Element el) {
      super(name, type, el);

      /* store objects */
      this.value = value;
   }

   public String getValue() {
      return value;
   }

   public Boolean getBoolean() {
      return ExImportHelperService.XML_TRUE.equals(value);
   }

   public Integer getInteger() {
      return Integer.parseInt(value);
   }

   public Double getDouble() {
      return Double.valueOf(value);
   }

   public Long getLong() {
      return Long.valueOf(value);
   }

}
