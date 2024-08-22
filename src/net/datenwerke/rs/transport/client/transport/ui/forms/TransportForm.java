package net.datenwerke.rs.transport.client.transport.ui.forms;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.EditorError;
import com.sencha.gxt.widget.core.client.form.Validator;
import com.sencha.gxt.widget.core.client.form.error.DefaultEditorError;

import net.datenwerke.gf.client.managerhelper.mainpanel.SimpleFormView;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCReadOnly;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCStringValidator;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.impl.SFFCTextAreaImpl;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.transport.client.transport.dto.pa.TransportDtoPA;
import net.datenwerke.rs.transport.client.transport.locale.TransportMessages;

public class TransportForm extends SimpleFormView {

   public void configureSimpleForm(SimpleForm form) {
      /* configure form */
      form.setHeading(TransportMessages.INSTANCE.editTransport()
            + (getSelectedNode() == null ? "" : " (" + getSelectedNode().getId() + ")"));

      form.beginFloatRow();
      form.setFieldWidth(600);

      /* key */
      form.addField(String.class, TransportDtoPA.INSTANCE.key(), BaseMessages.INSTANCE.key(), SFFCReadOnly.TRUE);
      
      form.setFieldWidth(500);
      form.addField(String.class, TransportDtoPA.INSTANCE.status(), TransportMessages.INSTANCE.status(),
            SFFCReadOnly.TRUE);

      form.addField(Boolean.class, TransportDtoPA.INSTANCE.closed(), TransportMessages.INSTANCE.closed());
      form.endRow();

      /* description */
      form.beginFloatRow();
      form.setFieldWidth(1110);
      form.addField(String.class, TransportDtoPA.INSTANCE.description(), BaseMessages.INSTANCE.description(),
            new SFFCStringValidator() {
               @Override
               public Validator<String> getValidator() {
                  return new Validator<String>() {
                     @Override
                     public List<EditorError> validate(Editor<String> editor, String value) {
                        if (value.length() > 200) {
                           List<EditorError> list = new ArrayList<EditorError>();
                           list.add(new DefaultEditorError(editor,
                                 BaseMessages.INSTANCE.stringLengthValidationError(200), value));
                           return list;
                        }
                        return null;
                     }
                  };
               }

               @Override
               public void setAllowBlank(boolean allowBlank) {
                  throw new RuntimeException("method not implemented: setAllowBlank");
               }
            }, new SFFCTextAreaImpl(0));
      form.endRow();

      form.beginFloatRow();
      form.setFieldWidth(600);

      /* created on */
      form.addField(String.class, TransportDtoPA.INSTANCE.createdOnStr(), BaseMessages.INSTANCE.createdOn(),
            SFFCReadOnly.TRUE);

      form.setFieldWidth(500);

      form.endRow();

      form.beginFloatRow();
      form.setFieldWidth(600);
      /* created by */
      form.addField(String.class, TransportDtoPA.INSTANCE.creatorFirstname(),
            TransportMessages.INSTANCE.createdByFirstname(), SFFCReadOnly.TRUE);
      form.setFieldWidth(500);
      form.addField(String.class, TransportDtoPA.INSTANCE.creatorLastname(),
            TransportMessages.INSTANCE.createdByLastname(), SFFCReadOnly.TRUE);
      form.endRow();

      form.beginFloatRow();
      form.setFieldWidth(600);
      form.addField(String.class, TransportDtoPA.INSTANCE.creatorUsername(),
            TransportMessages.INSTANCE.createdByUsername(), SFFCReadOnly.TRUE);
      form.setFieldWidth(500);
      form.addField(String.class, TransportDtoPA.INSTANCE.creatorEmail(), TransportMessages.INSTANCE.createdByEmail(),
            SFFCReadOnly.TRUE);
      form.endRow();

      form.beginFloatRow();
      form.setFieldWidth(600);
      form.addField(String.class, TransportDtoPA.INSTANCE.serverId(), TransportMessages.INSTANCE.serverIdLabel(),
            SFFCReadOnly.TRUE);
      form.endRow();

      form.beginFloatRow();
      form.setFieldWidth(600);
      form.addField(String.class, TransportDtoPA.INSTANCE.rsVersion(), TransportMessages.INSTANCE.reportServerVersion(),
            SFFCReadOnly.TRUE);
      form.setFieldWidth(500);
      form.addField(String.class, TransportDtoPA.INSTANCE.rsSchemaVersion(), TransportMessages.INSTANCE.schemaVersion(),
            SFFCReadOnly.TRUE);
      form.endRow();
      
      form.beginFloatRow();
      form.setFieldWidth(600);
      form.addField(String.class, TransportDtoPA.INSTANCE.importedOnStr(), TransportMessages.INSTANCE.importedOn(),
            SFFCReadOnly.TRUE);
      form.setFieldWidth(500);
      form.addField(String.class, TransportDtoPA.INSTANCE.importedByStr(), TransportMessages.INSTANCE.importedBy(),
            SFFCReadOnly.TRUE);
      form.endRow();

      form.beginFloatRow();
      form.setFieldWidth(600);
      form.addField(String.class, TransportDtoPA.INSTANCE.appliedOnStr(), TransportMessages.INSTANCE.appliedOn(),
            SFFCReadOnly.TRUE);
      form.setFieldWidth(500);
      form.addField(String.class, TransportDtoPA.INSTANCE.appliedByStr(), TransportMessages.INSTANCE.appliedBy(),
            SFFCReadOnly.TRUE);
      form.endRow();
      
      /* protocol */
      form.beginFloatRow();
      form.setFieldWidth(1110);
      form.addField(String.class, TransportDtoPA.INSTANCE.appliedProtocol(), TransportMessages.INSTANCE.appliedLog(),
            new SFFCTextAreaImpl(25), SFFCReadOnly.TRUE);
      form.endRow();
      
   }

}
