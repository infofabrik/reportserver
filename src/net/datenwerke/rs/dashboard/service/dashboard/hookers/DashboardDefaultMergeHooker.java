package net.datenwerke.rs.dashboard.service.dashboard.hookers;


import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.assistedinject.Assisted;

import net.datenwerke.rs.dashboard.service.dashboard.DashboardService;
import net.datenwerke.rs.dashboard.service.dashboard.entities.AbstractDashboardManagerNode;
import net.datenwerke.rs.utils.entitycloner.EntityClonerService;
import net.datenwerke.rs.utils.entitymerge.service.hooker.EntityMergeHooker;
import net.datenwerke.rs.utils.entitymerge.service.hooks.EntityMergeHook;


public class DashboardDefaultMergeHooker<T extends AbstractDashboardManagerNode> extends EntityMergeHooker
      implements EntityMergeHook {

   private final Provider<DashboardService> service;
   
   @Inject
   public DashboardDefaultMergeHooker(
         Provider<DashboardService> service, 
         Provider<EntityClonerService> cloneService, 
         @Assisted Class<T> targetClazz) {
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
