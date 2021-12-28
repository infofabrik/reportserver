package net.datenwerke.rs.samba.client.samba.hookers;

import java.util.Map;
import java.util.Optional;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.login.LoginService;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.fileserver.client.fileserver.FileServerUiService;
import net.datenwerke.rs.fileserver.client.fileserver.hooks.DatasinkSendToFormConfiguratorHook;
import net.datenwerke.rs.samba.client.samba.SambaUiModule;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class SambaSendToFormConfiguratorHooker implements DatasinkSendToFormConfiguratorHook {

   @Inject
   public SambaSendToFormConfiguratorHooker(Provider<LoginService> loginServiceProvider) {
   }

   @Override
   public boolean consumes(Class<? extends DatasinkDefinitionDto> datasinkType) {
      return SambaUiModule.TYPE.equals(datasinkType);
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
      return SambaUiModule.NAME;
   }

   @Override
   public BaseIcon getIcon() {
      return SambaUiModule.ICON;
   }

   @Override
   public int getWindowHeight() {
      return FileServerUiService.DEFAULT_FILE_SEND_TO_WINDOW_HEIGHT;
   }

   @Override
   public boolean isFolderedDatasink() {
      return true;
   }

}