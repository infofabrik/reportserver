package net.datenwerke.security.service.eventlogger.jpa;

public class AfterPersistEntityEvent extends JpaEvent {

   public AfterPersistEntityEvent(Object entity, Object... properties) {
      super(entity, properties);
   }

   @Override
   public String getLoggedAction() {
      return "AFTER_PERSIST_ENTITY";
   }

}
