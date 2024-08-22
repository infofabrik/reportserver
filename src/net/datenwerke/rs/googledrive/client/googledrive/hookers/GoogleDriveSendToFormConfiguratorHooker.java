package net.datenwerke.rs.googledrive.client.googledrive.hookers;

import java.util.Map;
import java.util.Optional;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.login.LoginService;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.fileserver.client.fileserver.FileServerUiService;
import net.datenwerke.rs.fileserver.client.fileserver.hooks.DatasinkSendToFormConfiguratorHook;
import net.datenwerke.rs.googledrive.client.googledrive.GoogleDriveUiModule;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class GoogleDriveSendToFormConfiguratorHooker implements DatasinkSendToFormConfiguratorHook {

   @Inject
   public GoogleDriveSendToFormConfiguratorHooker(Provider<LoginService> loginServiceProvider) {
   }

   @Override
   public boolean consumes(Class<? extends DatasinkDefinitionDto> datasinkType) {
      return GoogleDriveUiModule.TYPE.equals(datasinkType);
   }

   @Override
   public void installAdditionalFields(SimpleForm form) {
   }

   @Override
   public Optional<Map<String, Object>> getAdditionalFieldsValues(SimpleForm form) {
      return Optional.empty();
   }

   @Override
   public String getWindowTitle() {
      return GoogleDriveUiModule.NAME;
   }

   @Override
   public BaseIcon getIcon() {
      return GoogleDriveUiModule.ICON;
   }

   @Override
   public int getWindowHeight() {
      return FileServerUiService.DEFAULT_FILE_SEND_TO_WINDOW_HEIGHT;
   }

   @Override
   public boolean isFolderedDatasink() {
      return true;
   }
   
   @Override
   public boolean isCanCompress() {
      return true;
   }
   
   @Override
   public boolean isAllowNameConfiguration() {
      return true;
   }

}