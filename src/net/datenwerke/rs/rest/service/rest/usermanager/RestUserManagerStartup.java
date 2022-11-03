package net.datenwerke.rs.rest.service.rest.usermanager;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.rest.objects.hooks.RestObjectProviderHook;
import net.datenwerke.rs.rest.objects.usermanager.hookers.GroupObjectProviderHooker;
import net.datenwerke.rs.rest.objects.usermanager.hookers.OrganisationalUnitObjectProviderHooker;
import net.datenwerke.rs.rest.objects.usermanager.hookers.UserObjectProviderHooker;

public class RestUserManagerStartup {

   @Inject
   public RestUserManagerStartup( 
         final HookHandlerService hookHandler,
         final Provider<GroupObjectProviderHooker> groupObjectProviderHooker,
         final Provider<OrganisationalUnitObjectProviderHooker> organisationalUnitObjectProviderHooker,
         final Provider<UserObjectProviderHooker> userObjectProviderHooker
         ) {
      
      hookHandler.attachHooker(RestObjectProviderHook.class, groupObjectProviderHooker.get());
      hookHandler.attachHooker(RestObjectProviderHook.class, organisationalUnitObjectProviderHooker.get());
      hookHandler.attachHooker(RestObjectProviderHook.class, userObjectProviderHooker.get());
   }
}
