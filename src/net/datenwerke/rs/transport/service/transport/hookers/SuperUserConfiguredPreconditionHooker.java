package net.datenwerke.rs.transport.service.transport.hookers;

import static net.datenwerke.rs.transport.service.transport.TransportService.CONFIG_FILE;
import static net.datenwerke.rs.transport.service.transport.TransportService.APPLY_CONFIG_USER;

import java.util.Optional;

import com.google.inject.Inject;

import net.datenwerke.rs.configservice.service.configservice.ConfigService;
import net.datenwerke.rs.transport.service.transport.PreconditionResult;
import net.datenwerke.rs.transport.service.transport.entities.Transport;
import net.datenwerke.rs.transport.service.transport.hookers.helper.PreconditionHelper;
import net.datenwerke.rs.transport.service.transport.hooks.ApplyPreconditionHook;
import net.datenwerke.security.service.usermanager.UserManagerService;
import net.datenwerke.security.service.usermanager.entities.User;

public class SuperUserConfiguredPreconditionHooker implements ApplyPreconditionHook {

   private final PreconditionHelper preconditionHelper;
   private final ConfigService configService;
   private final UserManagerService userManagerService;
   
   @Inject
   public SuperUserConfiguredPreconditionHooker(
         PreconditionHelper preconditionHelper,
         ConfigService configService,
         UserManagerService userManagerService
         ) {
      this.preconditionHelper = preconditionHelper;
      this.configService = configService;
      this.userManagerService = userManagerService;
   }
   
   @Override
   public String getKey() {
      return "SUPER_USER_CHECK";
   }

   @Override
   public PreconditionResult analyze(Transport transport) {
      if (!preconditionHelper.configFileExists(CONFIG_FILE))
         return new PreconditionResult(Optional.of(CONFIG_FILE + " not found"));

      String username = configService.getConfig(CONFIG_FILE).getString(APPLY_CONFIG_USER);
      if (null == username)
         return new PreconditionResult(Optional.of("Super user not configured"));

      User user = userManagerService.getUserByName(username);
      if (null == user)
         return new PreconditionResult(Optional.of("User '" + username + "' not found in the system"));

      if (!user.isSuperUser())
         return new PreconditionResult(Optional.of("User '" + username + "' is not a super user"));

      return new PreconditionResult(Optional.empty());
   }

}
