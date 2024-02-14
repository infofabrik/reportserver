package net.datenwerke.rs.keyutils.service.keyutils

import java.util.regex.Pattern

import javax.persistence.EntityManager
import javax.persistence.NoResultException

import org.hibernate.FlushMode

import com.google.inject.Inject
import com.google.inject.Provider

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService
import net.datenwerke.rs.keyutils.service.keyutils.hooks.GeneralGenerateDefaultKeyHook
import net.datenwerke.rs.keyutils.service.keyutils.hooks.SpecificGenerateDefaultKeyHook
import net.datenwerke.rs.utils.crypto.HashUtil
import net.datenwerke.treedb.service.treedb.TreeDBManager

class KeyNameGeneratorServiceImpl implements KeyNameGeneratorService {
   
   private final Provider<HashUtil> hashUtilProvider
   
   private final Provider<EntityManager> entityManager
   
   private final Provider<HookHandlerService> hookHandler;
   
   private final Pattern copiesPattern = ~/(?ix)    # case insensitive(i), ignore space(x)
   ^                                                # start of line
   .*                                               # any number of any characters
   \(copy                                           # (copy
   (\(\d*\))?                                       # any number of digits inside parenthesis (optional)
   \)                                               # )
   $                                                # end of line
                                                    # example matches:
                                                    # myfile(copy)
                                                    # myfile (copy)
                                                    # myfile (copy(1))
                                                    # myfile (copy(50))
   /
   
   @Inject
   public KeyNameGeneratorServiceImpl(
      Provider<HashUtil> hashUtilProvider,
      Provider<EntityManager> entityManager,
      Provider<HookHandlerService> hookhandler
   ) {
      this.hashUtilProvider = hashUtilProvider
      this.entityManager = entityManager
      this.hookHandler = hookhandler
   }
   
   @Override
   public String getNextCopyName(String name,  Closure getAllNodes) {
      def nodes = getAllNodes.doCall()
      List<String> allNodes = nodes.collect { it.Name }
      if(!name in allNodes)
         return name
      if (name ==~ copiesPattern) {
         int lastPosition = name.lastIndexOf(' (copy')-1
         name = name[0..lastPosition]
      } 

      String nextCopyName = "$name (copy)"
      if (!allNodes.contains(nextCopyName)) {
         return nextCopyName
      }

      for (long i = 2; i <= Integer.MAX_VALUE; i++) {
         nextCopyName = "$name (copy($i))"
         if (!allNodes.contains(nextCopyName)) {
            return nextCopyName
         }
      }

      return nextCopyName
   }

   @Override
   public String getNextCopyNameFileServerFile(String name, Closure getAllNodes) {
      def nodes = getAllNodes.doCall()
      List<String> allNodes = nodes.collect { it.Name }
      if(!(name in allNodes))
         return name
      String nextCopyName = ''
      String extension = ''

      if (name ==~ /.*\.[a-zA-Z0-9]*/) {
         int lastPosition = name.lastIndexOf('.')
         extension = name[lastPosition..name.size()-1]
         name = name[0..lastPosition-1]
      }

      if (name ==~ copiesPattern) {
         int lastPosition = name.lastIndexOf(' (copy')-1
         name = name[0..lastPosition]
      } 

      if (!allNodes.contains("$name (copy)$extension" as String)) {
         return "$name (copy)$extension"
      }

      for (long i = 2; i <= Integer.MAX_VALUE; i++) {
         nextCopyName = "$name (copy($i))$extension"
         if (!allNodes.contains(nextCopyName)) {
            return nextCopyName
         }
      }
      
      return nextCopyName
   }

   @Override
   public String getNextCopyKey(String key, TreeDBManager treeDBManager) { 
      if (!key)
         return generateDefaultKey()
         
      if (key ==~ /.*_copy|.*_copy_[0-9]*/) {
         int lastPosition = key.lastIndexOf("_copy");
         key = key.substring(0, lastPosition);
      }
      
      def findNextCopyKey = {
         String nextCopyKey = key + "_copy";
         if (null == treeDBManager.getNodeByKey(nextCopyKey)) {
            return nextCopyKey;
         }
         for (long i = 2; i <= Integer.MAX_VALUE; i++) {
            if (null == treeDBManager.getNodeByKey(key + "_copy_" + i)) {
               return key + "_copy_" + i;
            }
         }
         return nextCopyKey;
      }
      String nextCopyKey = findNextCopyKey()
      if (nextCopyKey.length() > KEY_LENGTH)
         return generateDefaultKey(treeDBManager)
      return nextCopyKey
   }
   
   @Override
   public String generateDefaultKey() {
      List<GeneralGenerateDefaultKeyHook> hookers = hookHandler.get().getHookers(GeneralGenerateDefaultKeyHook.class)
      switch (hookers.size()) {
         case 0:
            Random random = new Random()
            String time = String.valueOf(System.currentTimeMillis())
            String rand = String.valueOf(random.nextInt())
            return hashUtilProvider.get().sha1(time + rand)
         case 1:
            return hookers[0].generateDefaultKey()
         default:
            throw new IllegalArgumentException("There is more than 1 GeneralDefaultKeyGeneratorHook registered.")
      }
   }

   @Override
   public String generateDefaultKey(TreeDBManager treeDBManager) {
      List<SpecificGenerateDefaultKeyHook> hookedKeyGenerator = hookHandler.get()
            .getHookers(SpecificGenerateDefaultKeyHook.class)
            .findAll{it.consumes(treeDBManager)}
      if(hookedKeyGenerator.size() > 1)
         throw new IllegalArgumentException("There is more than 1 SpecificDefaultKeyGeneratorHook registered.")
      def previousFlushMode = entityManager.get().flushMode
      def currentKey = ""
      try {
         entityManager.get().flushMode = FlushMode.MANUAL
         while (true) {
            currentKey = hookedKeyGenerator.isEmpty()
                  ? generateDefaultKey()
                  : hookedKeyGenerator[0].generateDefaultKey(treeDBManager)
            def result = treeDBManager.getNodeByKey(currentKey)
            if(result == null)
               throw new NoResultException() // new key found
         }
      } catch (NoResultException ex) {
      } finally {
         entityManager.get().flushMode = previousFlushMode
      }
      return currentKey
   }
}
