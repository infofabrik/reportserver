package net.datenwerke.security.service.eventlogger.jpa;

public class RemoveEntityEvent extends JpaEvent {

   public RemoveEntityEvent(Object entity, Object... properties) {
      super(entity, properties);
   }

   @Override
   public String getLoggedAction() {
      return "REMOVE_ENTITY";
   }

}
