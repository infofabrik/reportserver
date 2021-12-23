package net.datenwerke.rs.scp.client.scp.hookers;

import java.util.List;

import javax.inject.Inject;

import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;

import net.datenwerke.gf.client.managerhelper.mainpanel.SimpleFormView;
import net.datenwerke.gf.client.upload.FileUploadUiService;
import net.datenwerke.gf.client.upload.dto.FileToUpload;
import net.datenwerke.gf.client.upload.dto.UploadProperties;
import net.datenwerke.gf.client.upload.simpleform.FileUpload;
import net.datenwerke.gf.client.upload.simpleform.SFFCFileUpload;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenu;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenuItem;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCPasswordField;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.base.client.datasinks.hooks.DatasinkAuthenticatorConfiguratorHook;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.core.client.datasinkmanager.locale.DatasinksMessages;
import net.datenwerke.rs.fileserver.client.fileserver.FileServerUiModule;
import net.datenwerke.rs.scp.client.scp.ScpUiModule;
import net.datenwerke.rs.scp.client.scp.dto.ScpDatasinkDto;
import net.datenwerke.rs.scp.client.scp.dto.pa.ScpDatasinkDtoPA;

public class ScpPublicKeyAuthenticatorHooker implements DatasinkAuthenticatorConfiguratorHook {

   private final FileUploadUiService fileUploadService;
   
   public static final String AUTHENTICATION_TYPE = "public-key-auth";
   
   @Inject
   public ScpPublicKeyAuthenticatorHooker(FileUploadUiService fileUploadService) {
      this.fileUploadService = fileUploadService;
   }
   
   @Override
   public Widget configureForm(final SimpleFormView mainForm, final DatasinkDefinitionDto datasink) {
      SimpleForm form = SimpleForm.getInlineInstance();
      fileUploadService.prepareForUpload(form);

      form.setFieldWidth(760);
      
      form.beginFloatRow();
      form.addField(String.class, ScpDatasinkDtoPA.INSTANCE.username(), BaseMessages.INSTANCE.username());
      form.endRow();

      form.setFieldWidth(375);
      form.beginFloatRow();

      /* upload */
      form.addField(FileUpload.class, FileServerUiModule.UPLOAD_SERVLET_KEY_UPLOAD,
            DatasinksMessages.INSTANCE.privateKey(), new SFFCFileUpload() {
               @Override
               public UploadProperties getProperties() {
                  UploadProperties uploadProperties = new UploadProperties("file",
                        ScpUiModule.SCP_PRIVATE_KEY_UPLOAD_HANDLER_ID);
                  uploadProperties.addMetadata(ScpUiModule.SCP_UPLOAD_DATASINK_ID_FIELD,
                        String.valueOf(mainForm.getSelectedNode().getId()));

                  return uploadProperties;
               }

               @Override
               public boolean enableHTML5() {
                  return true;
               }

               @Override
               public void filesUploaded(List<FileToUpload> list) {
               }

            });

      /* passphrase */
      String passphraseKey = form.addField(String.class, ScpDatasinkDtoPA.INSTANCE.privateKeyPassphrase(),
            DatasinksMessages.INSTANCE.privateKeyPassphrase(), new SFFCPasswordField() {
               @Override
               public Boolean isPasswordSet() {
                  return ((ScpDatasinkDto) mainForm.getSelectedNode()).isHasPrivateKeyPassphrase();
               }
            }); // $NON-NLS-1$
      Menu clearPassphraseMenu = new DwMenu();
      MenuItem clearPassphraseItem = new DwMenuItem(DatasinksMessages.INSTANCE.clearPassphrase());
      clearPassphraseMenu.add(clearPassphraseItem);
      clearPassphraseMenu
            .addSelectionHandler(event -> ((ScpDatasinkDto) mainForm.getSelectedNode()).setPrivateKeyPassphrase(null));
      form.addFieldMenu(passphraseKey, clearPassphraseMenu);

      form.endRow();

      form.bind(datasink);

      return form;
   }

   @Override
   public String getAuthenticatorLabel() {
      return DatasinksMessages.INSTANCE.publicKeyAuthenticationType();
   }

   @Override
   public boolean consumes(DatasinkDefinitionDto datasink) {
      return datasink instanceof ScpDatasinkDto;
   }

   @Override
   public String getAuthenticatorName() {
      return AUTHENTICATION_TYPE;
   }

   @Override
   public boolean isUploadForm() {
      return true;
   }

}
