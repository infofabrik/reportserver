package net.datenwerke.eximport.im.enclosed;

import javax.persistence.EntityManager;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.eximport.ex.enclosed.EnclosedEntityExporter;
import net.datenwerke.eximport.hooks.ImporterPersistEnclosedEntityHook;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.utils.jpa.EntityUtils;

/**
 * 
 *
 */
public class EnclosedEntityImporter extends EnclosedObjectImporter {

   protected EntityUtils entityServices;
   protected Provider<EntityManager> entityManagerProvider;
   protected HookHandlerService hookHandler;

   @Inject
   public void setHookHandler(HookHandlerService hookHandler) {
      this.hookHandler = hookHandler;
   }

   @Inject
   public void setEntityServices(EntityUtils entityServices) {
      this.entityServices = entityServices;
   }

   @Inject
   public void setEntityManagerProvider(Provider<EntityManager> entityManagerProvider) {
      this.entityManagerProvider = entityManagerProvider;
   }

   @Override
   public Class<?>[] getRecognizedExporters() {
      return new Class<?>[] { EnclosedEntityExporter.class };
   }

   @Override
   public boolean postProcess(String id, Object object, boolean enclosed) {
      try {
         boolean foundHook = false;
         for (ImporterPersistEnclosedEntityHook persister : hookHandler
               .getHookers(ImporterPersistEnclosedEntityHook.class)) {
            if (persister.consumes(object)) {
               persister.persist(object);
               foundHook = true;
               break;
            }
         }

         if (!foundHook && entityServices.isEntity(object) && null == entityServices.getId(object))
            entityManagerProvider.get().persist(object);

         return true;
      } catch (Exception e) {
         throw new IllegalArgumentException(e);
      }
   }
}
