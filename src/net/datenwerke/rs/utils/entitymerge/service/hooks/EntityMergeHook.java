package net.datenwerke.rs.utils.entitymerge.service.hooks;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;

public interface EntityMergeHook extends Hook{
   
   /**
    * Decides if the hook can merge the two entities
    * @param oldInstance the object to be merged
    * @param newInstance the object with the target values
    * @return true if merge is possible otherwise false
    */
   public boolean consumes(Object oldInstance, Object newInstance);
   
   /**
    * Performs the merge of two entities
    * @param oldInstance the object to be merged
    * @param newInstance the object with the target values
    * @throws IllegalAccessException if the merging of fields failed
    */
   public void mergeEntity(Object oldInstance, Object newInstance) throws IllegalAccessException;
}
