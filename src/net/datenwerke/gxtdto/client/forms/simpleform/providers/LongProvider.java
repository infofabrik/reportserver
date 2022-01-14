package net.datenwerke.gxtdto.client.forms.simpleform.providers;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.sencha.gxt.widget.core.client.form.Field;
import com.sencha.gxt.widget.core.client.form.NumberField;
import com.sencha.gxt.widget.core.client.form.NumberPropertyEditor.LongPropertyEditor;

import net.datenwerke.gxtdto.client.baseex.widget.form.DwNumberField;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleFormFieldConfiguration;
import net.datenwerke.gxtdto.client.forms.simpleform.hooks.FormFieldProviderHookImpl;

/**
 * 
 *
 */
public class LongProvider extends FormFieldProviderHookImpl {

   @Override
   public boolean doConsumes(Class<?> type, SimpleFormFieldConfiguration... configs) {
      return Long.class.equals(type);
   }

   @Override
   public Field<Long> createFormField() {
      final NumberField<Long> field = new DwNumberField<Long>(new LongPropertyEditor());
      field.setName(name);

      field.addValueChangeHandler(new ValueChangeHandler<Long>() {
         @Override
         public void onValueChange(ValueChangeEvent<Long> event) {
            ValueChangeEvent.fire(LongProvider.this, event.getValue());
         }
      });

      installBlankValidation(field);

      return field;
   }

}
