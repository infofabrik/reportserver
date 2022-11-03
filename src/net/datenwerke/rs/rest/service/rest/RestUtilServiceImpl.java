package net.datenwerke.rs.rest.service.rest;

import javax.inject.Inject;
import javax.inject.Provider;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.rest.objects.RestAbstractNode;
import net.datenwerke.rs.rest.objects.hooks.RestObjectProviderHook;

public class RestUtilServiceImpl implements RestUtilService {

   final Provider<HookHandlerService> hookHandlerServiceProvider;

   @Inject
   public RestUtilServiceImpl(Provider<HookHandlerService> hookHandlerServiceProvider) {
      this.hookHandlerServiceProvider = hookHandlerServiceProvider;
   }

   @Override
   public RestAbstractNode toRestObject(Object object) {
      return hookHandlerServiceProvider.get().getHookers(RestObjectProviderHook.class)
            .stream()
            .filter(hooker -> hooker.consumes(object))
            .map(hooker -> hooker.createRestObject(object))
            .findAny()
            .orElseThrow(() -> new UnsupportedOperationException(
                  "object of type " + object.getClass().getCanonicalName() + " cannot be converted to a REST object"));
   }

   @Override
   public RestAbstractNode toDetailedRestObject(Object object) {
      return hookHandlerServiceProvider.get().getHookers(RestObjectProviderHook.class)
      .stream()
      .filter(hooker -> hooker.consumes(object))
      .map(hooker -> hooker.createDetailedRestObject(object))
      .findAny()
      .orElseThrow(() -> new UnsupportedOperationException(
            "object of type " + object.getClass().getCanonicalName() + " cannot be converted to a REST object"));
   }

}
