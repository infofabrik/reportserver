package net.datenwerke.gxtdto.client.forms.simpleform.providers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.widget.core.client.form.TextField;

import net.datenwerke.gxtdto.client.forms.binding.HasValueFieldBinding;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleFormFieldConfiguration;
import net.datenwerke.gxtdto.client.forms.simpleform.hooks.FormFieldProviderHookImpl;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.lists.SFFCTextFieldList;

public class TextAsListProvider extends FormFieldProviderHookImpl {

   @Override
   public boolean doConsumes(Class<?> type, SimpleFormFieldConfiguration... configs) {
      return List.class.equals(type) && null != configs && configs[0] instanceof SFFCTextFieldList;
   }

   @Override
   public Widget createFormField() {
      TextField field = new TextField();

      /* add listener for change events */
      field.addValueChangeHandler(new ValueChangeHandler<String>() {
         @Override
         public void onValueChange(ValueChangeEvent<String> event) {
            ValueChangeEvent.fire(TextAsListProvider.this, event.getValue());
         }
      });

      return field;
   }

   @Override
   public void addFieldBindings(Object model, ValueProvider vp, Widget field) {
      TextField f = (TextField) field;

      fieldBinding = new HasValueFieldBinding(f, model, vp) {
         protected Object convertFieldValue(Object value) {
            if (null == value || !(value instanceof String))
               return new ArrayList<String>();

            return new ArrayList(Arrays.asList(((String) value).split(getConfig().getSeparator())));
         };

         protected Object convertModelValue(Object value) {
            if (null == value || !(value instanceof List))
               return null;

            List<String> list = (List<String>) value;
            if (list.isEmpty())
               return "";

            String sep = getConfig().getSeparator();
            StringBuilder strValue = new StringBuilder();

            Iterator<String> it = list.iterator();
            strValue.append(it.next());

            while (it.hasNext())
               strValue.append(sep).append(it.next());

            return strValue.toString();
         };
      };
   }

   protected SFFCTextFieldList getConfig() {
      for (SimpleFormFieldConfiguration config : configs)
         if (config instanceof SFFCTextFieldList)
            return (SFFCTextFieldList) config;
      return null;
   }

}
