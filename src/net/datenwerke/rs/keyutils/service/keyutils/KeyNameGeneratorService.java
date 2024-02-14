package net.datenwerke.rs.keyutils.service.keyutils;

import groovy.lang.Closure;
import net.datenwerke.treedb.service.treedb.TreeDBManager;

public interface KeyNameGeneratorService {
   
   public static final int KEY_LENGTH = 50;

   String getNextCopyName(String name, Closure getAllNodes);

   String getNextCopyNameFileServerFile(String name, Closure getAllNodes);

   String getNextCopyKey(String key, TreeDBManager treeDBManager);

   /**
    * Uses a sha1 hashing to based on time-stamp and a random int to 
    * determine a random key of length 40
    * @return randomly generated key
    */
   String generateDefaultKey();
   
   /**
    * Uses a sha1 hashing to based on time-stamp and a random int to 
    * determine a random key of length 40. This is repeated until a
    * key is found that is not already used in the given treeDBManager.
    * This can be used to avoid the edge-cases of a duplicated key error
    * while using generateDefaultKey()
    * @param treeDBManager to be queried
    * @return randomly generated key
    */
   String generateDefaultKey(TreeDBManager treeDBManager);
}
