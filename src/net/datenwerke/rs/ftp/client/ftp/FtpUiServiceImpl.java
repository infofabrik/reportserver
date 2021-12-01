package net.datenwerke.rs.ftp.client.ftp;

import java.util.HashMap;
import java.util.Map;

import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;

public class FtpUiServiceImpl implements FtpUiService {

   private Map<StorageType, Boolean> enabledConfigs = new HashMap<>();
   
   @Override
   public Map<StorageType, Boolean> getStorageEnabledConfigs() {
      return enabledConfigs;
   }
   
   public void setEnabledConfigs(Map<StorageType, Boolean> enabledConfigs) {
      this.enabledConfigs = enabledConfigs;
   }

}
