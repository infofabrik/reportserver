package net.datenwerke.rs.samba.service.samba;

import java.io.IOException;
import java.net.SocketException;
import java.util.Collections;
import java.util.Map;

import net.datenwerke.rs.samba.service.samba.definitions.SambaDatasink;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;

public class DummySambaServiceImpl implements SambaService {

   @Override
   public void sendToSambaServer(Object report, SambaDatasink sambaDatasink, String filename, String folder)
         throws IOException, SocketException {
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
   public void testSambaDataSink(SambaDatasink sambaDatasink) throws IOException {
   }

   @Override
   public Map<StorageType, Boolean> getSambaEnabledConfigs() {
      return Collections.emptyMap();
   }

}