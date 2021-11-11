package net.datenwerke.rs.samba.service.samba;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import net.datenwerke.rs.samba.service.samba.definitions.SambaDatasink;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;

public class DummySambaServiceImpl implements SambaService {

   @Override
   public void exportIntoDatasink(Object report, SambaDatasink sambaDatasink, String filename, String folder)
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
   public void testDatasink(SambaDatasink sambaDatasink) throws IOException {

   }

   @Override
   public Optional<SambaDatasink> getDefaultDatasink() {
      return null;
   }

}