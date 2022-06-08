package net.datenwerke.rs.ftp.client.ftp.ui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;

import net.datenwerke.gf.client.managerhelper.mainpanel.SimpleFormView;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenu;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenuItem;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCPasswordField;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.impl.SFFCStaticDropdownList;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.impl.SFFCTextAreaImpl;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.core.client.datasinkmanager.locale.DatasinksMessages;
import net.datenwerke.rs.ftp.client.ftp.dto.FtpDatasinkDto;
import net.datenwerke.rs.ftp.client.ftp.dto.pa.FtpDatasinkDtoPA;
import net.datenwerke.rs.ftp.client.ftp.hookers.FtpDatasinkConfigProviderHooker;

/**
 * 
 *
 */
public class FtpDatasinkForm extends SimpleFormView {

   public void configureSimpleForm(SimpleForm form) {
      /* configure form */
      form.setHeading(DatasinksMessages.INSTANCE.editDatasink()
            + (getSelectedNode() == null ? "" : " (" + getSelectedNode().getId() + ")"));

      /* name name */
      form.addField(String.class, FtpDatasinkDtoPA.INSTANCE.name(), BaseMessages.INSTANCE.name());

      form.addField(String.class, FtpDatasinkDtoPA.INSTANCE.description(), BaseMessages.INSTANCE.description(),
            new SFFCTextAreaImpl());

      form.setFieldWidth(750);
      form.beginFloatRow();

      /* host */
      form.addField(String.class, FtpDatasinkDtoPA.INSTANCE.host(), BaseMessages.INSTANCE.host());

      form.setFieldWidth(50);
      /* port */
      form.addField(Integer.class, FtpDatasinkDtoPA.INSTANCE.port(), BaseMessages.INSTANCE.port());

      form.endRow();

      form.setFieldWidth(400);
      form.beginFloatRow();

      /* username */
      form.addField(String.class, FtpDatasinkDtoPA.INSTANCE.username(), BaseMessages.INSTANCE.username());

      /* password */
      String passwordKey = form.addField(String.class, FtpDatasinkDtoPA.INSTANCE.password(),
            BaseMessages.INSTANCE.password(), new SFFCPasswordField() {
               @Override
               public Boolean isPasswordSet() {
                  return ((FtpDatasinkDto) getSelectedNode()).isHasPassword();
               }
            }); // $NON-NLS-1$
      Menu clearPwMenu = new DwMenu();
      MenuItem clearPwItem = new DwMenuItem(BaseMessages.INSTANCE.clearPassword());
      clearPwMenu.add(clearPwItem);
      clearPwItem.addSelectionHandler(event -> ((FtpDatasinkDto) getSelectedNode()).setPassword(null));
      form.addFieldMenu(passwordKey, clearPwMenu);

      form.endRow();

      form.setFieldWidth(0.3);

      // active / passive mode
      form.addField(List.class, FtpDatasinkDtoPA.INSTANCE.ftpMode(), DatasinksMessages.INSTANCE.mode(),
            new SFFCStaticDropdownList<String>() {

               private Map<String, String> map;

               @Override
               public Map<String, String> getValues() {
                  if (null == map) {
                     map = new HashMap<>();
                     map.put(DatasinksMessages.INSTANCE.active(), FtpDatasinkConfigProviderHooker.ACTIVE_MODE);
                     map.put(DatasinksMessages.INSTANCE.passive(), FtpDatasinkConfigProviderHooker.PASSIVE_MODE);
                  }

                  return map;
               }
            });

      form.setFieldWidth(810);

      /* folder */
      form.addField(String.class, FtpDatasinkDtoPA.INSTANCE.folder(), BaseMessages.INSTANCE.folder());

   }

}
