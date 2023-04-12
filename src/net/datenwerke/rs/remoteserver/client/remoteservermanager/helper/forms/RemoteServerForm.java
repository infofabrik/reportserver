package net.datenwerke.rs.remoteserver.client.remoteservermanager.helper.forms;

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
import net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.RemoteServerDto;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.pa.RemoteServerDtoPA;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.locale.RemoteServerMessages;

/**
 * 
 *
 */
public class RemoteServerForm extends SimpleFormView {

   public void configureSimpleForm(SimpleForm form) {
      /* configure form */
      form.setHeading(RemoteServerMessages.INSTANCE.editRemoteRsServer()
            + (getSelectedNode() == null ? "" : " (" + getSelectedNode().getId() + ")"));

      form.beginFloatRow();
      form.setFieldWidth(600);
      
      /* name */
      form.addField(String.class, RemoteServerDtoPA.INSTANCE.name(), BaseMessages.INSTANCE.name());
      
      form.setFieldWidth(500);
      
      /* key */
      form.addField(String.class, RemoteServerDtoPA.INSTANCE.key(), BaseMessages.INSTANCE.key(),
            new SFFCStringValidatorRegex(KeyValidator.KEY_REGEX, BaseMessages.INSTANCE.invalidKey()));

      form.endRow();
      
      form.setFieldWidth(1);
      
      /* description */
      form.addField(String.class, RemoteServerDtoPA.INSTANCE.description(), BaseMessages.INSTANCE.description(),
            new SFFCTextAreaImpl());

      /* REST URL */
      form.addField(String.class, RemoteServerDtoPA.INSTANCE.url(), BaseMessages.INSTANCE.restUrl());

      form.setFieldWidth(250);
      form.beginFloatRow();
      
      /* username */
      form.addField(String.class, RemoteServerDtoPA.INSTANCE.username(), BaseMessages.INSTANCE.username());
      
      form.setFieldWidth(850);
      
      /* api key */
      String apikey = form.addField(String.class, RemoteServerDtoPA.INSTANCE.apikey(), 
         BaseMessages.INSTANCE.apiKey(), new SFFCPasswordField() {
            @Override
            public Boolean isPasswordSet() {
               return ((RemoteServerDto) getSelectedNode()).isHasApikey();
            }
      }); // $NON-NLS-1$
      form.endRow();
      
      Menu clearPwMenu = new DwMenu();
      MenuItem clearApikeyItem = new DwMenuItem(BaseMessages.INSTANCE.clearApikey());
      clearPwMenu.add(clearApikeyItem);
      clearApikeyItem.addSelectionHandler(event -> ((RemoteServerDto) getSelectedNode()).setApikey(null));
      form.addFieldMenu(apikey, clearPwMenu);
      
   }

}
