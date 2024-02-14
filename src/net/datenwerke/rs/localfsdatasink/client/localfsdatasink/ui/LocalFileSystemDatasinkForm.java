package net.datenwerke.rs.localfsdatasink.client.localfsdatasink.ui;

import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.core.client.datasinkmanager.ui.forms.DatasinkSimpleForm;
import net.datenwerke.rs.localfsdatasink.client.localfsdatasink.dto.pa.LocalFileSystemDatasinkDtoPA;

public class LocalFileSystemDatasinkForm extends DatasinkSimpleForm {
   
   protected void configureSimpleFormCustomFields(SimpleForm form) {
      form.setFieldWidth(750);
      /* path */
      form.addField(String.class, LocalFileSystemDatasinkDtoPA.INSTANCE.path(), "path");

      /* folder */
      form.addField(String.class, LocalFileSystemDatasinkDtoPA.INSTANCE.folder(), BaseMessages.INSTANCE.folder());

   }

}