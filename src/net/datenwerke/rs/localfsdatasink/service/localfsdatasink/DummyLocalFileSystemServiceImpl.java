package net.datenwerke.rs.localfsdatasink.service.localfsdatasink;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import net.datenwerke.rs.localfsdatasink.service.localfsdatasink.definitions.LocalFileSystemDatasink;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;

public class DummyLocalFileSystemServiceImpl implements LocalFileSystemService {

   @Override
   public void sendToLocalFileSystem(Object report, LocalFileSystemDatasink localFileSystemDatasink, String filename,
         String folder) throws IOException {
      
   }

   @Override
   public Map<StorageType, Boolean> getLocalFileSystemEnabledConfigs() {
      return Collections.emptyMap();
   }

   @Override
   public boolean isLocalFileSystemEnabled() {
      return false;
   }

   @Override
   public boolean isLocalFileSystemSchedulingEnabled() {
      return false;
   }

   @Override
   public void testLocalFileSystemDataSink(LocalFileSystemDatasink localFileSystemDatasink) throws IOException {
      
   }


}