package net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.impl;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.EditorError;
import com.sencha.gxt.widget.core.client.form.Validator;
import com.sencha.gxt.widget.core.client.form.error.DefaultEditorError;

import net.datenwerke.gxtdto.client.forms.locale.FormsMessages;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCStringValidator;

public class SFFCStringValidatorImplementation implements SFFCStringValidator {

   private boolean allowBlank;

   @Override
   public Validator<String> getValidator() {
      return new Validator<String>() {
         @Override
         public List<EditorError> validate(Editor<String> editor, String value) {

            if (allowBlank && (null == value || value.isEmpty()))
               return null;

            if (!value.matches("[a-zA-Z][_a-zA-Z0-9]*")) {
               List<EditorError> list = new ArrayList<EditorError>();
               list.add(new DefaultEditorError(editor, FormsMessages.INSTANCE.fieldMustBeAlphaNumeric(), value));
               return list;
            }
            return null;
         }
      };
   }

   @Override
   public void setAllowBlank(boolean allowBlank) {
      this.allowBlank = allowBlank;
   }
}