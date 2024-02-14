package net.datenwerke.gf.client.validator;

import java.util.Collections;
import java.util.List;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.EditorError;
import com.sencha.gxt.widget.core.client.form.Validator;
import com.sencha.gxt.widget.core.client.form.error.DefaultEditorError;

import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.utils.validator.shared.SharedRegex;

public class KeyValidator implements Validator<String> {

   private final String msg;
   
   public KeyValidator(String msg) {
      this.msg = msg;
   }

   @Override
   public List<EditorError> validate(Editor<String> editor, String value) {
      if (value.matches(SharedRegex.KEY_REGEX))
         return null;
      return Collections
            .singletonList(new DefaultEditorError(editor, null == msg? BaseMessages.INSTANCE.alphaNumericErrorMsg(): msg, value));
   }

}
