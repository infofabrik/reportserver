package net.datenwerke.rs.fileserver.service.fileserver.hookers;


import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.assistedinject.Assisted;

import net.datenwerke.rs.fileserver.service.fileserver.FileServerService;
import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFile;
import net.datenwerke.rs.utils.entitycloner.EntityClonerService;
import net.datenwerke.rs.utils.entitymerge.service.hooker.EntityMergeHooker;
import net.datenwerke.rs.utils.entitymerge.service.hooks.EntityMergeHook;


public class FileServerDefaultMergeHooker<T extends FileServerFile> extends EntityMergeHooker
      implements EntityMergeHook {

   private final Provider<FileServerService> service;
   
   @Inject
   public FileServerDefaultMergeHooker(
         Provider<FileServerService> service, 
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
