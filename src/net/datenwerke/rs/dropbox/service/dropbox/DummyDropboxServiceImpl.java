package net.datenwerke.rs.dropbox.service.dropbox;

import java.util.Optional;

import net.datenwerke.rs.core.service.datasinkmanager.configs.DatasinkConfiguration;
import net.datenwerke.rs.core.service.datasinkmanager.exceptions.DatasinkExportException;
import net.datenwerke.rs.dropbox.service.dropbox.definitions.DropboxDatasink;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;

public class DummyDropboxServiceImpl implements DropboxService {

   @Override
   public void exportIntoDatasink(Object report, DropboxDatasink dropboxDatasink, DatasinkConfiguration config)
         throws DatasinkExportException {

   }

   @Override
   public void testDatasink(DropboxDatasink dropboxDatasink) throws DatasinkExportException {
   }

   @Override
   public Optional<DropboxDatasink> getDefaultDatasink() {
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