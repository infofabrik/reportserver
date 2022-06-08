package net.datenwerke.rs.box.service.box;

import java.util.Optional;

import net.datenwerke.rs.box.service.box.definitions.BoxDatasink;
import net.datenwerke.rs.core.service.datasinkmanager.configs.DatasinkConfiguration;
import net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkDefinition;
import net.datenwerke.rs.core.service.datasinkmanager.exceptions.DatasinkExportException;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;
import net.datenwerke.security.service.usermanager.entities.User;

public class DummyBoxServiceImpl implements BoxService {

   @Override
   public Optional<BoxDatasink> getDefaultDatasink() {
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
   public void doExportIntoDatasink(Object data, User user, DatasinkDefinition datasinkDefinition,
         DatasinkConfiguration config) throws DatasinkExportException {
   }

}