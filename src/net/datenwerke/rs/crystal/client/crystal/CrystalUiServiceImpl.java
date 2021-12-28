package net.datenwerke.rs.crystal.client.crystal;

import com.google.inject.Singleton;

@Singleton
public class CrystalUiServiceImpl implements CrystalUiService {

   private boolean available;

   public void setAvailable(boolean available) {
      this.available = available;
   }

   @Override
   public boolean isAvailable() {
      return available;
   }
}
