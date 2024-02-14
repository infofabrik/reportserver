package net.datenwerke.rs.core.service.datasourcemanager.hookers;


import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.assistedinject.Assisted;

import net.datenwerke.rs.core.service.datasourcemanager.DatasourceService;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceDefinition;
import net.datenwerke.rs.utils.entitycloner.EntityClonerService;
import net.datenwerke.rs.utils.entitymerge.service.hooker.EntityMergeHooker;
import net.datenwerke.rs.utils.entitymerge.service.hooks.EntityMergeHook;


public class DatasourceDefaultMergeHooker<T extends DatasourceDefinition> extends EntityMergeHooker
      implements EntityMergeHook {

   private final Provider<DatasourceService> service;
   
   @Inject
   public DatasourceDefaultMergeHooker(
         Provider<DatasourceService> service, 
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
