package net.datenwerke.rs.dropbox.service.dropbox;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import net.datenwerke.rs.dropbox.service.dropbox.definitions.DropboxDatasink;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;

public class DummyDropboxServiceImpl implements DropboxService {

   @Override
   public void exportIntoDatasink(Object report, DropboxDatasink dropboxDatasink, String filename, String folder)
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
   public void testDatasink(DropboxDatasink dropboxDatasink) throws IOException {
   }

   @Override
   public Optional<DropboxDatasink> getDefaultDatasink() {
      return null;
   }

}