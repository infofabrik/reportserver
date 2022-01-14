package net.datenwerke.security.service.security.action;

import net.datenwerke.security.service.security.Securee;
import net.datenwerke.security.service.security.SecurityServiceSecuree;

public abstract class SecurityActionImpl implements SecurityAction {

   public Class<? extends Securee> getSecuree() {
      return SecurityServiceSecuree.class;
   }

   @Override
   public boolean requireInheritance() {
      return false;
   }
}
