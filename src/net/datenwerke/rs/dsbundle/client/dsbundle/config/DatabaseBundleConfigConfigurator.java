package net.datenwerke.rs.dsbundle.client.dsbundle.config;

import com.google.inject.Inject;

import net.datenwerke.gxtdto.client.utilityservices.toolbar.ToolbarService;
import net.datenwerke.rs.base.client.datasources.config.DatabaseDatasourceConfigConfigurator;
import net.datenwerke.rs.base.client.datasources.dto.DatabaseDatasourceConfigDto;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionConfigDto;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionDto;
import net.datenwerke.rs.dsbundle.client.dsbundle.dto.DatabaseBundleDto;

public class DatabaseBundleConfigConfigurator extends DatabaseDatasourceConfigConfigurator {

   @Inject
   public DatabaseBundleConfigConfigurator(ToolbarService toolbarService) {
      super(toolbarService);
   }

   public DatasourceDefinitionConfigDto createConfigObject() {
      return new DatabaseDatasourceConfigDto();
   }

   @Override
   public boolean consumes(DatasourceDefinitionDto datasourceDefinitionDto,
         DatasourceDefinitionConfigDto datasourceConfig) {
      return null != datasourceConfig && datasourceConfig instanceof DatabaseDatasourceConfigDto
            && datasourceDefinitionDto instanceof DatabaseBundleDto;
   }
}
