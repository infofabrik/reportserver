package net.datenwerke.rs.localfsdatasink.client.localfsdatasink.ui;

import net.datenwerke.gf.client.managerhelper.mainpanel.SimpleFormView;
import net.datenwerke.gf.client.validator.KeyValidator;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCStringValidatorRegex;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.impl.SFFCTextAreaImpl;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.core.client.datasinkmanager.locale.DatasinksMessages;
import net.datenwerke.rs.localfsdatasink.client.localfsdatasink.dto.pa.LocalFileSystemDatasinkDtoPA;

public class LocalFileSystemDatasinkForm extends SimpleFormView {

   @Override
   protected void configureSimpleForm(SimpleForm form) {
      /* configure form */
      form.setHeading(DatasinksMessages.INSTANCE.editDatasink()
            + (getSelectedNode() == null ? "" : " (" + getSelectedNode().getId() + ")"));

      form.beginFloatRow();
      form.setFieldWidth(600);
      
      /* name */
      form.addField(String.class, LocalFileSystemDatasinkDtoPA.INSTANCE.name(), BaseMessages.INSTANCE.name());
      
      form.setFieldWidth(500);
      /* key */
      form.addField(String.class, LocalFileSystemDatasinkDtoPA.INSTANCE.key(), BaseMessages.INSTANCE.key(),
            new SFFCStringValidatorRegex(KeyValidator.KEY_REGEX, BaseMessages.INSTANCE.invalidKey()));
      
      form.endRow();
      
      form.setFieldWidth(1);

      /* description */
      form.addField(String.class, LocalFileSystemDatasinkDtoPA.INSTANCE.description(), BaseMessages.INSTANCE.description(),
            new SFFCTextAreaImpl());

      form.setFieldWidth(750);

      /* path */
      form.addField(String.class, LocalFileSystemDatasinkDtoPA.INSTANCE.path(), "path");

      /* folder */
      form.addField(String.class, LocalFileSystemDatasinkDtoPA.INSTANCE.folder(), BaseMessages.INSTANCE.folder());

   }

}