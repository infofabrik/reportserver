package net.datenwerke.gxtdto.client.model;

import com.sencha.gxt.core.client.ValueProvider;

import net.datenwerke.gxtdto.client.dtomanager.HasValueProviderByPath;
import net.datenwerke.gxtdto.client.model.pa.StringBaseModelPa;

/**
 * A wrapper object to wrap Strings in a BaseModel e.g. for adding them to a
 * store.
 *
 */
public class StringBaseModel implements DwModel, HasValueProviderByPath<StringBaseModel> {

   /**
    * 
    */
   private static final long serialVersionUID = 1483262026820046726L;

   private String value;

   public StringBaseModel() {
   }

   public StringBaseModel(String value) {
      setValue(value);
   }

   public void setValue(String value) {
      this.value = value;
   }

   public String getValue() {
      return value;
   }

   public ValueProvider<StringBaseModel, ?> getValueProviderByPath(String path) {
      if ("value".equals(path)) {
         return StringBaseModelPa.INSTANCE.value();
      }

      return null;
   }

}
