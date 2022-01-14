package net.datenwerke.gxtdto.client.forms.simpleform.providers;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.sencha.gxt.widget.core.client.form.Field;
import com.sencha.gxt.widget.core.client.form.NumberField;
import com.sencha.gxt.widget.core.client.form.NumberPropertyEditor.DoublePropertyEditor;

import net.datenwerke.gxtdto.client.baseex.widget.form.DwNumberField;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleFormFieldConfiguration;
import net.datenwerke.gxtdto.client.forms.simpleform.hooks.FormFieldProviderHookImpl;

/**
 * 
 *
 */
public class DoubleProvider extends FormFieldProviderHookImpl {

   @Override
   public boolean doConsumes(Class<?> type, SimpleFormFieldConfiguration... configs) {
      return Double.class.equals(type);
   }

   @Override
   public Field<Double> createFormField() {
      final NumberField<Double> field = new DwNumberField<Double>(new DoublePropertyEditor());
      field.setName(name);

      field.addValueChangeHandler(event -> ValueChangeEvent.fire(DoubleProvider.this, event.getValue()));

      installBlankValidation(field);

      return field;
   }

}
