package net.datenwerke.security.service.eventlogger.jpa;

public class AfterForceRemoveEntityEvent extends JpaEvent {

   public AfterForceRemoveEntityEvent(Object entity, Object... properties) {
      super(entity, properties);
   }

   @Override
   public String getLoggedAction() {
      return "AFTER_FORCE_REMOVE_ENTITY";
   }

}
