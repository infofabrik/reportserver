package net.datenwerke.rs.transport.service.transport.hookers;

import static org.apache.commons.lang3.exception.ExceptionUtils.getRootCauseMessage;

import java.util.Optional;

import com.google.inject.Inject;

import net.datenwerke.rs.transport.service.transport.PreconditionResult;
import net.datenwerke.rs.transport.service.transport.entities.Transport;
import net.datenwerke.rs.transport.service.transport.hooks.ApplyPreconditionHook;
import net.datenwerke.security.service.authenticator.AuthenticatorService;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.exceptions.ViolatedSecurityException;
import net.datenwerke.security.service.security.rights.Execute;
import net.datenwerke.security.service.security.rights.Read;

public class PermissionPreconditionHooker implements ApplyPreconditionHook {

   private final AuthenticatorService authenticatorService;
   private final SecurityService securityService;
   
   @Inject
   public PermissionPreconditionHooker(
         AuthenticatorService authenticatorService,
         final SecurityService securityService
         ) {
      this.authenticatorService = authenticatorService;
      this.securityService = securityService;
   }
   
   @Override
   public String getKey() {
      return "USER_PERMISSION_CHECK";
   }

   @Override
   public PreconditionResult analyze(Transport transport) {
      if (!authenticatorService.isAuthenticated())
         return new PreconditionResult(Optional.of("No user authenticated"));
      
      try {
         securityService.assertRights(transport, Read.class, Execute.class);
      } catch (ViolatedSecurityException e) {
         return new PreconditionResult(Optional.of(getRootCauseMessage(e)));
      }
         
      return new PreconditionResult(Optional.empty());
   }

}
