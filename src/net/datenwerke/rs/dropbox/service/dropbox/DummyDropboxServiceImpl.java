package net.datenwerke.rs.dropbox.service.dropbox;

import java.util.Optional;

import net.datenwerke.rs.core.service.datasinkmanager.exceptions.DatasinkExportException;
import net.datenwerke.rs.dropbox.service.dropbox.definitions.DropboxDatasink;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;

public class DummyDropboxServiceImpl implements DropboxService {

   @Override
   public void exportIntoDatasink(Object report, DropboxDatasink dropboxDatasink, String filename, String folder)
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