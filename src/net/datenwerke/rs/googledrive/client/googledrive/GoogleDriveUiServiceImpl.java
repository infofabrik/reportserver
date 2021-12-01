package net.datenwerke.rs.googledrive.client.googledrive;

import java.util.HashMap;
import java.util.Map;

import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;

public class GoogleDriveUiServiceImpl implements GoogleDriveUiService {

   private Map<StorageType, Boolean> enabledConfigs = new HashMap<>();
   
   @Override
   public Map<StorageType, Boolean> getStorageEnabledConfigs() {
      return enabledConfigs;
   }
   
   public void setEnabledConfigs(Map<StorageType, Boolean> enabledConfigs) {
      this.enabledConfigs = enabledConfigs;
   }

}
