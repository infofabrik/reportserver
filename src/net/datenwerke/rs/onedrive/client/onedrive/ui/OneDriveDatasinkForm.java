package net.datenwerke.rs.onedrive.client.onedrive.ui;

import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;

import net.datenwerke.gf.client.managerhelper.mainpanel.SimpleFormView;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenu;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenuItem;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCPasswordField;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.impl.SFFCTextAreaImpl;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.core.client.datasinkmanager.locale.DatasinksMessages;
import net.datenwerke.rs.onedrive.client.onedrive.dto.OneDriveDatasinkDto;
import net.datenwerke.rs.onedrive.client.onedrive.dto.pa.OneDriveDatasinkDtoPA;

public class OneDriveDatasinkForm extends SimpleFormView {
   @Override
   protected void configureSimpleForm(SimpleForm form) {
      /* configure form */
      form.setHeading(DatasinksMessages.INSTANCE.editDatasink()
            + (getSelectedNode() == null ? "" : " (" + getSelectedNode().getId() + ")"));

      /* name */
      form.addField(String.class, OneDriveDatasinkDtoPA.INSTANCE.name(), BaseMessages.INSTANCE.name());

      /* description */
      form.addField(String.class, OneDriveDatasinkDtoPA.INSTANCE.description(), BaseMessages.INSTANCE.description(),
            new SFFCTextAreaImpl());

      form.setFieldWidth(750);

      /* tenant id */
      form.addField(String.class, OneDriveDatasinkDtoPA.INSTANCE.tenantId(), DatasinksMessages.INSTANCE.tenantId());

      /* base root */
      form.addField(String.class, OneDriveDatasinkDtoPA.INSTANCE.baseRoot(), DatasinksMessages.INSTANCE.baseRoot());

      /* folder */
      form.addField(String.class, OneDriveDatasinkDtoPA.INSTANCE.folder(), BaseMessages.INSTANCE.folder());

      /* app key */
      form.addField(String.class, OneDriveDatasinkDtoPA.INSTANCE.appKey(), DatasinksMessages.INSTANCE.appKey());

      /* secret key */
      String secretKey = form.addField(String.class, OneDriveDatasinkDtoPA.INSTANCE.secretKey(),
            DatasinksMessages.INSTANCE.secretKey(), new SFFCPasswordField() {
               @Override
               public Boolean isPasswordSet() {
                  return ((OneDriveDatasinkDto) getSelectedNode()).isHasSecretKey();
               }
            }); // $NON-NLS-1$
      Menu clearPwMenu = new DwMenu();
      MenuItem clearPwItem = new DwMenuItem(BaseMessages.INSTANCE.clearPassword());
      clearPwMenu.add(clearPwItem);
      clearPwItem.addSelectionHandler(event -> ((OneDriveDatasinkDto) getSelectedNode()).setAppKey(null));
      form.addFieldMenu(secretKey, clearPwMenu);

   }
}