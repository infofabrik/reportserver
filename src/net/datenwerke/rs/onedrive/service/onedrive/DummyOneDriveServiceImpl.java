package net.datenwerke.rs.onedrive.service.onedrive;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import net.datenwerke.rs.onedrive.service.onedrive.definitions.OneDriveDatasink;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;

public class DummyOneDriveServiceImpl implements OneDriveService {

   @Override
   public void exportIntoOneDrive(Object report, OneDriveDatasink oneDriveDatasink, String filename, String folder)
         throws IOException {
   }

   @Override
   public Map<StorageType, Boolean> getStorageEnabledConfigs() {
      return Collections.emptyMap();
   }

   @Override
   public boolean isOneDriveEnabled() {
      return false;
   }

   @Override
   public boolean isOneDriveSchedulingEnabled() {
      return false;
   }

   @Override
   public void testOneDriveDatasink(OneDriveDatasink OneDriveDatasink) throws IOException {
   }

   @Override
   public Optional<OneDriveDatasink> getDefaultDatasink() {
      return null;
   }

}