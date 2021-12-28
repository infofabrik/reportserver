package net.datenwerke.rs.dropbox.client.dropbox;

import java.util.HashMap;
import java.util.Map;

import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;

public class DropboxUiServiceImpl implements DropboxUiService {

   private Map<StorageType, Boolean> enabledConfigs = new HashMap<>();

   @Override
   public Map<StorageType, Boolean> getStorageEnabledConfigs() {
      return enabledConfigs;
   }

   public void setEnabledConfigs(Map<StorageType, Boolean> enabledConfigs) {
      this.enabledConfigs = enabledConfigs;
   }

}
