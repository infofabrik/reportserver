package net.datenwerke.rs.printer.client.printer.hookers;

import java.util.Map;
import java.util.Optional;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.login.LoginService;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCStaticLabel;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.dummy.StaticLabel;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.core.client.datasinkmanager.locale.DatasinksMessages;
import net.datenwerke.rs.fileserver.client.fileserver.hooks.DatasinkSendToFormConfiguratorHook;
import net.datenwerke.rs.printer.client.printer.PrinterUiModule;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class PrinterSendToFormConfiguratorHooker implements DatasinkSendToFormConfiguratorHook {

   @Inject
   public PrinterSendToFormConfiguratorHooker(Provider<LoginService> loginServiceProvider) {
   }

   @Override
   public boolean consumes(Class<? extends DatasinkDefinitionDto> datasinkType) {
      return PrinterUiModule.TYPE.equals(datasinkType);
   }

   @Override
   public void installAdditionalFields(SimpleForm form) {
      form.addField(StaticLabel.class, new SFFCStaticLabel() {
         @Override
         public String getLabel() {
            return DatasinksMessages.INSTANCE.printerWarning();
         }
      });
   }

   @Override
   public Optional<Map<String, Object>> getAdditionalFieldsValues(SimpleForm form) {
      return Optional.empty();
   }

   @Override
   public String getWindowTitle() {
      return PrinterUiModule.NAME;
   }

   @Override
   public BaseIcon getIcon() {
      return PrinterUiModule.ICON;
   }

   @Override
   public int getWindowHeight() {
      return 330;
   }

   @Override
   public boolean isFolderedDatasink() {
      return false;
   }

   @Override
   public boolean isCanCompress() {
      return false;
   }
   
   @Override
   public boolean isAllowNameConfiguration() {
      return false;
   }
}