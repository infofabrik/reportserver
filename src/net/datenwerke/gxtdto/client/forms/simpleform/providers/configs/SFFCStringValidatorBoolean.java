package net.datenwerke.gxtdto.client.forms.simpleform.providers.configs;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.EditorError;
import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.form.Validator;
import com.sencha.gxt.widget.core.client.form.error.DefaultEditorError;

import net.datenwerke.gxtdto.client.forms.locale.FormsMessages;
import net.datenwerke.gxtdto.client.i18n.I18nToolsUIService;

public class SFFCStringValidatorBoolean implements SFFCStringValidator {

   @Inject
   protected static I18nToolsUIService i18nTools;
   private boolean allowBlank;

   public Validator<String> getValidator() {
      return new Validator<String>() {

         @Override
         public List<EditorError> validate(Editor<String> editor, String value) {

            if (allowBlank && (null == value || value.isEmpty()))
               return null;

            if (value.equalsIgnoreCase("false") || value.equalsIgnoreCase("true")) {
               return null;
            }
            List<EditorError> list = new ArrayList<EditorError>();
            list.add(new DefaultEditorError(editor, FormsMessages.INSTANCE.invalidBoolean(), value));
            return list;
         }
      };
   }

   @Override
   public void setAllowBlank(boolean allowBlank) {
      this.allowBlank = allowBlank;
   }

}
