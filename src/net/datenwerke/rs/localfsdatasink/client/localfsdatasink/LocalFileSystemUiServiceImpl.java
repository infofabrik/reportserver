package net.datenwerke.rs.localfsdatasink.client.localfsdatasink;

import java.util.HashMap;
import java.util.Map;

import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;

public class LocalFileSystemUiServiceImpl implements LocalFileSystemUiService {

   private Map<StorageType, Boolean> enabledConfigs = new HashMap<>();
   
   @Override
   public Map<StorageType, Boolean> getStorageEnabledConfigs() {
      return enabledConfigs;
   }
   
   public void setEnabledConfigs(Map<StorageType, Boolean> enabledConfigs) {
      this.enabledConfigs = enabledConfigs;
   }

}
