package net.datenwerke.rs.transport.client.transport.ui.forms;

import net.datenwerke.gf.client.managerhelper.mainpanel.SimpleFormView;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCReadOnly;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.impl.SFFCTextAreaImpl;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.license.client.locale.LicenseMessages;
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

      form.addField(Boolean.class, TransportDtoPA.INSTANCE.closed(), TransportMessages.INSTANCE.closed());
      form.endRow();

      form.setFieldWidth(1);

      /* description */
      form.addField(String.class, TransportDtoPA.INSTANCE.description(), BaseMessages.INSTANCE.description(),
            new SFFCTextAreaImpl());

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
      form.addField(String.class, TransportDtoPA.INSTANCE.serverId(), LicenseMessages.INSTANCE.serverIdLabel(),
            SFFCReadOnly.TRUE);
      form.endRow();

      form.beginFloatRow();
      form.setFieldWidth(600);
      form.addField(String.class, TransportDtoPA.INSTANCE.rsVersion(), BaseMessages.INSTANCE.reportServerVersion(),
            SFFCReadOnly.TRUE);
      form.setFieldWidth(500);
      form.addField(String.class, TransportDtoPA.INSTANCE.rsSchemaVersion(), BaseMessages.INSTANCE.schemaVersion(),
            SFFCReadOnly.TRUE);
      form.endRow();
   }

}
