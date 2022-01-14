package net.datenwerke.gxtdto.client.forms.simpleform.hooks;

import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.ValueProvider;

import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleFormFieldConfiguration;
import net.datenwerke.gxtdto.client.forms.simpleform.json.SimpleFormFieldJson;
import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;

public interface FormFieldProviderHook extends Hook, HasValueChangeHandlers {

   public boolean consumes(Class<?> type, SimpleFormFieldConfiguration... configs);

   /**
    * Used to construct fields from a text based (e.g., json) config.
    */
   boolean consumes(String type, SimpleFormFieldJson config);

   void init(String name, SimpleForm form);

   Widget createFormField();

   void addFieldBindings(Object model, ValueProvider vp, Widget field);

   void removeFieldBindings(Object model, Widget field);

   Object getValue(Widget field);

   String getStringValue(Widget field);

   Widget reload(Widget field);

   void setValue(Widget field, Object value);

   boolean isDecorateable();

   void installBlankValidation(Widget field);

}
