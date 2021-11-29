package net.datenwerke.rs.googledrive.service.googledrive;

import java.util.Optional;

import net.datenwerke.rs.core.service.datasinkmanager.configs.DatasinkConfiguration;
import net.datenwerke.rs.core.service.datasinkmanager.exceptions.DatasinkExportException;
import net.datenwerke.rs.googledrive.service.googledrive.definitions.GoogleDriveDatasink;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;

public class DummyGoogleDriveServiceImpl implements GoogleDriveService {

   @Override
   public void exportIntoDatasink(Object report, GoogleDriveDatasink googleDriveDatasink, DatasinkConfiguration config)
         throws DatasinkExportException {

   }

   @Override
   public void testDatasink(GoogleDriveDatasink googleDriveDatasink) throws DatasinkExportException {
   }

   @Override
   public Optional<GoogleDriveDatasink> getDefaultDatasink() {
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