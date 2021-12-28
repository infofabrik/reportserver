package net.datenwerke.gxtdto.client.forms.simpleform.decorators.field;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.form.FieldLabel;

import net.datenwerke.gxtdto.client.forms.layout.FormFieldLayoutConfiguration;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;

/**
 * 
 *
 */
public class LabelDecorator implements SimpleFormFieldDecorator {

   public static final String DECORATOR_ID = "LabelDecorator";

   protected final Map<String, FormFieldLayoutConfiguration> labelLookup = new HashMap<String, FormFieldLayoutConfiguration>();

   @Override
   public String getDecoratorId() {
      return DECORATOR_ID;
   }

   @Override
   public void configureFieldAfterLayout(SimpleForm simpleForm, Widget widget, String key) {
   }

   @Override
   public void configureFieldOnLoad(SimpleForm form, Widget field, String key) {

   }

   public void addLabel(String key, FormFieldLayoutConfiguration config) {
      labelLookup.put(key, config);
   }

   public FormFieldLayoutConfiguration getConfigFor(String key) {
      return labelLookup.get(key);
   }

   @Override
   public Widget adjustFieldForDisplay(SimpleForm simpleForm, Widget formField, String fieldKey) {
      FormFieldLayoutConfiguration config = labelLookup.get(fieldKey);
      if (null != config && config.isHasLabel() && ((null != config.getLabelText() && !"".equals(config.getLabelText()))
            || null != config.getLabelHtml())) {
         FieldLabel fieldLabel = new FieldLabel(formField, config.getLabelText());

         if (null != config.getLabelHtml())
            fieldLabel.setHTML(config.getLabelHtml());
         fieldLabel.setLabelAlign(config.getLabelAlign());
         fieldLabel.setLabelWidth(config.getLabelWidth());

         return fieldLabel;
      }

      return formField;
   }

}
