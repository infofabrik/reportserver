package net.datenwerke.security.service.eventlogger.jpa;

public class AfterRemoveEntityEvent extends JpaEvent {

   public AfterRemoveEntityEvent(Object entity, Object... properties) {
      super(entity, properties);
   }

   @Override
   public String getLoggedAction() {
      return "AFTER_REMOVE_ENTITY";
   }

}
