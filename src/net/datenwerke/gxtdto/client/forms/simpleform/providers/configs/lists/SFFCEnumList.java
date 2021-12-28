package net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.lists;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import net.datenwerke.gxtdto.client.utils.StringEscapeUtils;

public class SFFCEnumList extends SFFCStaticList<Enum> {

   private HashMap<String, Enum> values;

   public SFFCEnumList(Class<? extends Enum> enumType) {
      this(enumType, false);
   }

   public SFFCEnumList(Class<? extends Enum> enumType, boolean nullable) {
      this.values = new LinkedHashMap<String, Enum>();

      if (nullable)
         values.put("&nbsp;", null);

      for (Enum e : enumType.getEnumConstants())
         values.put(StringEscapeUtils.unescapeHTML(e.toString()), e);
   }

   @Override
   public Map<String, Enum> getValues() {
      return this.values;
   }

   @Override
   public net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.lists.SFFCStaticList.TYPE getType() {
      return TYPE.Dropdown;
   }

   @Override
   public boolean allowTextSelection() {
      return false;
   }
}
