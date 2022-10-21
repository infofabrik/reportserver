package net.datenwerke.rs.amazons3.client.amazons3;

import java.util.HashMap;
import java.util.Map;

import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;

public class AmazonS3UiServiceImpl implements AmazonS3UiService {

   private Map<StorageType, Boolean> enabledConfigs = new HashMap<>();

   @Override
   public Map<StorageType, Boolean> getStorageEnabledConfigs() {
      return enabledConfigs;
   }

   public void setEnabledConfigs(Map<StorageType, Boolean> enabledConfigs) {
      this.enabledConfigs = enabledConfigs;
   }

}
