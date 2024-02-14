package net.datenwerke.rs.saiku.client.datasource.ui;

import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.info.DefaultInfoConfig;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.info.InfoConfig;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;

import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenu;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenuItem;
import net.datenwerke.gxtdto.client.codemirror.CodeMirrorPanel.ToolBarEnhancer;
import net.datenwerke.gxtdto.client.dialog.error.DetailErrorDialog;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCCodeMirror;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCNoHtmlDecode;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCPasswordField;
import net.datenwerke.rs.core.client.datasourcemanager.locale.DatasourcesMessages;
import net.datenwerke.rs.core.client.datasourcemanager.ui.forms.DatasourceSimpleForm;
import net.datenwerke.rs.saiku.client.datasource.dto.MondrianDatasourceDto;
import net.datenwerke.rs.saiku.client.datasource.dto.pa.MondrianDatasourceDtoPA;
import net.datenwerke.rs.saiku.client.saiku.SaikuDao;
import net.datenwerke.rs.saiku.client.saiku.locale.SaikuMessages;

public class MondrianDatasourceForm extends DatasourceSimpleForm {

   private final SaikuDao saikuDao;

   @Inject
   public MondrianDatasourceForm(SaikuDao saikuDao) {

      /* store objects */
      this.saikuDao = saikuDao;
   }

   @Override
   protected void configureSimpleFormCustomFields(SimpleForm form) {

      final DwTextButton clearCacheButton = new DwTextButton(SaikuMessages.INSTANCE.clearCache());
      clearCacheButton.addSelectHandler(event -> clearCache());
      form.clearButtonBar();
      /* add clear cache button */
      form.addButton(clearCacheButton);

      form.addSubmitButton();

      /* configure form */
      /* add form fields */

      form.setFieldWidth(250);
      form.beginFloatRow();

      /* username */
      form.addField(String.class, MondrianDatasourceDtoPA.INSTANCE.username(), DatasourcesMessages.INSTANCE.username());

      /* password */
      String passwordKey = form.addField(String.class, MondrianDatasourceDtoPA.INSTANCE.password(),
            DatasourcesMessages.INSTANCE.password(), new SFFCPasswordField() {
               @Override
               public Boolean isPasswordSet() {
                  return ((MondrianDatasourceDto) getSelectedNode()).isHasPassword();
               }
            }); // $NON-NLS-1$
      Menu clearPwMenu = new DwMenu();
      MenuItem clearPwItem = new DwMenuItem(DatasourcesMessages.INSTANCE.clearPassword());
      clearPwMenu.add(clearPwItem);
      clearPwItem.addSelectionHandler(event -> ((MondrianDatasourceDto) getSelectedNode()).setPassword(null));
      form.addFieldMenu(passwordKey, clearPwMenu);

      form.endRow();

      /* url */
      form.setFieldWidth(1);
      form.addField(String.class, MondrianDatasourceDtoPA.INSTANCE.url(), DatasourcesMessages.INSTANCE.url());

      form.addField(String.class, MondrianDatasourceDtoPA.INSTANCE.properties(),
            SaikuMessages.INSTANCE.propertyProperties(), new SFFCCodeMirror() {

               @Override
               public int getWidth() {
                  return -1;
               }

               @Override
               public int getHeight() {
                  return 150;
               }

               @Override
               public String getLanguage() {
                  return "text/x-ini";
               }

               @Override
               public boolean lineNumbersVisible() {
                  return true;
               }

               @Override
               public ToolBarEnhancer getEnhancer() {
                  return null;
               }
            }, SFFCNoHtmlDecode.INSTANCE);

      form.addField(String.class, MondrianDatasourceDtoPA.INSTANCE.mondrianSchema(),
            SaikuMessages.INSTANCE.propertySchema(), new SFFCCodeMirror() {

               @Override
               public int getWidth() {
                  return -1;
               }

               @Override
               public int getHeight() {
                  return 300;
               }

               @Override
               public boolean lineNumbersVisible() {
                  return true;
               }

               @Override
               public String getLanguage() {
                  return "application/xml";
               }

               @Override
               public ToolBarEnhancer getEnhancer() {
                  return null;
               }
            }, SFFCNoHtmlDecode.INSTANCE);

   }

   private void clearCache() {
      saikuDao.clearCache((MondrianDatasourceDto) getSelectedNode(), new RsAsyncCallback<Void>() {
         @Override
         public void onSuccess(Void result) {
            InfoConfig infoConfig = new DefaultInfoConfig(SaikuMessages.INSTANCE.cacheCleared(),
                  SaikuMessages.INSTANCE.cacheIsCleared());
            infoConfig.setWidth(350);
            Info.display(infoConfig);
         }

         public void onFailure(Throwable ex) {
            new DetailErrorDialog(ex).show();
         }
      });
   }

}
