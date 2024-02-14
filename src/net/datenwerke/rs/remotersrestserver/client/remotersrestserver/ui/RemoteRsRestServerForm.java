package net.datenwerke.rs.remotersrestserver.client.remotersrestserver.ui;

import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;

import net.datenwerke.gf.client.managerhelper.mainpanel.SimpleFormView;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenu;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenuItem;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCAllowBlank;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCPasswordField;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCStringValidatorRegex;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.impl.SFFCTextAreaImpl;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.remotersrestserver.client.remotersrestserver.dto.RemoteRsRestServerDto;
import net.datenwerke.rs.remotersrestserver.client.remotersrestserver.dto.pa.RemoteRsRestServerDtoPA;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.locale.RemoteServerMessages;
import net.datenwerke.rs.utils.validator.shared.SharedRegex;

/**
 * 
 *
 */
public class RemoteRsRestServerForm extends SimpleFormView {

   public void configureSimpleForm(SimpleForm form) {
      /* configure form */
      form.setHeading(RemoteServerMessages.INSTANCE.editRemoteRsServer()
            + (getSelectedNode() == null ? "" : " (" + getSelectedNode().getId() + ")"));

      form.beginFloatRow();
      form.setFieldWidth(600);
      
      /* name */
      form.addField(String.class, RemoteRsRestServerDtoPA.INSTANCE.name(), BaseMessages.INSTANCE.name());
      
      form.setFieldWidth(500);
      
      /* key */
      form.addField(String.class, RemoteRsRestServerDtoPA.INSTANCE.key(), BaseMessages.INSTANCE.key(),
            new SFFCStringValidatorRegex(SharedRegex.KEY_REGEX, BaseMessages.INSTANCE.invalidKey()),
            new SFFCAllowBlank() {
         @Override
         public boolean allowBlank() {
            return false;
         }
      }); // $NON-NLS-1$

      form.endRow();
      
      form.setFieldWidth(1);
      
      /* description */
      form.addField(String.class, RemoteRsRestServerDtoPA.INSTANCE.description(), BaseMessages.INSTANCE.description(),
            new SFFCTextAreaImpl());

      /* REST URL */
      form.addField(String.class, RemoteRsRestServerDtoPA.INSTANCE.url(), BaseMessages.INSTANCE.restUrl());

      form.setFieldWidth(250);
      form.beginFloatRow();
      
      /* username */
      form.addField(String.class, RemoteRsRestServerDtoPA.INSTANCE.username(), BaseMessages.INSTANCE.username());
      
      form.setFieldWidth(850);
      
      /* api key */
      String apikey = form.addField(String.class, RemoteRsRestServerDtoPA.INSTANCE.apikey(), 
         BaseMessages.INSTANCE.apiKey(), new SFFCPasswordField() {
            @Override
            public Boolean isPasswordSet() {
               return ((RemoteRsRestServerDto) getSelectedNode()).isHasApikey();
            }
      }); // $NON-NLS-1$
      form.endRow();
      
      Menu clearPwMenu = new DwMenu();
      MenuItem clearApikeyItem = new DwMenuItem(BaseMessages.INSTANCE.clearApikey());
      clearPwMenu.add(clearApikeyItem);
      clearApikeyItem.addSelectionHandler(event -> ((RemoteRsRestServerDto) getSelectedNode()).setApikey(null));
      form.addFieldMenu(apikey, clearPwMenu);
      
   }

}
