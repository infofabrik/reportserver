package net.datenwerke.eximport.ids;

import javax.persistence.Entity;

import com.google.inject.Inject;

import net.datenwerke.eximport.hooks.ExImportIdProviderHook;
import net.datenwerke.rs.utils.jpa.EntityUtils;

/**
 * Provides IDs for entities.
 * 
 *
 */
public class EntityIdProviderHooker implements ExImportIdProviderHook {

   private final EntityUtils jpaServices;

   @Inject
   public EntityIdProviderHooker(EntityUtils jpaServices) {

      /* store objects */
      this.jpaServices = jpaServices;
   }

   @Override
   public String provideIdFor(Object object) {
      if (null == object)
         return null;

      if (!object.getClass().isAnnotationPresent(Entity.class))
         return null;

      Object id = jpaServices.getId(object);

      if (null != id)
         return object.getClass().getName() + "-" + id.toString();

      return null;
   }

}
