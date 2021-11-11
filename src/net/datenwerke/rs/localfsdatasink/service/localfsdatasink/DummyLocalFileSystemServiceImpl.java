package net.datenwerke.rs.localfsdatasink.service.localfsdatasink;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import net.datenwerke.rs.localfsdatasink.service.localfsdatasink.definitions.LocalFileSystemDatasink;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;

public class DummyLocalFileSystemServiceImpl implements LocalFileSystemService {

   @Override
   public void exportIntoDatasink(Object report, LocalFileSystemDatasink localFileSystemDatasink, String filename,
         String folder) throws IOException {

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
   public void testDatasink(LocalFileSystemDatasink localFileSystemDatasink) throws IOException {

   }

   @Override
   public Optional<LocalFileSystemDatasink> getDefaultDatasink() {
      return null;
   }

}