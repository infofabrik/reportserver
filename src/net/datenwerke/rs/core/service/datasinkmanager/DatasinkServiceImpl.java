package net.datenwerke.rs.core.service.datasinkmanager;

import static net.datenwerke.rs.core.service.datasinkmanager.DatasinkModule.PROPERTY_DEFAULT_DISABLED;
import static net.datenwerke.rs.core.service.datasinkmanager.DatasinkModule.PROPERTY_DEFAULT_SCHEDULING_ENABLED;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;

import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;
import net.datenwerke.rs.utils.config.ConfigService;

public class DatasinkServiceImpl implements DatasinkService {

   private final Provider<ConfigService> configServiceProvider;

   @Inject
   public DatasinkServiceImpl(Provider<ConfigService> configServiceProvider) {
      this.configServiceProvider = configServiceProvider;
   }

   @Override
   public String getDefaultFolder(FolderedDatasink datasink) {
      return datasink.getFolder();
   }

   @Override
   public boolean isEnabled(BasicDatasinkService datasinkService) {
      return !configServiceProvider.get().getConfigFailsafe(DatasinkModule.CONFIG_FILE)
            .getBoolean(datasinkService.getDatasinkPropertyName() + PROPERTY_DEFAULT_DISABLED, false);
   }

   @Override
   public boolean isSchedulingEnabled(BasicDatasinkService datasinkService) {
      return configServiceProvider.get().getConfigFailsafe(DatasinkModule.CONFIG_FILE)
            .getBoolean(datasinkService.getDatasinkPropertyName() + PROPERTY_DEFAULT_SCHEDULING_ENABLED, true);
   }

   @Override
   public Map<StorageType, Boolean> getEnabledConfigs(BasicDatasinkService datasinkService) {
      Map<StorageType, Boolean> configs = new HashMap<>();
      configs.put(datasinkService.getStorageType(), isEnabled(datasinkService));
      configs.put(datasinkService.getSchedulingStorageType(), isSchedulingEnabled(datasinkService));
      return configs;
   }

}
