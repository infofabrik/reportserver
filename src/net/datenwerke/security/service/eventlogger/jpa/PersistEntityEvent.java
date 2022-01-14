package net.datenwerke.security.service.eventlogger.jpa;

public class PersistEntityEvent extends JpaEvent {

   public PersistEntityEvent(Object entity, Object... properties) {
      super(entity, properties);
   }

   @Override
   public String getLoggedAction() {
      return "PERSIST_ENTITY";
   }

}
