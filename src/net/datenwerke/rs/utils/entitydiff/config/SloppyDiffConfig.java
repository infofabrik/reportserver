package net.datenwerke.rs.utils.entitydiff.config;

public class SloppyDiffConfig extends StrictDiffConfig {

   @Override
   public boolean ignoreId() {
      return true;
   }

   @Override
   public boolean ignoreVersion() {
      return true;
   }

}
