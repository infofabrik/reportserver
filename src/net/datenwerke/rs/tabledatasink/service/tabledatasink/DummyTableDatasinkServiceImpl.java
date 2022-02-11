package net.datenwerke.rs.tabledatasink.service.tabledatasink;

import java.util.Optional;

import net.datenwerke.rs.core.service.datasinkmanager.configs.DatasinkConfiguration;
import net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkDefinition;
import net.datenwerke.rs.core.service.datasinkmanager.exceptions.DatasinkExportException;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;
import net.datenwerke.rs.tabledatasink.service.tabledatasink.definitions.TableDatasink;

public class DummyTableDatasinkServiceImpl implements TableDatasinkService {

   @Override
   public Optional<TableDatasink> getDefaultDatasink() {
      return null;
   }

   @Override
   public String getDatasinkPropertyName() {
      return null;
   }

   @Override
   public StorageType getStorageType() {
      return null;
   }

   @Override
   public StorageType getSchedulingStorageType() {
      return null;
   }

   @Override
   public void doExportIntoDatasink(Object report, DatasinkDefinition datasinkDefinition, DatasinkConfiguration config)
         throws DatasinkExportException {
   }

}