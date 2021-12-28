package net.datenwerke.rs.scp.client.scp;

import java.util.HashMap;
import java.util.Map;

import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;

public class ScpUiServiceImpl implements ScpUiService {

   private Map<StorageType, Boolean> enabledConfigs = new HashMap<>();

   @Override
   public Map<StorageType, Boolean> getStorageEnabledConfigs() {
      return enabledConfigs;
   }

   public void setEnabledConfigs(Map<StorageType, Boolean> enabledConfigs) {
      this.enabledConfigs = enabledConfigs;
   }

}
