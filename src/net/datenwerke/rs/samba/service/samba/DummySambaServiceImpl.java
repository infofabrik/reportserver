package net.datenwerke.rs.samba.service.samba;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import net.datenwerke.rs.samba.service.samba.definitions.SambaDatasink;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;

public class DummySambaServiceImpl implements SambaService {

   @Override
   public void sendToSambaServer(Object report, SambaDatasink sambaDatasink, String filename, String folder)
         throws IOException {

   }

   @Override
   public Map<StorageType, Boolean> getSambaEnabledConfigs() {
      return Collections.emptyMap();
   }

   @Override
   public boolean isSambaEnabled() {
      return false;
   }

   @Override
   public boolean isSambaSchedulingEnabled() {
      return false;
   }

   @Override
   public void testSambaDatasink(SambaDatasink sambaDatasink) throws IOException {

   }

   @Override
   public Optional<SambaDatasink> getDefaultDatasink() {
      return null;
   }

}