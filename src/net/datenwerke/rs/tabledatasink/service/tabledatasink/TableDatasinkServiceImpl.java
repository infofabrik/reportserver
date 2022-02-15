package net.datenwerke.rs.tabledatasink.service.tabledatasink;

import java.util.Objects;
import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Provider;

import net.datenwerke.rs.base.service.datasources.definitions.DatabaseDatasource;
import net.datenwerke.rs.base.service.reportengines.table.entities.TableReport;
import net.datenwerke.rs.core.service.datasinkmanager.DatasinkService;
import net.datenwerke.rs.core.service.datasinkmanager.configs.DatasinkConfiguration;
import net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkDefinition;
import net.datenwerke.rs.core.service.datasinkmanager.exceptions.DatasinkExportException;
import net.datenwerke.rs.core.service.datasourcemanager.DatasourceEEService;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;
import net.datenwerke.rs.tabledatasink.service.tabledatasink.annotations.DefaultTableDatasink;
import net.datenwerke.rs.tabledatasink.service.tabledatasink.definitions.TableDatasink;
import net.datenwerke.rsenterprise.license.service.annotations.EnterpriseChecked;
import net.datenwerke.rsenterprise.license.service.annotations.EnterpriseCheckedBypass;
import net.datenwerke.security.service.usermanager.entities.User;

@EnterpriseChecked
public class TableDatasinkServiceImpl implements TableDatasinkService {

   private final Provider<Optional<TableDatasink>> defaultDatasinkProvider;
   private final Provider<DatasinkService> datasinkServiceProvider;
   private final Provider<DatasourceEEService> datasourceEEServiceProvider;
   

   @Inject
   public TableDatasinkServiceImpl(
         @DefaultTableDatasink Provider<Optional<TableDatasink>> defaultDatasinkProvider,
         Provider<DatasinkService> datasinkServiceProvider,
         Provider<DatasourceEEService> datasourceEEServiceProvider
         ) {
      this.defaultDatasinkProvider = defaultDatasinkProvider;
      this.datasinkServiceProvider = datasinkServiceProvider;
      this.datasourceEEServiceProvider = datasourceEEServiceProvider;
   }

   @EnterpriseCheckedBypass
   @Override
   public String getDatasinkPropertyName() {
      return "tabledatasink";
   }

   @EnterpriseCheckedBypass
   @Override
   public StorageType getStorageType() {
      return StorageType.TABLE_DATASINK;
   }

   @EnterpriseCheckedBypass
   @Override
   public StorageType getSchedulingStorageType() {
      return StorageType.TABLE_DATASINK_SCHEDULING;
   }

   @Override
   public void doExportIntoDatasink(Object report, User user, DatasinkDefinition datasink, DatasinkConfiguration config)
         throws DatasinkExportException {
      if (!datasinkServiceProvider.get().isEnabled(this))
         throw new IllegalStateException("Table datasinks are disabled");
      if (!(datasink instanceof TableDatasink))
         throw new IllegalStateException("Not a table datasink");
      if (!(report instanceof TableReport)) 
         throw new IllegalStateException("Not a dynamic list");
      
      TableDatasink tableDatasink = (TableDatasink) datasink;
      
      Objects.requireNonNull(tableDatasink, "Datasink is null");
      
      String tableName = tableDatasink.getTableName();
      Objects.requireNonNull(tableName);
      
      if (!(tableDatasink.getDatasourceContainer().getDatasource() instanceof DatabaseDatasource))
         throw new IllegalArgumentException("not a DatabaseDatasource");
      
      DatasourceEEService datasourceEEService = datasourceEEServiceProvider.get();
      
      if (!datasourceEEService.tableExists((DatabaseDatasource)
            tableDatasink.getDatasourceContainer().getDatasource(), tableName)) 
         throw new IllegalArgumentException("Table '" + tableName + "' does not exist");
      
      try {
         datasourceEEServiceProvider.get().exportIntoTable((TableReport) report, user, tableDatasink);
      } catch (Exception e) {
         throw new DatasinkExportException(e);
      }

   }

   @EnterpriseCheckedBypass
   @Override
   public Optional<? extends DatasinkDefinition> getDefaultDatasink() {
      return defaultDatasinkProvider.get();
   }
   
}
