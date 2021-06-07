package net.datenwerke.rs.scp.service.scp;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;
import net.datenwerke.rs.scp.service.scp.definitions.ScpDatasink;

public class DummyScpServiceImpl implements ScpService {

   @Override
   public void sendToScpServer(Object report, ScpDatasink scpDatasink, String filename, String folder)
         throws IOException {

   }

   @Override
   public Map<StorageType, Boolean> getScpEnabledConfigs() {
      return Collections.emptyMap();
   }

   @Override
   public boolean isScpEnabled() {
      return false;
   }

   @Override
   public boolean isScpSchedulingEnabled() {
      return false;
   }

   @Override
   public void testScpDatasink(ScpDatasink scpDatasink) throws IOException {

   }

   @Override
   public Optional<ScpDatasink> getDefaultDatasink() {
      return null;
   }

}