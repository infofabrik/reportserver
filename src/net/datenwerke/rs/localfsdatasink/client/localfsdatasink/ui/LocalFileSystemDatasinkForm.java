package net.datenwerke.rs.localfsdatasink.client.localfsdatasink.ui;

import net.datenwerke.gf.client.managerhelper.mainpanel.SimpleFormView;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.impl.SFFCTextAreaImpl;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.core.client.datasinkmanager.locale.DatasinksMessages;
import net.datenwerke.rs.ftp.client.ftp.dto.pa.FtpDatasinkDtoPA;
import net.datenwerke.rs.localfsdatasink.client.localfsdatasink.dto.pa.LocalFileSystemDatasinkDtoPA;

public class LocalFileSystemDatasinkForm extends SimpleFormView {

   @Override
   protected void configureSimpleForm(SimpleForm form) {
      /* configure form */
      form.setHeading(DatasinksMessages.INSTANCE.editDatasink()
            + (getSelectedNode() == null ? "" : " (" + getSelectedNode().getId() + ")"));

      /* name */
      form.addField(String.class, FtpDatasinkDtoPA.INSTANCE.name(), BaseMessages.INSTANCE.name());

      /* description */
      form.addField(String.class, FtpDatasinkDtoPA.INSTANCE.description(), BaseMessages.INSTANCE.description(),
            new SFFCTextAreaImpl());

      form.setFieldWidth(750);

      /* path */
      form.addField(String.class, LocalFileSystemDatasinkDtoPA.INSTANCE.path(), "path");

      /* folder */
      form.addField(String.class, LocalFileSystemDatasinkDtoPA.INSTANCE.folder(), BaseMessages.INSTANCE.folder());

   }

}