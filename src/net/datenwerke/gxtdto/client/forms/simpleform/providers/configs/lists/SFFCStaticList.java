package net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.lists;

import java.util.Map;

public abstract class SFFCStaticList<M> implements SFFCSimpleList {

   public enum TYPE {
      Dropdown, Radio
   }

   public abstract Map<String, M> getValues();

   public abstract TYPE getType();

   @Override
   public final Boolean isMultiselect() {
      return false;
   }

   public boolean allowTextSelection() {
      return false;
   }

   public boolean allowBlank() {
      return false;
   }
}
