package net.datenwerke.gxtdto.client.forms.simpleform.providers;

import net.datenwerke.gxtdto.client.forms.simpleform.hooks.FormFieldProviderHookImpl;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.lists.SFFCList;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.lists.SFFCSimpleList;

/**
 * 
 *
 */
public abstract class BaseListProvider extends FormFieldProviderHookImpl {

   public boolean isMultiSelect() {
      if (configs[0] instanceof SFFCList)
         return ((SFFCList) configs[0]).isMultiselect();
      return ((SFFCSimpleList) configs[0]).isMultiselect();
   }

}
