package net.datenwerke.rs.onedrive.service.onedrive;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import net.datenwerke.rs.onedrive.service.onedrive.definitions.OneDriveDatasink;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;

public class DummyOneDriveServiceImpl implements OneDriveService {

   @Override
   public void exportIntoDatasink(Object report, OneDriveDatasink oneDriveDatasink, String filename, String folder)
         throws IOException {
   }

   @Override
   public Map<StorageType, Boolean> getEnabledConfigs() {
      return Collections.emptyMap();
   }

   @Override
   public boolean isEnabled() {
      return false;
   }

   @Override
   public boolean isSchedulingEnabled() {
      return false;
   }

   @Override
   public void testDatasink(OneDriveDatasink OneDriveDatasink) throws IOException {
   }

   @Override
   public Optional<OneDriveDatasink> getDefaultDatasink() {
      return null;
   }

}