package net.datenwerke.rs.ftp.client.ftp.hookers;

import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;

import net.datenwerke.gf.client.managerhelper.mainpanel.SimpleFormView;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenu;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenuItem;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCPasswordField;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.base.client.datasinks.hooks.DatasinkAuthenticatorConfiguratorHook;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.core.client.datasinkmanager.locale.DatasinksMessages;
import net.datenwerke.rs.ftp.client.ftp.dto.FtpsDatasinkDto;
import net.datenwerke.rs.ftp.client.ftp.dto.pa.FtpsDatasinkDtoPA;

public class FtpsUsernamePasswordAuthenticatorHooker implements DatasinkAuthenticatorConfiguratorHook {
    
    public static final String AUTHENTICATION_TYPE = "username-password-auth";

    @Override
    public Widget configureForm(SimpleFormView mainForm, DatasinkDefinitionDto datasink) {
          SimpleForm form = SimpleForm.getInlineInstance();

          form.setFieldWidth(350);
          form.beginFloatRow();
          
          form.addField(String.class, FtpsDatasinkDtoPA.INSTANCE.username(), BaseMessages.INSTANCE.username());

          /* password */
          String passwordKey = form.addField(String.class, FtpsDatasinkDtoPA.INSTANCE.password(),
                BaseMessages.INSTANCE.password(), new SFFCPasswordField() {
                   @Override
                   public Boolean isPasswordSet() {
                      return ((FtpsDatasinkDto) mainForm.getSelectedNode()).isHasPassword();
                   }
                }); // $NON-NLS-1$
          Menu clearPwMenu = new DwMenu();
          MenuItem clearPwItem = new DwMenuItem(BaseMessages.INSTANCE.clearPassword());
          clearPwMenu.add(clearPwItem);
          clearPwItem.addSelectionHandler(event -> ((FtpsDatasinkDto) mainForm.getSelectedNode()).setPassword(null));
          form.addFieldMenu(passwordKey, clearPwMenu);
          
          form.endRow();

          form.bind(datasink);

          return form;
    }

    @Override
    public String getAuthenticatorLabel() {
        return DatasinksMessages.INSTANCE.userPasswordAuthenticationType();
    }

    @Override
    public String getAuthenticatorName() {
        return AUTHENTICATION_TYPE;
    }

    @Override
    public boolean consumes(DatasinkDefinitionDto datasink) {
       return datasink instanceof FtpsDatasinkDto;
    }

    @Override
    public boolean isUploadForm() {
        return false;
    }

}