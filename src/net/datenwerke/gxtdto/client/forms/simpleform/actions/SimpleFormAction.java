package net.datenwerke.gxtdto.client.forms.simpleform.actions;

import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;

/**
 * 
 *
 */
public interface SimpleFormAction {

   /**
    * Executed if condition is met.
    */
   public void onSuccess(SimpleForm form);

   /**
    * Executed if the condition was not met.
    */
   public void onFailure(SimpleForm form);

}
