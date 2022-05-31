package net.datenwerke.rs.tabledatasink.client.tabledatasink.hookers;

import java.util.Map;
import java.util.Optional;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.login.LoginService;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.fileserver.client.fileserver.FileServerUiService;
import net.datenwerke.rs.fileserver.client.fileserver.hooks.DatasinkSendToFormConfiguratorHook;
import net.datenwerke.rs.tabledatasink.client.tabledatasink.TableDatasinkUiModule;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class TableDatasinkSendToFormConfiguratorHooker implements DatasinkSendToFormConfiguratorHook {

   @Inject
   public TableDatasinkSendToFormConfiguratorHooker(Provider<LoginService> loginServiceProvider) {
   }

   @Override
   public boolean consumes(Class<? extends DatasinkDefinitionDto> datasinkType) {
      return TableDatasinkUiModule.TYPE.equals(datasinkType);
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
      return TableDatasinkUiModule.NAME;
   }

   @Override
   public BaseIcon getIcon() {
      return TableDatasinkUiModule.ICON;
   }

   @Override
   public int getWindowHeight() {
      return FileServerUiService.DEFAULT_FILE_SEND_TO_WINDOW_HEIGHT;
   }

   @Override
   public boolean isFolderedDatasink() {
      return false;
   }
   
   @Override
   public boolean isCanCompress() {
      return false;
   }

}