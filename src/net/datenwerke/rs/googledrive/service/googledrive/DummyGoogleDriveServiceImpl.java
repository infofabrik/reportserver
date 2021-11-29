package net.datenwerke.rs.googledrive.service.googledrive;

import java.io.IOException;
import java.util.Optional;

import net.datenwerke.rs.googledrive.service.googledrive.definitions.GoogleDriveDatasink;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;

public class DummyGoogleDriveServiceImpl implements GoogleDriveService {

   @Override
   public void exportIntoDatasink(Object report, GoogleDriveDatasink googleDriveDatasink, String filename,
         String folder) throws IOException {

   }

   @Override
   public void testDatasink(GoogleDriveDatasink googleDriveDatasink) throws IOException {
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