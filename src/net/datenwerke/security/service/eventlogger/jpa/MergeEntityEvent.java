package net.datenwerke.security.service.eventlogger.jpa;

public class MergeEntityEvent extends JpaEvent {

   public MergeEntityEvent(Object entity, Object... properties) {
      super(entity, properties);
   }

   @Override
   public String getLoggedAction() {
      return "MERGE_ENTITY";
   }

}
