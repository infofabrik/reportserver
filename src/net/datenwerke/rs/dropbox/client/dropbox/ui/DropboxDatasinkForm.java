package net.datenwerke.rs.dropbox.client.dropbox.ui;

import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;

import net.datenwerke.gf.client.managerhelper.mainpanel.SimpleFormView;
import net.datenwerke.gf.client.validator.KeyValidator;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenu;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenuItem;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCPasswordField;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCStringValidatorRegex;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.impl.SFFCTextAreaImpl;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.core.client.datasinkmanager.locale.DatasinksMessages;
import net.datenwerke.rs.dropbox.client.dropbox.dto.DropboxDatasinkDto;
import net.datenwerke.rs.dropbox.client.dropbox.dto.pa.DropboxDatasinkDtoPA;

public class DropboxDatasinkForm extends SimpleFormView {

   @Override
   protected void configureSimpleForm(SimpleForm form) {
      /* configure form */
      form.setHeading(DatasinksMessages.INSTANCE.editDatasink()
            + (getSelectedNode() == null ? "" : " (" + getSelectedNode().getId() + ")"));

      form.beginFloatRow();
      form.setFieldWidth(600);
      
      /* name */
      form.addField(String.class, DropboxDatasinkDtoPA.INSTANCE.name(), BaseMessages.INSTANCE.name());
      
      form.setFieldWidth(500);
      /* key */
      form.addField(String.class, DropboxDatasinkDtoPA.INSTANCE.key(), BaseMessages.INSTANCE.key(),
            new SFFCStringValidatorRegex(KeyValidator.KEY_REGEX, BaseMessages.INSTANCE.invalidKey()));
      
      form.endRow();
      
      form.setFieldWidth(1);

      /* description */
      form.addField(String.class, DropboxDatasinkDtoPA.INSTANCE.description(), BaseMessages.INSTANCE.description(),
            new SFFCTextAreaImpl());

      form.setFieldWidth(750);

      /* folder */
      form.addField(String.class, DropboxDatasinkDtoPA.INSTANCE.folder(), BaseMessages.INSTANCE.folder());

      /* app key */
      form.addField(String.class, DropboxDatasinkDtoPA.INSTANCE.appKey(), DatasinksMessages.INSTANCE.appKey());

      /* secret key */
      String secretKey = form.addField(String.class, DropboxDatasinkDtoPA.INSTANCE.secretKey(),
            DatasinksMessages.INSTANCE.secretKey(), new SFFCPasswordField() {
               @Override
               public Boolean isPasswordSet() {
                  return ((DropboxDatasinkDto) getSelectedNode()).isHasSecretKey();
               }
            }); // $NON-NLS-1$
      Menu clearPwMenu = new DwMenu();
      MenuItem clearPwItem = new DwMenuItem(BaseMessages.INSTANCE.clearPassword());
      clearPwMenu.add(clearPwItem);
      clearPwItem.addSelectionHandler(event -> ((DropboxDatasinkDto) getSelectedNode()).setAppKey(null));
      form.addFieldMenu(secretKey, clearPwMenu);

   }

}
