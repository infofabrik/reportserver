package net.datenwerke.rs.samba.client.samba.ui;

import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;

import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenu;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenuItem;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCPasswordField;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.core.client.datasinkmanager.ui.forms.DatasinkSimpleForm;
import net.datenwerke.rs.samba.client.samba.dto.SambaDatasinkDto;
import net.datenwerke.rs.samba.client.samba.dto.pa.SambaDatasinkDtoPA;

/**
 * 
 *
 */
public class SambaDatasinkForm extends DatasinkSimpleForm {
   
   protected void configureSimpleFormCustomFields(SimpleForm form) {
      form.setFieldWidth(750);
      form.beginFloatRow();

      /* host */
      form.addField(String.class, SambaDatasinkDtoPA.INSTANCE.host(), BaseMessages.INSTANCE.host());

      form.setFieldWidth(50);
      /* port */
      form.addField(Integer.class, SambaDatasinkDtoPA.INSTANCE.port(), BaseMessages.INSTANCE.port());

      form.endRow();

      form.setFieldWidth(280);

      form.beginFloatRow();

      /* domain */
      form.addField(String.class, SambaDatasinkDtoPA.INSTANCE.domain(), "domain");

      /* username */
      form.addField(String.class, SambaDatasinkDtoPA.INSTANCE.username(), BaseMessages.INSTANCE.username());

      /* password */
      String passwordKey = form.addField(String.class, SambaDatasinkDtoPA.INSTANCE.password(),
            BaseMessages.INSTANCE.password(), new SFFCPasswordField() {
               @Override
               public Boolean isPasswordSet() {
                  return ((SambaDatasinkDto) getSelectedNode()).isHasPassword();
               }
            }); // $NON-NLS-1$
      Menu clearPwMenu = new DwMenu();
      MenuItem clearPwItem = new DwMenuItem(BaseMessages.INSTANCE.clearPassword());
      clearPwMenu.add(clearPwItem);
      clearPwItem.addSelectionHandler(event -> ((SambaDatasinkDto) getSelectedNode()).setPassword(null));
      form.addFieldMenu(passwordKey, clearPwMenu);

      form.endRow();

      form.setFieldWidth(860);

      /* folder */
      form.addField(String.class, SambaDatasinkDtoPA.INSTANCE.folder(), BaseMessages.INSTANCE.folder());

   }

}