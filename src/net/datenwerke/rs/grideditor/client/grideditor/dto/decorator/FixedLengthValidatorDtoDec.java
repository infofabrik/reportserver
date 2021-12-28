package net.datenwerke.rs.grideditor.client.grideditor.dto.decorator;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.EditorError;
import com.sencha.gxt.widget.core.client.form.Validator;
import com.sencha.gxt.widget.core.client.form.error.DefaultEditorError;
import com.sencha.gxt.widget.core.client.form.validator.AbstractValidator;

import net.datenwerke.rs.grideditor.client.grideditor.dto.FixedLengthValidatorDto;

/**
 * Dto Decorator for {@link FixedLengthValidatorDto}
 *
 */
public class FixedLengthValidatorDtoDec extends FixedLengthValidatorDto {

   private static final long serialVersionUID = 1L;

   public FixedLengthValidatorDtoDec() {
      super();
   }

   @Override
   public Validator<?> getValidator() {
      return new AbstractValidator<Object>() {

         @Override
         public List<EditorError> validate(Editor<Object> editor, Object value) {
            if (value == null || String.valueOf(value).length() != getLength()) {
               List<EditorError> errors = new ArrayList<EditorError>();
               errors.add(new DefaultEditorError(editor, getErrorMsg(), ""));
               return errors;
            }
            return null;
         }
      };
   }
}
