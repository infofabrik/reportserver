package net.datenwerke.rs.core.service.datasinkmanager;

import static net.datenwerke.rs.core.service.datasinkmanager.DatasinkModule.PROPERTY_DEFAULT_DISABLED;
import static net.datenwerke.rs.core.service.datasinkmanager.DatasinkModule.PROPERTY_DEFAULT_SCHEDULING_ENABLED;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Provider;

import net.datenwerke.rs.configservice.service.configservice.ConfigService;
import net.datenwerke.rs.core.service.datasinkmanager.configs.DatasinkConfiguration;
import net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkDefinition;
import net.datenwerke.rs.core.service.datasinkmanager.exceptions.DatasinkExportException;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;

public class DatasinkServiceImpl implements DatasinkService {

   private final Provider<ConfigService> configServiceProvider;
   
   private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

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

   @Override
   public void testDatasink(DatasinkDefinition datasinkDefinition, BasicDatasinkService basicDatasinkService,
         DatasinkConfiguration config) throws DatasinkExportException {
      if (!isEnabled(basicDatasinkService))
         throw new IllegalStateException("datasink is disabled: " + basicDatasinkService);
      exportIntoDatasink(
            "ReportServer " + basicDatasinkService.getDatasinkPropertyName() + " Datasink Test "
                  + dateFormat.format(Calendar.getInstance().getTime()),
            datasinkDefinition, basicDatasinkService, config);
   }

   @Override
   public void exportIntoDatasink(Object report, DatasinkDefinition datasinkDefinition,
         BasicDatasinkService basicDatasinkService, DatasinkConfiguration config) throws DatasinkExportException {
      basicDatasinkService.doExportIntoDatasink(report, datasinkDefinition, config);
   }

   @Override
   public Optional<? extends DatasinkDefinition> getDefaultDatasink(BasicDatasinkService basicDatasinkService) {
      return basicDatasinkService.getDefaultDatasink();
   }

}
