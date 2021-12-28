package net.datenwerke.rs.dsbundle.client.dsbundle;

import java.util.List;

public class DatasourceBundleUiServiceImpl implements DatasourceBundleUiService {

   private List<String> availbaleKeys;

   @Override
   public void setAvailableBundleKeys(List<String> keys) {
      this.availbaleKeys = keys;
   }

   @Override
   public List<String> getAvailableBundleKeys() {
      return availbaleKeys;
   }

}
