package net.datenwerke.gxtdto.client.forms.simpleform.providers;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.form.Field;
import com.sencha.gxt.widget.core.client.form.NumberField;
import com.sencha.gxt.widget.core.client.form.NumberPropertyEditor.IntegerPropertyEditor;

import net.datenwerke.gxtdto.client.baseex.widget.form.DwNumberField;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleFormFieldConfiguration;
import net.datenwerke.gxtdto.client.forms.simpleform.hooks.FormFieldProviderHookImpl;
import net.datenwerke.gxtdto.client.forms.simpleform.json.SimpleFormFieldJson;

/**
 * 
 *
 */
public class IntegerProvider extends FormFieldProviderHookImpl {

   @Override
   public boolean doConsumes(Class<?> type, SimpleFormFieldConfiguration... configs) {
      return Integer.class.equals(type);
   }

   @Override
   public boolean doConsumes(String type, SimpleFormFieldJson config) {
      return type.equals("int") || type.equals("integer");
   }

   @Override
   public Field<Integer> createFormField() {
      final NumberField<Integer> field = new DwNumberField<Integer>(new IntegerPropertyEditor());
      field.setName(name);

      field.addValueChangeHandler(event -> ValueChangeEvent.fire(IntegerProvider.this, event.getValue()));

      installBlankValidation(field);

      return field;
   }

   @Override
   protected void setStringValue(Widget field, String value) {
      ((HasValue<Integer>) field).setValue(Integer.parseInt(value));
   }

}
