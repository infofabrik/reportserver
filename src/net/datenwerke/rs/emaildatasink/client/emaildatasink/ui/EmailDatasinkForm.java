package net.datenwerke.rs.emaildatasink.client.emaildatasink.ui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;

import net.datenwerke.gf.client.managerhelper.mainpanel.SimpleFormView;
import net.datenwerke.gf.client.validator.KeyValidator;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenu;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenuItem;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCPasswordField;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCStringValidatorRegex;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.impl.SFFCStaticDropdownList;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.impl.SFFCTextAreaImpl;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.core.client.RsCoreUiModule;
import net.datenwerke.rs.core.client.datasinkmanager.locale.DatasinksMessages;
import net.datenwerke.rs.emaildatasink.client.emaildatasink.dto.EmailDatasinkDto;
import net.datenwerke.rs.emaildatasink.client.emaildatasink.dto.pa.EmailDatasinkDtoPA;

public class EmailDatasinkForm extends SimpleFormView {

   @Override
   protected void configureSimpleForm(SimpleForm form) {
      /* configure form */
      form.setHeading(DatasinksMessages.INSTANCE.editDatasink()
            + (getSelectedNode() == null ? "" : " (" + getSelectedNode().getId() + ")"));

      form.beginFloatRow();
      form.setFieldWidth(600);
      
      /* name */
      form.addField(String.class, EmailDatasinkDtoPA.INSTANCE.name(), BaseMessages.INSTANCE.name());
      
      form.setFieldWidth(500);
      /* key */
      form.addField(String.class, EmailDatasinkDtoPA.INSTANCE.key(), BaseMessages.INSTANCE.key(),
            new SFFCStringValidatorRegex(KeyValidator.KEY_REGEX, BaseMessages.INSTANCE.invalidKey()));
      
      form.endRow();
      
      form.setFieldWidth(1);

      form.addField(String.class, EmailDatasinkDtoPA.INSTANCE.description(), BaseMessages.INSTANCE.description(),
            new SFFCTextAreaImpl());

      form.setFieldWidth(750);
      form.beginFloatRow();

      /* host */
      form.addField(String.class, EmailDatasinkDtoPA.INSTANCE.host(), BaseMessages.INSTANCE.host());

      /* port */
      form.setFieldWidth(50);
      form.addField(Integer.class, EmailDatasinkDtoPA.INSTANCE.port(), BaseMessages.INSTANCE.port());
      form.endRow();

      form.setFieldWidth(400);
      form.beginFloatRow();

      /* username */
      form.addField(String.class, EmailDatasinkDtoPA.INSTANCE.username(), BaseMessages.INSTANCE.username());

      /* password */
      String passwordKey = form.addField(String.class, EmailDatasinkDtoPA.INSTANCE.password(),
            BaseMessages.INSTANCE.password(), new SFFCPasswordField() {
               @Override
               public Boolean isPasswordSet() {
                  return ((EmailDatasinkDto) getSelectedNode()).isHasPassword();
               }
            }); // $NON-NLS-1$
      Menu clearPwMenu = new DwMenu();
      MenuItem clearPwItem = new DwMenuItem(BaseMessages.INSTANCE.clearPassword());
      clearPwMenu.add(clearPwItem);
      clearPwItem.addSelectionHandler(event -> ((EmailDatasinkDto) getSelectedNode()).setPassword(null));
      form.addFieldMenu(passwordKey, clearPwMenu);

      form.endRow();

      form.setFieldWidth(300);
      form.beginFloatRow();

      /* ssl */
      form.addField(Boolean.class, EmailDatasinkDtoPA.INSTANCE.sslEnable(), "SSL");

      /* tls */
      form.addField(Boolean.class, EmailDatasinkDtoPA.INSTANCE.tlsEnable(), DatasinksMessages.INSTANCE.tlsEnable());

      /* tls */
      form.addField(Boolean.class, EmailDatasinkDtoPA.INSTANCE.tlsRequire(), DatasinksMessages.INSTANCE.tlsRequire());

      form.endRow();

      form.setFieldWidth(400);
      form.beginFloatRow();

      form.addField(String.class, EmailDatasinkDtoPA.INSTANCE.sender(), DatasinksMessages.INSTANCE.sender());

      form.addField(String.class, EmailDatasinkDtoPA.INSTANCE.senderName(), DatasinksMessages.INSTANCE.senderName());

      form.addField(Boolean.class, EmailDatasinkDtoPA.INSTANCE.forceSender(), DatasinksMessages.INSTANCE.forceSender());

      form.endRow();

      // encryption policy
      form.addField(List.class, EmailDatasinkDtoPA.INSTANCE.encryptionPolicy(),
            DatasinksMessages.INSTANCE.encryptionPolicy(), new SFFCStaticDropdownList<String>() {

               private Map<String, String> map;

               @Override
               public Map<String, String> getValues() {
                  if (null == map) {
                     map = new HashMap<>();
                     map.put(RsCoreUiModule.EMAIL_ENCRYPTION_MIXED, RsCoreUiModule.EMAIL_ENCRYPTION_MIXED);
                     map.put(RsCoreUiModule.EMAIL_ENCRYPTION_STRICT, RsCoreUiModule.EMAIL_ENCRYPTION_STRICT);
                  }

                  return map;
               }
            });

   }

}