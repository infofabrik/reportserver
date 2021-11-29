package net.datenwerke.rs.box.service.box;

import java.util.Optional;

import net.datenwerke.rs.box.service.box.definitions.BoxDatasink;
import net.datenwerke.rs.core.service.datasinkmanager.configs.DatasinkConfiguration;
import net.datenwerke.rs.core.service.datasinkmanager.exceptions.DatasinkExportException;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;

public class DummyBoxServiceImpl implements BoxService {

   @Override
   public void exportIntoDatasink(Object report, BoxDatasink boxDatasink, DatasinkConfiguration config)
         throws DatasinkExportException {

   }

   @Override
   public void testDatasink(BoxDatasink boxDatasink) throws DatasinkExportException {
   }

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

}