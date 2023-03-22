package net.datenwerke.gf.client.validator;

import java.util.Collections;
import java.util.List;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.EditorError;
import com.sencha.gxt.widget.core.client.form.Validator;
import com.sencha.gxt.widget.core.client.form.error.DefaultEditorError;

import net.datenwerke.gxtdto.client.locale.BaseMessages;

public class KeyValidator implements Validator<String> {

   public static final String KEY_REGEX = "^[a-zA-Z0-9_\\-]*$";
   
   private final String msg;
   
   public KeyValidator(String msg) {
      this.msg = msg;
   }

   @Override
   public List<EditorError> validate(Editor<String> editor, String value) {
      if (null == value || value.matches(KEY_REGEX))
         return null;
      return Collections
            .singletonList(new DefaultEditorError(editor, null == msg? BaseMessages.INSTANCE.alphaNumericErrorMsg(): msg, value));
   }

}
