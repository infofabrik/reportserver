package net.datenwerke.eximport;

import org.hibernate.proxy.HibernateProxy;

import com.google.inject.Inject;

import net.datenwerke.eximport.hooks.ExImportIdProviderHook;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;

/**
 * Helper service to provide IDs during the export.
 * 
 *
 */
public class ExImportIdService {

   private final HookHandlerService hookHandler;

   @Inject
   public ExImportIdService(HookHandlerService hookHandler) {

      /* store objects */
      this.hookHandler = hookHandler;
   }

   /**
    * Loops over all registered {@link ExImportIdProviderHook} Hookees to provide
    * an id for the given object.
    * 
    * @param object
    * @return the id
    */
   public String provideId(Object object) {
      for (ExImportIdProviderHook provider : hookHandler.getHookers(ExImportIdProviderHook.class)) {
         if (object instanceof HibernateProxy)
            object = ((HibernateProxy) object).getHibernateLazyInitializer().getImplementation();

         String id = provider.provideIdFor(object);
         if (null != id)
            return id;
      }
      return null;
   }
}
