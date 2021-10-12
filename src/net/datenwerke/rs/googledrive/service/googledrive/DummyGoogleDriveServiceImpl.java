package net.datenwerke.rs.googledrive.service.googledrive;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import net.datenwerke.rs.googledrive.service.googledrive.definitions.GoogleDriveDatasink;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;

public class DummyGoogleDriveServiceImpl implements GoogleDriveService {

   @Override
   public void exportIntoGoogleDrive(Object report, GoogleDriveDatasink googleDriveDatasink, String filename,
         String folder) throws IOException {

   }

   @Override
   public Map<StorageType, Boolean> getStorageEnabledConfigs() {
      return Collections.emptyMap();
   }

   @Override
   public boolean isGoogleDriveEnabled() {
      return false;
   }

   @Override
   public boolean isGoogleDriveSchedulingEnabled() {
      return false;
   }

   @Override
   public void testGoogleDriveDatasink(GoogleDriveDatasink googleDriveDatasink) throws IOException {
   }

   @Override
   public Optional<GoogleDriveDatasink> getDefaultDatasink() {
      return null;
   }

}