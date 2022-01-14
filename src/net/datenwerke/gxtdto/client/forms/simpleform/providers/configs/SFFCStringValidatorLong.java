package net.datenwerke.gxtdto.client.forms.simpleform.providers.configs;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.EditorError;
import com.sencha.gxt.widget.core.client.form.Validator;
import com.sencha.gxt.widget.core.client.form.error.DefaultEditorError;

import net.datenwerke.gxtdto.client.forms.locale.FormsMessages;

public class SFFCStringValidatorLong implements SFFCStringValidator {

   private boolean allowBlank;

   public Validator<String> getValidator() {
      return new Validator<String>() {

         @Override
         public List<EditorError> validate(Editor<String> editor, String value) {

            if (allowBlank && (null == value || value.isEmpty()))
               return null;

            try {
               Long.valueOf(value);
               return null;
            } catch (NumberFormatException e) {
               List<EditorError> list = new ArrayList<EditorError>();
               list.add(new DefaultEditorError(editor, FormsMessages.INSTANCE.invalidInteger(), value));
               return list;
            }
         }
      };
   }

   @Override
   public void setAllowBlank(boolean allowBlank) {
      this.allowBlank = allowBlank;
   }

}
