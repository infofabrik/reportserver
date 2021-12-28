package net.datenwerke.rs.utils.entitycloner.config;

public class DefaultClonerConfig implements ClonerConfig {

   @Override
   public boolean nullReferencedEntities() {
      return false;
   }

   @Override
   public boolean cloneReferencedEntities() {
      return false;
   }

}
