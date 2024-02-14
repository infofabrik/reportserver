package net.datenwerke.rs.utils.entitymerge.service;


public interface EntityMergeService {
   
   /**
    * This method merges entities (mostly AbstractNode types) using EntityMergeHookers 
    * @param a The object to be merged into
    * @param b The object with the target values
    * @throws IllegalAccessException if the merging of fields failed
    * @throws IllegalArgumentException if no hooker applicable to classes of a and b was found
    */
   public void mergeEntity(Object a, Object b) throws IllegalAccessException, IllegalArgumentException;
}
