package net.datenwerke.rs.onedrive.service.onedrive;

import java.io.IOException;
import java.util.Optional;

import net.datenwerke.rs.onedrive.service.onedrive.definitions.OneDriveDatasink;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;

public class DummyOneDriveServiceImpl implements OneDriveService {

   @Override
   public void exportIntoDatasink(Object report, OneDriveDatasink oneDriveDatasink, String filename, String folder)
         throws IOException {
   }

   @Override
   public void testDatasink(OneDriveDatasink OneDriveDatasink) throws IOException {
   }

   @Override
   public Optional<OneDriveDatasink> getDefaultDatasink() {
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