package net.datenwerke.rs.core.service.datasinkmanager.hookers;


import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.assistedinject.Assisted;

import net.datenwerke.rs.core.service.datasinkmanager.DatasinkTreeService;
import net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkDefinition;
import net.datenwerke.rs.utils.entitycloner.EntityClonerService;
import net.datenwerke.rs.utils.entitymerge.service.hooker.EntityMergeHooker;
import net.datenwerke.rs.utils.entitymerge.service.hooks.EntityMergeHook;


public class DatasinkDefaultMergeHooker<T extends DatasinkDefinition> extends EntityMergeHooker
      implements EntityMergeHook {

   private final Provider<DatasinkTreeService> service;
   
   @Inject
   public DatasinkDefaultMergeHooker(
         Provider<DatasinkTreeService> service, 
         Provider<EntityClonerService> cloneService, 
         @Assisted Class<T> targetClazz
         ) {
      super(targetClazz, cloneService);
      this.service = service;
   }

   @Override
   protected void mergeSpecialFields(Object oldInstance, Object newInstance) {
   }

   @Override
   protected void commitChanges(Object oldInstance) {
      service.get().merge((T) oldInstance);     
   }

}
